package app.nahehuo.com.ui.personal.popup;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.base.BasePopupWindow;
import app.nahehuo.com.util.TextUtil;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by WYB on 2016/2/26.
 */
public class SetIdPopup extends BasePopupWindow
        implements View.OnClickListener {

    TextView tv_cancel;
    TextView tv_compelete;
    SetOnClickCallBack mCallBack;
    String id;
    TextView tv_sub_title;
    StringBuffer sb;


    public SetIdPopup(Activity context, String id) {
        super(context);
        this.id = id;
        sb = new StringBuffer();
        tv_cancel = (TextView) mPopupView.findViewById(R.id.tv_cancel);
        tv_compelete = (TextView) mPopupView.findViewById(R.id.tv_compelete);
        tv_sub_title = (TextView) mPopupView.findViewById(R.id.tv_sub_title);
        bindEvent();
        if (!TextUtil.isEmpty(id)) {
            sb.append("哪合伙ID只能设置一次,确定将");
            sb.append(id);
            sb.append("设为哪合伙ID吗？");
            tv_sub_title.setText(sb.toString());
        }
    }


    public void setCallBack(SetOnClickCallBack callBack) {
        mCallBack = callBack;
    }


    private void bindEvent() {
        tv_cancel.setOnClickListener(this);
        tv_compelete.setOnClickListener(this);
    }


    @Override protected Animation getAnimation() {
        return null;
    }


    @Override protected Animator getAnimator() {
        ViewHelper.setPivotX(getAnimaView(),
                getAnimaView().getMeasuredWidth() / 2);
        ViewHelper.setPivotY(getAnimaView(),
                getAnimaView().getMeasuredHeight() / 2);
        return getDefaultSlideFromBottomAnimationSet();
    }


    @Override public Animator getExitAnimator() {
        AnimatorSet set = null;
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.HONEYCOMB) {
            set = new AnimatorSet();
            if (getAnimaView() != null) {
                set.playTogether(
                        ObjectAnimator.ofFloat(getAnimaView(), "translationY",
                                0, 250).setDuration(400),
                        ObjectAnimator.ofFloat(getAnimaView(), "alpha", 1, 0.4f)
                                      .setDuration(250 * 3 / 2));
            }
        }
        return set;
    }


    @Override protected View getInputView() {
        return null;
    }


    @Override protected View getDismissView() {
        return null;
    }


    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                mCallBack.setCancel();
                break;
            case R.id.tv_compelete:
                mCallBack.setSave();
                break;
        }
    }


    @Override public View getPopupView() {

        return LayoutInflater.from(mContext)
                             .inflate(R.layout.popup_set_id, null);
    }


    @Override public View getAnimaView() {
        return mPopupView.findViewById(R.id.popup_anima);
    }


    public interface SetOnClickCallBack {
        void setSave();

        void setCancel();
    }
}

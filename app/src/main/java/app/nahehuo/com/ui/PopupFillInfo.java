package app.nahehuo.com.ui;

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
 * Created by WYB on 2016/1/29.
 */
public class PopupFillInfo extends BasePopupWindow
        implements View.OnClickListener {

    TextView tv_cancel, tv_title, tv_sub_title;
    SetOnSelect mSetOnSelect;
    String title;
    String sub_title;


    public PopupFillInfo(Activity context, String title, String sub_title) {
        super(context);
        tv_cancel = (TextView) mPopupView.findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
        tv_title = (TextView) mPopupView.findViewById(R.id.tv_title);
        tv_sub_title = (TextView) mPopupView.findViewById(R.id.tv_sub_title);
        this.title = title;
        this.sub_title = sub_title;
        if (!TextUtil.isEmpty(title) && !TextUtil.isEmpty(sub_title)) {
            tv_title.setText(title);
            tv_sub_title.setText(sub_title);
        }
    }


    public void setSetOnSelect(SetOnSelect setOnSelect) {
        mSetOnSelect = setOnSelect;
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


    @Override protected View getInputView() {

        return null;
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


    @Override protected View getDismissView() {
        return null;
    }


    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                dismiss();
                mSetOnSelect.onSelect();
                break;
        }
    }


    @Override public View getPopupView() {
        return LayoutInflater.from(mContext)
                             .inflate(R.layout.popup_fill_info, null);
    }


    @Override public View getAnimaView() {
        return mPopupView.findViewById(R.id.popup_anima);
    }


    public interface SetOnSelect {
        void onSelect();
    }
}

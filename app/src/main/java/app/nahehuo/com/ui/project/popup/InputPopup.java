package app.nahehuo.com.ui.project.popup;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.base.BasePopupWindow;
import app.nahehuo.com.util.TextUtil;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by WYB on 2016/1/26.
 */
public class InputPopup extends BasePopupWindow implements View.OnClickListener{

    private EditText ed_input;
    private TextView tv_cancel;
    private TextView tv_compelete;
    private DataCallBac mDataCallBac;

    public InputPopup(Activity context) {
        super(context);
        ed_input= (EditText) mPopupView.findViewById(R.id.ed_input);
        tv_cancel= (TextView) mPopupView.findViewById(R.id.tv_cancel);
        tv_compelete= (TextView) mPopupView.findViewById(R.id.tv_compelete);
        setAutoShowInputMethod(true);
        bindEvent();
    }

    public void setDataCallBac(DataCallBac dataCallBac) {
        mDataCallBac = dataCallBac;
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
        ViewHelper.setPivotY(getAnimaView(),getAnimaView().getMeasuredHeight()/2);
        return getDefaultSlideFromBottomAnimationSet();
    }


    @Override public Animator getExitAnimator() {
        AnimatorSet set = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            set = new AnimatorSet();
            if (getAnimaView() != null) {
                set.playTogether(
                        ObjectAnimator.ofFloat(getAnimaView(), "translationY",
                                0, 250).setDuration(400),
                        ObjectAnimator.ofFloat(getAnimaView(), "alpha", 1, 0.4f).setDuration(250 * 3 / 2));
            }
        }
        return set;
    }


    @Override protected View getInputView() {
        return ed_input;
    }


    @Override protected View getDismissView() {
        return null;
    }


    @Override public void onClick(View v) {

        switch (v.getId()){
            case R.id.tv_compelete:
                if(!TextUtil.isEmpty(ed_input.getText().toString())){
                    mDataCallBac.getDataBack(ed_input.getText().toString());
                }
                dismiss();
                break;
            case R.id.tv_cancel:
                dismiss();
                break;
        }
    }


    @Override public View getPopupView() {
        return LayoutInflater.from(mContext).inflate(R.layout.popup_input,null);
    }


    @Override public View getAnimaView() {
        return mPopupView.findViewById(R.id.popup_anima);
    }

    public interface DataCallBac {
        void getDataBack(String data);
    }
}

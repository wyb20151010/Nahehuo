package app.nahehuo.com.ui.project.popup;

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
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by WYB on 2016/2/15.
 */
public class EnsurePopup extends BasePopupWindow implements View.OnClickListener{

    TextView tv_cancel;
    TextView tv_compelete;
    EnsureCallBack mEnsureCallBack;

    public EnsurePopup(Activity context) {
        super(context);
        tv_cancel= (TextView) mPopupView.findViewById(R.id.tv_cancel);
        tv_compelete= (TextView) mPopupView.findViewById(R.id.tv_compelete);
        bindEvent();
    }

    public void setEnsureCallBack(EnsureCallBack ensureCallBack) {
        mEnsureCallBack = ensureCallBack;
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
        return null;
    }


    @Override protected View getDismissView() {
        return null;
    }


    @Override public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_cancel:
                mEnsureCallBack.setCancel();
                break;
            case R.id.tv_compelete:
                mEnsureCallBack.setSave();
                break;
        }

    }


    @Override public View getPopupView() {
        return LayoutInflater.from(mContext).inflate(R.layout
                .popup_project_ensure,null);
    }


    @Override public View getAnimaView() {
        return mPopupView.findViewById(R.id.popup_anima);
    }

    public  interface EnsureCallBack{
        void setSave();
        void setCancel();
    }
}

package app.nahehuo.com.ui.partner.popup;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import app.nahehuo.com.R;
import app.nahehuo.com.base.BasePopupWindow;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by WYB on 2016/2/22.
 */
public class SelectSexPopup extends BasePopupWindow
        implements View.OnClickListener {

    private RelativeLayout rl_male, rl_female;

    SetOnClickCallBack mCallBack;


    public SelectSexPopup(Activity context) {
        super(context);
        rl_male = (RelativeLayout) mPopupView.findViewById(R.id.rl_male);
        rl_female = (RelativeLayout) mPopupView.findViewById(R.id.rl_female);
        rl_male.setOnClickListener(this);
        rl_female.setOnClickListener(this);
        mDismissView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                dismiss();
            }
        });
    }


    public void setCallBack(SetOnClickCallBack callBack) {
        mCallBack = callBack;
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
        return mPopupView.findViewById(R.id.rl_dismiss);
    }


    @Override public View getPopupView() {
        return LayoutInflater.from(mContext)
                             .inflate(R.layout.popup_select_sex, null);
    }


    @Override public View getAnimaView() {
        return mPopupView.findViewById(R.id.popup_anima);
    }


    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_male:
                mCallBack.setMale("男生");
                break;
            case R.id.rl_female:
                mCallBack.setFemale("女生");
                break;
        }
    }


    public interface SetOnClickCallBack {
        void setMale(String male);

        void setFemale(String female);
    }
}

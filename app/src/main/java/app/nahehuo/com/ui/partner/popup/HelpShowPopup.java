package app.nahehuo.com.ui.partner.popup;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import app.nahehuo.com.R;
import app.nahehuo.com.base.BasePopupWindow;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by WYB on 2016/2/23.
 */
public class HelpShowPopup extends BasePopupWindow {

    private Button btn_cancel;

    public HelpShowPopup(Activity context) {
        super(context);
        btn_cancel = (Button) mPopupView.findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                dismiss();
            }
        });
        mDismissView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                dismiss();
            }
        });
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
                             .inflate(R.layout.popup_show_help, null);
    }


    @Override public View getAnimaView() {
        return mPopupView.findViewById(R.id.popup_anima);
    }
}

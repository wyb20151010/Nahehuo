package app.nahehuo.com.ui.partner.popup;

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
 * Created by WYB on 2016/1/29.
 */
public class QRCodePopup extends BasePopupWindow {

    private TextView tv_name, tv_position, tv_id;


    public QRCodePopup(Activity context, String name, String position, String id) {
        super(context);
        tv_name = (TextView) mPopupView.findViewById(R.id.tv_name);
        tv_position = (TextView) mPopupView.findViewById(R.id.tv_position);
        tv_id = (TextView) mPopupView.findViewById(R.id.tv_id);
        tv_name.setText(name);
        tv_position.setText(position);
        tv_id.setText(id);
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
                             .inflate(R.layout.popup_two_demin, null);
    }


    @Override public View getAnimaView() {
        return mPopupView.findViewById(R.id.popup_anima);
    }
}

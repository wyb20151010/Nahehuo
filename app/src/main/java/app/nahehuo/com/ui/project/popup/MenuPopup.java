package app.nahehuo.com.ui.project.popup;

import android.animation.Animator;
import android.app.Activity;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import app.nahehuo.com.R;
import app.nahehuo.com.base.BasePopupWindow;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by WYB on 2016/1/26.
 */
public class MenuPopup extends BasePopupWindow implements View.OnClickListener {

    private LinearLayout ll_start_pro, ll_search_pro, ll_my_pro,
            ll_apply_partner;

    private int[] viewLocation;
    public MenuPopup(Activity context) {
        super(context);
        viewLocation=new int[2];
        ll_start_pro = (LinearLayout) mPopupView.findViewById(
                R.id.ll_start_pro);
        ll_start_pro.setOnClickListener(this);
        ll_search_pro = (LinearLayout) mPopupView.findViewById(
                R.id.ll_search_pro);
        ll_search_pro.setOnClickListener(this);
        ll_my_pro = (LinearLayout) mPopupView.findViewById(R.id.ll_my_pro);
        ll_my_pro.setOnClickListener(this);
        ll_apply_partner = (LinearLayout) mPopupView.findViewById(
                R.id.ll_apply_partner);
        ll_apply_partner.setOnClickListener(this);
    }


    @Override protected Animation getAnimation() {
        AnimationSet set=new AnimationSet(true);
        set.setInterpolator(new DecelerateInterpolator());
        set.addAnimation(getScaleAnimation(0,1,0,1,Animation.RELATIVE_TO_SELF,1,Animation.RELATIVE_TO_SELF,0));
        set.addAnimation(getDefaultAlphaAnimation());
        return set;
    }


    @Override protected Animator getAnimator() {
        return null;
    }


    @Override protected View getInputView() {
        return null;
    }


    @Override protected View getDismissView() {
        return null;
    }


    @Override public void onClick(View v) {

        switch (v.getId()){
            case R.id.ll_start_pro:

                break;
            case R.id.ll_search_pro:
                break;
            case R.id.ll_my_pro:
                break;
            case R.id.ll_apply_partner:
                break;
        }
    }


    @Override public void showPopupWindow(View v) {
        try {
            v.getLocationOnScreen(viewLocation);
            mPopupWindow.showAsDropDown(v, 0, -100);
            if (getAnimation() != null && mAnimaView != null) {
                mAnimaView.clearAnimation();
                mAnimaView.startAnimation(getAnimation());
            }
            if (getAnimation() == null && getAnimator() != null && mAnimaView != null &&
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                ViewHelper.setPivotX(mAnimaView, 1);
                ViewHelper.setPivotY(mAnimaView,0);
                getAnimator().start();
            }
        } catch (Exception e) {
            Log.w("error", "error");
        }
    }


    @Override public View getPopupView() {
        return LayoutInflater.from(mContext).inflate(R.layout.popup_menu, null);
    }


    @Override public View getAnimaView() {
        return mPopupView.findViewById(R.id.popup_contianer);
    }
}

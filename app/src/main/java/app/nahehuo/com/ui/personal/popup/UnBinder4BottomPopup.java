package app.nahehuo.com.ui.personal.popup;

import android.animation.Animator;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import app.nahehuo.com.R;
import app.nahehuo.com.base.BasePopupWindow;

/**
 * Created by Administrator on 2016/3/2.
 */
public class UnBinder4BottomPopup extends BasePopupWindow
        implements View.OnClickListener {

    private View popupView;
    private SetOnItemClickListener mSetOnItemClickListener;


    public UnBinder4BottomPopup(Activity context) {
        super(context);
        bindEvent();
    }


    public void setSetOnItemClickListener(SetOnItemClickListener setOnItemClickListener) {
        mSetOnItemClickListener = setOnItemClickListener;
    }


    @Override protected Animation getAnimation() {
        return getTranslateAnimation(250 * 2, 0, 300);
    }


    @Override protected Animator getAnimator() {
        return null;
    }


    @Override protected View getInputView() {
        return null;
    }


    @Override protected View getDismissView() {
        return popupView.findViewById(R.id.click_to_dismiss);
    }


    @Override public View getPopupView() {
        popupView = LayoutInflater.from(mContext)
                                  .inflate(R.layout.popup_slide_from_bottom,
                                          null);
        return popupView;
    }


    @Override public View getAnimaView() {
        return popupView.findViewById(R.id.popup_anima);
    }


    private void bindEvent() {
        if (popupView != null) {
            popupView.findViewById(R.id.tx_1).setOnClickListener(this);
            popupView.findViewById(R.id.tx_2).setOnClickListener(this);
        }
    }


    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tx_1:
                mSetOnItemClickListener.setUnbinderBankCard();
                break;
            case R.id.tx_2:
                mSetOnItemClickListener.setDismiss();
                break;
            default:
                break;
        }
    }


    public interface SetOnItemClickListener {
        void setUnbinderBankCard();

        void setDismiss();
    }
}

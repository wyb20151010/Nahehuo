package app.nahehuo.com.ui.personal.popup;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import app.nahehuo.com.R;
import app.nahehuo.com.base.BasePopupWindow;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by wyb on 2016/3/2.
 */
public class EnterPwdPopup extends BasePopupWindow
        implements View.OnClickListener {

    private EditText et_1, et_2, et_3, et_4, et_5, et_6;
    private TextView tv_fg_pwd, tv_ok;
    private OnClickListenter mListenter;


    public EnterPwdPopup(Activity context) {
        super(context);

        et_1 = (EditText) mPopupView.findViewById(R.id.et_1);
        et_2 = (EditText) mPopupView.findViewById(R.id.et_2);
        et_3 = (EditText) mPopupView.findViewById(R.id.et_3);
        et_4 = (EditText) mPopupView.findViewById(R.id.et_4);
        et_5 = (EditText) mPopupView.findViewById(R.id.et_5);
        et_6 = (EditText) mPopupView.findViewById(R.id.et_6);
        et_1.requestFocus();

        et_1.addTextChangedListener(mWatcher);
        et_2.addTextChangedListener(mWatcher);
        et_3.addTextChangedListener(mWatcher);
        et_4.addTextChangedListener(mWatcher);
        et_5.addTextChangedListener(mWatcher);
        et_6.addTextChangedListener(mWatcher);
        tv_fg_pwd = (TextView) mPopupView.findViewById(R.id.tv_fg_pwd);
        tv_ok = (TextView) mPopupView.findViewById(R.id.tv_ok);
        tv_fg_pwd.setOnClickListener(this);
        tv_ok.setOnClickListener(this);
    }


    public void setListenter(OnClickListenter listenter) {
        mListenter = listenter;
    }


    public void clearEditText() {
        et_1.setText("");
        et_2.setText("");
        et_3.setText("");
        et_4.setText("");
        et_5.setText("");
        et_6.setText("");
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


    @Override public View getPopupView() {

        return LayoutInflater.from(mContext)
                             .inflate(R.layout.popup_enter_pwd, null);
    }


    @Override public View getAnimaView() {
        return mPopupView.findViewById(R.id.popup_anima);
    }


    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_fg_pwd:
                mListenter.setOnforgetPwd();
                break;
            case R.id.tv_ok:
                if (!TextUtils.isEmpty(et_1.getText()) &&
                        !TextUtils.isEmpty(et_2.getText()) &&
                        !TextUtils.isEmpty(et_3.getText()) &&
                        !TextUtils.isEmpty(et_4.getText()) &&
                        !TextUtils.isEmpty(et_5.getText()) &&
                        !TextUtils.isEmpty(et_6.getText())) {
                    StringBuffer buffer = new StringBuffer();
                    buffer.append(et_1.getText().toString());
                    buffer.append(et_2.getText().toString());
                    buffer.append(et_3.getText().toString());
                    buffer.append(et_4.getText().toString());
                    buffer.append(et_5.getText().toString());
                    buffer.append(et_6.getText().toString());
                    mListenter.setOnOK(buffer.toString());
                }
                else {
                    Toast.makeText(mContext, "请完整填写密码", Toast.LENGTH_SHORT)
                         .show();
                }
                break;
        }
    }


    public interface OnClickListenter {
        void setOnOK(String pwd);

        void setOnforgetPwd();
    }

    TextWatcher mWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }


        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }


        @Override public void afterTextChanged(Editable s) {
            if (s.toString().length() == 1) {
                if (et_1.isFocused()) {
                    et_1.clearFocus();
                    et_2.requestFocus();
                }
                else if (et_2.isFocused()) {
                    et_2.clearFocus();
                    et_3.requestFocus();
                }
                else if (et_3.isFocused()) {
                    et_3.clearFocus();
                    et_4.requestFocus();
                }
                else if (et_4.isFocused()) {
                    et_4.clearFocus();
                    et_5.requestFocus();
                }
                else if (et_5.isFocused()) {
                    et_5.clearFocus();
                    et_6.requestFocus();
                }
                else if (et_6.isFocused()) {
                    et_6.clearFocus();
                }
            }
        }
    };
}

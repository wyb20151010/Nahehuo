package app.nahehuo.com.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.application.MyApplication;
import app.nahehuo.com.base.GlobalVariables;
import app.nahehuo.com.network.JsonObjectCallback;
import app.nahehuo.com.util.CheckTextFormatUtil;
import app.nahehuo.com.util.MyToast;
import app.nahehuo.com.util.TextUtil;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.zhy.http.okhttp.OkHttpUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by WYB on 2015/12/30.
 */
public class RegisterActivity extends AppCompatActivity
        implements View.OnClickListener {

    private RelativeLayout rl_go_view;
    private TextView tv_title, tv_user_agreement, tv_back_login, tv_verif_code;
    private Context mContext;
    private Button btn_register;
    private MaterialEditText met_phone, met_code;
    private PopupFillInfo mPopupFillInfo;
    private PopupFillInfo mPopupFillInfo2;
    private String phone;
    private String code;
    private static final int GETFAKETOKEN = 0;
    private static final int CONN_CHECKPHONEEXIT = 1;
    private static final int GETPHONECODE = 2;
    private static final int CHECKPHONECODE = 3;
    private SharedPreferences.Editor mEditor;

    private Handler mHandler = new Handler() {
        @Override public void handleMessage(Message msg) {
            if (msg.what == CONN_CHECKPHONEEXIT) {
                checkPhoneExit();
            }
            else if (msg.what == GETPHONECODE) {
                findPhoneCode();
            }
            else if (msg.what == CHECKPHONECODE) {
                checkPhoneCode();
            }else if(msg.what==GETFAKETOKEN){
                findFakeToken();
            }
            super.handleMessage(msg);
        }
    };

    private void findFakeToken() {
        OkHttpUtils.get()
                   .url(GlobalVariables.URL_CHECK_DICTVERSION)
                   .addParams("deviceCode", "123")
                   .addParams("deviceName", "app")
                   .build()
                   .execute(new JsonObjectCallback() {
                       @Override public void onResponse(String response) {
                           if (!TextUtil.isEmpty(response)) {
                               try {
                                   JSONObject jsonObject = new JSONObject(
                                           response);
                                   GlobalVariables.fake_access_token
                                           = jsonObject.getString(
                                           "accessToken");

                                   mEditor = getSharedPreferences("user",
                                           MODE_PRIVATE).edit();
                                   mEditor.putString("fake_access_token",
                                           jsonObject.getString("accessToken"))
                                          .commit();

                               } catch (JSONException e) {
                                   e.printStackTrace();
                               }
                           }
                           super.onResponse(response);
                       }
                   });
    }
    private void checkPhoneCode() {
        OkHttpUtils.get()
                   .url(GlobalVariables.CHECKPHONECODE)
                   .addParams("access_token", GlobalVariables.fake_access_token)
                   .addParams("device", "app")
                   .addParams("phone", phone)
                   .addParams("code", code)
                   .build()
                   .execute(new JsonObjectCallback() {
                       @Override public void onResponse(String response) {
                           if (!TextUtil.isEmpty(response)) {
                               try {
                                   JSONObject jsonObject = new JSONObject(
                                           response);
                                   if (jsonObject.getInt("code") == 1003) {
                                       MyToast.showToast(mContext, "验证码错误");
                                       Intent intent = new Intent(mContext,
                                               EnterPasswordActivity.class);
                                       intent.putExtra("phone", phone);
                                       intent.putExtra("code", code);
                                       startActivity(intent);
                                       overridePendingTransition(
                                               R.anim.push_left_in,
                                               R.anim.push_left_out);
                                   }
                                   else {
                                       //TODO 成功进入
                                      /* startActivity(new Intent(mContext,
                                               EnterPasswordActivity.class));
                                       overridePendingTransition(
                                               R.anim.push_left_in,
                                               R.anim.push_left_out);*/

                                   }
                               } catch (JSONException e) {
                                   e.printStackTrace();
                               }
                           }
                           super.onResponse(response);
                       }
                   });
    }


    private void findPhoneCode() {
        OkHttpUtils.get()
                   .url(GlobalVariables.GETPHONECODE)
                   .addParams("access_token", GlobalVariables.fake_access_token)
                   .addParams("device", "app")
                   .addParams("phone", phone)
                   .build()
                   .execute(new JsonObjectCallback() {
                       @Override public void onResponse(String response) {
                           if (!TextUtil.isEmpty(response)) {
                               try {
                                   JSONObject jsonObject = new JSONObject(
                                           response);
                                   if (jsonObject.getInt("code") == 1027) {
                                       MyToast.showToast(mContext,
                                               jsonObject.getString("message"));
                                   }
                                   else {
                                       MyToast.showToast(mContext, "验证码已发送");
                                   }
                               } catch (JSONException e) {
                                   e.printStackTrace();
                               }
                           }
                           super.onResponse(response);
                       }
                   });
    }


    private void checkPhoneExit() {
        OkHttpUtils.get()
                   .url(GlobalVariables.CHECKPHONEEXIT)
                   .addParams("access_token", GlobalVariables.fake_access_token)
                   .addParams("device", "app")
                   .addParams("phone", phone)
                   .build()
                   .execute(new JsonObjectCallback() {
                       @Override public void onResponse(String response) {
                           if (!TextUtil.isEmpty(response)) {
                               try {
                                   JSONObject jsonObject = new JSONObject(
                                           response);
                                   if (jsonObject.getInt("code") == 200) {
                                       new CountDownTimer(30000, 1000) {

                                           @Override
                                           public void onTick(long millisUntilFinished) {
                                               tv_verif_code.setText(
                                                       millisUntilFinished /
                                                               1000 + "s后重新发送");
                                               tv_verif_code.setEnabled(false);
                                           }


                                           @Override public void onFinish() {
                                               tv_verif_code.setText("重新获取验证码");
                                               tv_verif_code.setEnabled(true);
                                           }
                                       }.start();
                                       mHandler.sendEmptyMessage(GETPHONECODE);
                                   }
                                   else if (jsonObject.getInt("code") == 1004) {
                                       mPopupFillInfo2.showPopupWindow();
                                       mPopupFillInfo2.setSetOnSelect(
                                               new PopupFillInfo.SetOnSelect() {
                                                   @Override
                                                   public void onSelect() {
                                                       mPopupFillInfo2.dismiss();
                                                   }
                                               });
                                   }
                               } catch (JSONException e) {
                                   e.printStackTrace();
                               }
                           }
                           super.onResponse(response);
                       }
                   });
    }


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        MyApplication.getInstance().addActivity(this);
        mContext = this;
        mPopupFillInfo2 = new PopupFillInfo(this, "手机号已被注册", "此手机号已被注册，请换一个");
        initView();
        initToolbar();
        mHandler.sendEmptyMessage(GETFAKETOKEN);
    }


    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("注册");
        tv_user_agreement = (TextView) findViewById(R.id.tv_user_agreement);
        tv_user_agreement.setOnClickListener(this);
        btn_register = (Button) findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);
        tv_back_login = (TextView) findViewById(R.id.tv_back_login);
        tv_back_login.setOnClickListener(this);
        tv_verif_code = (TextView) findViewById(R.id.tv_verif_code);
        tv_verif_code.setOnClickListener(this);
        met_phone = (MaterialEditText) findViewById(R.id.met_phone);
        met_code = (MaterialEditText) findViewById(R.id.met_code);
        rl_go_view = (RelativeLayout) findViewById(R.id.rl_go_view);
        rl_go_view.setOnClickListener(this);
    }


    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_user_agreement:
                startActivity(
                        new Intent(mContext, UserAgreementActivity.class));
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.btn_register:
                if (!TextUtil.isEmpty(met_code.getText().toString())) {
                    code = met_code.getText().toString();
                    mHandler.sendEmptyMessage(CHECKPHONECODE);
                }
                else {
                    MyToast.showToast(mContext, "请输入验证码");
                }

                break;
            case R.id.tv_back_login:
                startActivity(new Intent(mContext, LoginActivity.class));
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.tv_verif_code:
                if (!TextUtil.isEmpty(met_phone.getText().toString())) {
                    if (CheckTextFormatUtil.isMobile(
                            met_phone.getText().toString())) {
                        phone = met_phone.getText().toString();
                        mHandler.sendEmptyMessage(CONN_CHECKPHONEEXIT);
                    }
                    else {
                        mPopupFillInfo = new PopupFillInfo(this, "手机号格式错误",
                                "目前支持11位有效中国大陆号码");
                        mPopupFillInfo.showPopupWindow();
                        mPopupFillInfo.setSetOnSelect(
                                new PopupFillInfo.SetOnSelect() {
                                    @Override public void onSelect() {
                                        mPopupFillInfo.dismiss();
                                    }
                                });
                    }
                }
                else {
                    MyToast.showToast(mContext, "请输入手机号");
                }

                break;
            case R.id.rl_go_view:
                startActivity(new Intent(mContext, MainActivity.class));
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
        }
    }
}

package app.nahehuo.com.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import app.nahehuo.com.R;
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
 * Created by Administrator on 2015/12/31.
 */
public class FindPasswordActivity extends AppCompatActivity
        implements View.OnClickListener {

    private Toolbar mToolbar;
    private TextView tv_title, tv_enter_email, tv_verif_code;
    private Button btn_find_pass_next;
    private Context mContext;
    private MaterialEditText met_phone, met_code;
    private String phone;
    private String code;
    private PopupFillInfo mPopupFillInfo2;
    private PopupFillInfo mPopupFillInfo;

    private static final int CONN_CHECKPHONEEXIT = 1;
    private static final int GETPHONECODE = 2;
    private static final int CHECKPHONECODE = 3;
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
            }
            super.handleMessage(msg);
        }
    };


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
                                               EnterPassword2Activity.class);
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
                                       mPopupFillInfo2.showPopupWindow();
                                       mPopupFillInfo2.setSetOnSelect(
                                               new PopupFillInfo.SetOnSelect() {
                                                   @Override
                                                   public void onSelect() {
                                                       mPopupFillInfo2.dismiss();
                                                   }
                                               });
                                   }
                                   else if (jsonObject.getInt("code") == 1004) {

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
        setContentView(R.layout.activity_find_password);
        mContext = this;
        mPopupFillInfo2 = new PopupFillInfo(this, "手机号还未注册", "此手机号不是哪合伙账号");
        initView();
        initToolbar();
    }


    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("验证手机");
        btn_find_pass_next = (Button) findViewById(R.id.btn_find_pass_next);
        btn_find_pass_next.setOnClickListener(this);
        tv_enter_email = (TextView) findViewById(R.id.tv_enter_email);
        tv_enter_email.setOnClickListener(this);
        tv_verif_code = (TextView) findViewById(R.id.tv_verif_code);
        tv_verif_code.setOnClickListener(this);
        met_phone = (MaterialEditText) findViewById(R.id.met_phone);
        met_code = (MaterialEditText) findViewById(R.id.met_code);
    }


    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_find_pass_next:

                if (!TextUtil.isEmpty(met_code.getText().toString())) {
                    code = met_code.getText().toString();
                    mHandler.sendEmptyMessage(CHECKPHONECODE);
                }
                else {
                    MyToast.showToast(mContext, "请输入验证码");
                }
                break;
            case R.id.tv_enter_email:
                startActivity(new Intent(mContext, EnterEmailActivity.class));
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);

                break;
            case R.id.tv_verif_code:
                if (!TextUtils.isEmpty(met_phone.getText().toString())) {
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
        }
    }
}

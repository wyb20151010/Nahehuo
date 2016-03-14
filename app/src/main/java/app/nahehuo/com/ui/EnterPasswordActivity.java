package app.nahehuo.com.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import app.nahehuo.com.application.MyApplication;
import app.nahehuo.com.base.GlobalVariables;
import app.nahehuo.com.bean.Login;
import app.nahehuo.com.network.GsonCallBack;
import app.nahehuo.com.util.MyToast;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by WYB on 2015/12/30.
 */
public class EnterPasswordActivity extends AppCompatActivity
        implements View.OnClickListener {

    private MaterialEditText met_pwd;
    private TextView tv_title;
    private Toolbar mToolbar;
    private Button btn_register_ok;
    private Context mContext;
    private Intent mIntent;
    private String phone;
    private String code;
    private String pwd;
    private static final int REGISTER_POST = 0;
    private Handler mHandler = new Handler() {

        @Override public void handleMessage(Message msg) {
            switch (msg.what) {
                case REGISTER_POST:
                    registerPost();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    private SharedPreferences sp;


    private void registerPost() {
        OkHttpUtils.post()
                   .url(GlobalVariables.REGISTER_POST)
                   .addParams("phone", phone)
                   .addParams("code", code)
                   .addParams("password", pwd)
                   .addParams("access_token", GlobalVariables.fake_access_token)
                   .addParams("device", "app")
                   .build()
                   .execute(new GsonCallBack<Login>(Login.class) {
                       @Override public void onResponse(Login response) {
                           if (response.getCode() == 1003) {
                               sp.edit()
                               /*  .putString("access_token",
                                         response.getData().getAccessToken())
                                 .putString("email", response.getData()
                                                             .getUser()
                                                             .getEmail())
                                 .putString("username", phone)
                                 .putString("password", pwd)
                                 .putString("refresh_token",
                                         response.getData().getRefreshToken())
                                 .putLong("dead_time",
                                         System.currentTimeMillis() +
                                                 response.getData()
                                                         .getAccessTokenExpiration() *
                                                         1000)*/
                                 .putBoolean("isRegister", true)
                                 .commit();

                               /*GlobalVariables.access_token = response.getData()
                                                                      .getAccessToken();*/
                               startActivity(
                                       new Intent(mContext, FillInformationActivity.class));
                               overridePendingTransition(R.anim.push_left_in,
                                       R.anim.push_left_out);
                           }
                           else {
                               MyToast.showToast(mContext,
                                       response.getMessage());
                           }
                           super.onResponse(response);
                       }

                       /*  @Override public void onResponse(String response) {
                           try {
                               JSONObject jsonObject = new JSONObject(response);
                               int code = jsonObject.getInt("code");
                               if (code == 1003) {
                                   MyToast.showToast(mContext,
                                           jsonObject.getString("message"));
                                  ;
                               }
                               else {
                                   //TODO 成功进入

                               }
                           } catch (JSONException e) {
                               e.printStackTrace();
                           }
                           super.onResponse(response);
                       }*/
                   });
    }


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_password);
        MyApplication.getInstance().addActivity(this);
        mContext = this;
        sp = getSharedPreferences("user", MODE_PRIVATE);
        mIntent = getIntent();
        phone = mIntent.getStringExtra("phone");
        code = mIntent.getStringExtra("code");
        initView();
        initToolbar();
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


    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("输入密码");
        btn_register_ok = (Button) findViewById(R.id.btn_register_ok);
        btn_register_ok.setOnClickListener(this);
        met_pwd = (MaterialEditText) findViewById(R.id.met_pwd);
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
            case R.id.btn_register_ok:
                if (!TextUtils.isEmpty(met_pwd.getText().toString())) {
                    pwd = met_pwd.getText().toString();
                    mHandler.sendEmptyMessage(REGISTER_POST);
                }


                break;
        }
    }
}

package app.nahehuo.com.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.application.MyApplication;
import app.nahehuo.com.base.GlobalVariables;
import app.nahehuo.com.network.JsonObjectCallback;
import app.nahehuo.com.util.MyToast;
import app.nahehuo.com.util.TextUtil;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.zhy.http.okhttp.OkHttpUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by WYB on 2015/12/30.
 */
public class EnterPassword2Activity extends AppCompatActivity
        implements View.OnClickListener {

    private TextView tv_title;
    private Toolbar mToolbar;
    private Button btn_register_ok;
    private Context mContext;
    private Intent mIntent;
    private String phone;
    private String code;
    private String pwd;
    private MaterialEditText met_pwd;

    private final static int FORGETPWD = 0;
    private Handler mHandler = new Handler() {
        @Override public void handleMessage(Message msg) {
            if (msg.what == FORGETPWD) {
                findForgetPwd();
            }
            super.handleMessage(msg);
        }
    };


    private void findForgetPwd() {
        OkHttpUtils.get()
                   .url(GlobalVariables.PHONE_RESETPASSWORD)
                   .addParams("access_token", GlobalVariables.fake_access_token)
                   .addParams("device", GlobalVariables.device)
                   .addParams("phone", phone)
                   .addParams("code", code)
                   .addParams("password", pwd)
                   .build()
                   .execute(new JsonObjectCallback() {
                       @Override public void onResponse(String response) {
                           Log.d("TAG", response);
                           try {
                               JSONObject jsonObject=new JSONObject(response);
                               int status=jsonObject.getInt(code);
                               if(status==1003){
                                   MyToast.showToast(mContext,jsonObject
                                           .getString("message"));
                                   startActivity(
                                           new Intent(mContext, ChangePassSuccessActivity.class));
                                   overridePendingTransition(R.anim.push_left_in,
                                           R.anim.push_left_out);
                               }
                           } catch (JSONException e) {
                               e.printStackTrace();
                           }
                           super.onResponse(response);
                       }
                   });
    }


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_password);
        MyApplication.getInstance().addActivity(this);
        mContext = this;
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
                if (!TextUtil.isEmpty(met_pwd.getText().toString())) {
                    pwd = met_pwd.getText().toString();
                    mHandler.sendEmptyMessage(FORGETPWD);
                }
                else {
                    MyToast.showToast(mContext, "请输入密码");
                }


                break;
        }
    }
}

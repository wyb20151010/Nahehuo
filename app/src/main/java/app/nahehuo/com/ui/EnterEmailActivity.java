package app.nahehuo.com.ui;

import android.content.Context;
import android.content.Intent;
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
import app.nahehuo.com.base.GlobalVariables;
import app.nahehuo.com.network.JsonObjectCallback;
import app.nahehuo.com.util.CheckTextFormatUtil;
import app.nahehuo.com.util.MyToast;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.zhy.http.okhttp.OkHttpUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by WYB on 2015/12/31.
 */
public class EnterEmailActivity extends AppCompatActivity
        implements View.OnClickListener {

    private TextView tv_title;
    private Toolbar mToolbar;
    private Context mContext;
    private Button btn_enter_email_ok;
    private MaterialEditText met_email;
    private String email;
    private PopupFillInfo mFillInfo;

    private final static int EMAIL_RESETPASSWORD = 0;
    private Handler mHandler = new Handler() {
        @Override public void handleMessage(Message msg) {
            switch (msg.what) {
                case EMAIL_RESETPASSWORD:
                    emailResetPwd();
                    break;
            }
            super.handleMessage(msg);
        }
    };


    private void emailResetPwd() {
        OkHttpUtils.get()
                   .url(GlobalVariables.EMAIL_RESETPASSWORD)
                   .addParams("access_token", GlobalVariables.fake_access_token)
                   .addParams("device", GlobalVariables.device)
                   .addParams("email", email)
                   .build()
                   .execute(new JsonObjectCallback() {
                       @Override public void onResponse(String response) {
                           try {
                               JSONObject jsonObject = new JSONObject(response);
                               int status = jsonObject.getInt("code");
                               if (status == 200) {
                                   Intent intent = new Intent(mContext,
                                           ChangeEmailPassSuccessActivity.class);
                                   intent.putExtra("email", email);
                                   startActivity(intent);
                                   overridePendingTransition(
                                           R.anim.push_left_in,
                                           R.anim.push_left_out);
                               }
                               else {
                                   mFillInfo.showPopupWindow();
                                   mFillInfo.setSetOnSelect(
                                           new PopupFillInfo.SetOnSelect() {
                                               @Override
                                               public void onSelect() {
                                                   mFillInfo.dismiss();
                                               }
                                           });
                                   MyToast.showToast(mContext,
                                           jsonObject.getString("message"));
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
        setContentView(R.layout.activity_enter_email);
        mContext = this;
        mFillInfo = new PopupFillInfo(this, "邮件发送失败", "你填写的邮箱有误或未验证，请重新填写");
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
        tv_title.setText("验证邮箱");
        btn_enter_email_ok = (Button) findViewById(R.id.btn_enter_email_ok);
        btn_enter_email_ok.setOnClickListener(this);
        met_email = (MaterialEditText) findViewById(R.id.met_email);
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
            case R.id.btn_enter_email_ok:
                if (!TextUtils.isEmpty(met_email.getText().toString())&&
                        CheckTextFormatUtil.checkEmail(met_email.getText().toString())) {
                    email = met_email.getText().toString();
                    mHandler.sendEmptyMessage(EMAIL_RESETPASSWORD);
                }

                break;
        }
    }
}

package app.nahehuo.com.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.application.MyApplication;

/**
 * Created by WYB on 2015/12/31.
 */
public class ChangeEmailPassSuccessActivity extends AppCompatActivity
        implements View.OnClickListener {

    private TextView tv_title;
    private Toolbar mToolbar;
    private Context mContext;
    private Button btn_back_login;
    private String email;
    private Intent mIntent;
    private TextView tv_change_email_pass;
    private char[] mCharacters;
    private int indexAt;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email_pass_success);
        MyApplication.getInstance().addActivity(this);
        mContext = this;

        mIntent = getIntent();
        email = mIntent.getStringExtra("email");
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
        tv_title.setText("发送成功");
        btn_back_login = (Button) findViewById(R.id.btn_back_login);
        btn_back_login.setOnClickListener(this);

        mCharacters = email.toCharArray();
        for (int i = 0; i < mCharacters.length; i++) {
            if (mCharacters[i] == '@') {
                indexAt = i;
            }
        }

        tv_change_email_pass = (TextView) findViewById(
                R.id.tv_change_email_pass);
        tv_change_email_pass.setText(
                "邮件已发送到邮箱" + email.substring(0, indexAt - 2) + "**" +
                        email.substring(indexAt, email.length()));
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
            case R.id.btn_back_login:
                startActivity(new Intent(mContext, LoginActivity.class));
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
        }
    }
}

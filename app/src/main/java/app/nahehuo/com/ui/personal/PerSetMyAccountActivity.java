package app.nahehuo.com.ui.personal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import app.nahehuo.com.R;

/**
 * Created by wyb on 2016/3/4.
 */
public class PerSetMyAccountActivity extends AppCompatActivity
        implements View.OnClickListener {

    private Toolbar mToolbar;
    private TextView tv_title;
    private Context mContext;
    private LinearLayout ll_id, ll_phone, ll_email, ll_change_pwd;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per_set_account);
        mContext = this;
        initView();
        initToolbar();
    }


    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("账号设置");
        ll_id = (LinearLayout) findViewById(R.id.ll_id);
        ll_phone = (LinearLayout) findViewById(R.id.ll_phone);
        ll_email = (LinearLayout) findViewById(R.id.ll_email);
        ll_change_pwd = (LinearLayout) findViewById(R.id.ll_change_pwd);
        ll_change_pwd.setOnClickListener(this);
        ll_id.setOnClickListener(this);
        ll_phone.setOnClickListener(this);
        ll_email.setOnClickListener(this);
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
            case R.id.ll_change_pwd:
                startActivity(
                        new Intent(mContext, PerSetValidateActivity.class));
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.ll_id:
                startActivity(new Intent(mContext, PerSetIdActivity.class));
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.ll_phone:
                startActivity(
                        new Intent(mContext, PerInfoCgPhoneActivity.class));
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.ll_email:
                startActivity(
                        new Intent(mContext, PerSetBindEmailActivity.class));
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
        }
    }
}

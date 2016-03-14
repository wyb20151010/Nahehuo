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
import android.widget.RelativeLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.ui.UserAgreementActivity;
import app.nahehuo.com.ui.partner.PartnerMsgActivity;

/**
 * Created by wyb on 2016/3/4.
 */
public class PersonalMySetActivity extends AppCompatActivity
        implements View.OnClickListener {

    private Toolbar mToolbar;
    private TextView tv_title;
    private Context mContext;

    private LinearLayout ll_my_account, ll_push, ll_cache, ll_advice, ll_about,
            ll_user;
    private RelativeLayout rl_quit;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per_my_set);
        mContext = this;
        initView();
        initToolbar();
    }


    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("设置");
        ll_my_account = (LinearLayout) findViewById(R.id.ll_my_account);
        ll_push = (LinearLayout) findViewById(R.id.ll_push);
        ll_cache = (LinearLayout) findViewById(R.id.ll_cache);
        ll_advice = (LinearLayout) findViewById(R.id.ll_advice);
        ll_about = (LinearLayout) findViewById(R.id.ll_about);
        ll_user = (LinearLayout) findViewById(R.id.ll_user);
        rl_quit = (RelativeLayout) findViewById(R.id.rl_quit);
        ll_my_account.setOnClickListener(this);
        ll_push.setOnClickListener(this);
        ll_cache.setOnClickListener(this);
        ll_advice.setOnClickListener(this);
        ll_about.setOnClickListener(this);
        ll_user.setOnClickListener(this);
        rl_quit.setOnClickListener(this);
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
            case R.id.rl_quit:
                break;
            case R.id.ll_my_account:
                startActivity(
                        new Intent(mContext, PerSetMyAccountActivity.class));
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.ll_push:
                startActivity(
                        new Intent(mContext, PerSetMsgPushActivity.class));
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.ll_cache:
                break;
            case R.id.ll_advice:
                startActivity(new Intent(mContext, PartnerMsgActivity.class));
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.ll_about:
                startActivity(
                        new Intent(mContext, PerSetAboutAppActivity.class));
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.ll_user:
                startActivity(
                        new Intent(mContext, UserAgreementActivity.class));
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
        }
    }
}

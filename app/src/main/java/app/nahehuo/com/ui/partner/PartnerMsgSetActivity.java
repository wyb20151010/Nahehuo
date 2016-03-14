package app.nahehuo.com.ui.partner;

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
 * Created by WYB on 2016/2/23.
 */
public class PartnerMsgSetActivity extends AppCompatActivity
        implements View.OnClickListener {

    protected Toolbar mToolbar;
    protected TextView tv_title;
    private Context mContext;
    private LinearLayout ll_report;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_msg_set);
        mContext = this;
        initToolbar();
        initView();
    }


    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("聊天设置");
        ll_report = (LinearLayout) findViewById(R.id.ll_report);
        ll_report.setOnClickListener(this);
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
            case R.id.ll_report:
                startActivity(
                        new Intent(mContext, PartnerMsgReportActivity.class));
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
        }
    }
}

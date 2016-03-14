package app.nahehuo.com.ui.partner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import app.nahehuo.com.R;

/**
 * Created by WYB on 2016/2/23.
 */
public class PartnerSwiLevelActivity extends AppCompatActivity
        implements View.OnClickListener {

    private Toolbar mToolbar;
    private TextView tv_title;
    private Context mContext;
    private LinearLayout ll_common, ll_advance, ll_master, ll_common_extra,
            ll_advance_extra, ll_master_extra;
    private TextView tv_temp;
    private TextView tv_one, tv_two, tv_three;
    private LinearLayout ll_temp;
    private Button btn_pay;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_sw_level);
        mContext = this;
        initToolbar();
        initView();
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
        tv_title.setText("选择合伙人等级");
        ll_common = (LinearLayout) findViewById(R.id.ll_common);
        ll_advance = (LinearLayout) findViewById(R.id.ll_advance);
        ll_master = (LinearLayout) findViewById(R.id.ll_master);
        tv_one = (TextView) findViewById(R.id.tv_one);
        tv_two = (TextView) findViewById(R.id.tv_two);
        tv_three = (TextView) findViewById(R.id.tv_three);
        ll_common_extra = (LinearLayout) findViewById(R.id.ll_common_extra);
        ll_advance_extra = (LinearLayout) findViewById(R.id.ll_advance_extra);
        ll_master_extra = (LinearLayout) findViewById(R.id.ll_master_extra);
        btn_pay = (Button) findViewById(R.id.btn_pay);
        btn_pay.setOnClickListener(this);

        ll_common.setOnClickListener(
                new MyOnclickListner(tv_one, ll_common_extra));
        ll_advance.setOnClickListener(
                new MyOnclickListner(tv_two, ll_advance_extra));
        ll_master.setOnClickListener(
                new MyOnclickListner(tv_three, ll_master_extra));
        tv_temp = tv_one;
        ll_temp = ll_common_extra;
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
            case R.id.btn_pay:
                startActivity(
                        new Intent(mContext, PartnerProgressActivity.class));
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
        }
    }


    class MyOnclickListner implements View.OnClickListener {

        TextView tv_To_oval;
        LinearLayout extra;


        public MyOnclickListner(TextView tv_To_oval, LinearLayout extra) {

            this.tv_To_oval = tv_To_oval;
            this.extra = extra;
        }


        @Override public void onClick(View v) {
            ll_temp.setVisibility(View.GONE);
            tv_temp.setBackgroundDrawable(
                    getResources().getDrawable(R.drawable.bg_oval_gray_border));

            extra.setVisibility(View.VISIBLE);
            tv_To_oval.setBackgroundDrawable(
                    getResources().getDrawable(R.drawable.bg_oval_blue_small));
            tv_temp = tv_To_oval;
            ll_temp = extra;
        }
    }
}

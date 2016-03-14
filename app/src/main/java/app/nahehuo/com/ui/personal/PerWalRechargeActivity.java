package app.nahehuo.com.ui.personal;

import android.content.Context;
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
 * Created by wyb on 2016/3/1.
 */
public class PerWalRechargeActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView tv_title;
    private TextView tv_wxpay, tv_alipay;
    private TextView tv_temp;
    private LinearLayout ll_wxpay, ll_alipay;
    private Context mContext;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per_wal_recharge);
        mContext = this;
        initView();
        initToolbar();
    }


    private void initView() {
        ll_wxpay = (LinearLayout) findViewById(R.id.ll_wxpay);
        ll_alipay = (LinearLayout) findViewById(R.id.ll_alipay);

        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("钱包充值");
        tv_wxpay = (TextView) findViewById(R.id.tv_wxpay);
        tv_alipay = (TextView) findViewById(R.id.tv_alipay);

        ll_wxpay.setOnClickListener(new MyOnclickListner(tv_wxpay));
        ll_alipay.setOnClickListener(new MyOnclickListner(tv_alipay));
        tv_temp = tv_wxpay;
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


    class MyOnclickListner implements View.OnClickListener {

        TextView tv_To_oval;


        public MyOnclickListner(TextView tv_To_oval) {

            this.tv_To_oval = tv_To_oval;
        }


        @Override public void onClick(View v) {
            tv_temp.setBackgroundDrawable(
                    getResources().getDrawable(R.drawable.bg_oval_gray_border));

            tv_To_oval.setBackgroundDrawable(
                    getResources().getDrawable(R.drawable.bg_oval_blue_small));
            tv_temp = tv_To_oval;
        }
    }
}

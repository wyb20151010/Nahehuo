package app.nahehuo.com.ui.event;

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

/**
 * Created by WYB on 2016/1/30.
 */
public class EventPayActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView tv_title, tv_alipay, tv_alipay_money, tv_walletpay,
            tv_walletpay_money, tv_wxpay, tv_wxpay_money;
    private LinearLayout ll_alipay, ll_walletpay, ll_wxpay;
    private RelativeLayout rl_alipay, rl_walletpay, rl_wxpay;
    private TextView temp1, temp2, temp3;
    private float alipay_money, walletpay_money, wxpay_money, good_money;
    private TextView tv_one, tv_two, tv_three;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_pay);
        initActionBar();
        good_money = 1000f;
        walletpay_money = 6000.55f;

        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("核对并支付");
        ll_alipay = (LinearLayout) findViewById(R.id.ll_alipay);
        ll_walletpay = (LinearLayout) findViewById(R.id.ll_walletpay);
        ll_wxpay = (LinearLayout) findViewById(R.id.ll_wxpay);

        tv_alipay = (TextView) findViewById(R.id.tv_alipay);
        tv_walletpay = (TextView) findViewById(R.id.tv_walletpay);
        tv_wxpay = (TextView) findViewById(R.id.tv_wxpay);

        tv_alipay_money = (TextView) findViewById(R.id.tv_alipay_money);
        tv_walletpay_money = (TextView) findViewById(R.id.tv_walletpay_money);
        tv_wxpay_money = (TextView) findViewById(R.id.tv_wxpay_money);
        tv_alipay_money.setText("当前金额: " + alipay_money);
        tv_walletpay_money.setText("当前金额: " + walletpay_money);
        tv_wxpay_money.setText("当前金额: " + wxpay_money);

        rl_alipay = (RelativeLayout) findViewById(R.id.rl_alipay);
        rl_walletpay = (RelativeLayout) findViewById(R.id.rl_walletpay);
        rl_wxpay = (RelativeLayout) findViewById(R.id.rl_wxpay);

        tv_one = (TextView) findViewById(R.id.tv_one);
        tv_two = (TextView) findViewById(R.id.tv_two);
        tv_three = (TextView) findViewById(R.id.tv_three);
        temp3 = tv_one;
        if (walletpay_money < good_money) {
            ll_walletpay.setBackgroundColor(
                    getResources().getColor(R.color.linecolorgray));
            tv_one.setVisibility(View.GONE);
        }
        else {
            tv_one.setVisibility(View.VISIBLE);
            tv_one.setBackgroundDrawable(
                    getResources().getDrawable(R.drawable.bg_oval_gray_border));
            tv_walletpay_money.setVisibility(View.GONE);
            RelativeLayout.LayoutParams params2
                    = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT, 50);
            params2.addRule(RelativeLayout.CENTER_VERTICAL);
            tv_walletpay.setLayoutParams(params2);
            ll_walletpay.setOnClickListener(new MyOnclickListner(tv_one));
        }

        ll_alipay.setOnClickListener(new MyOnclickListner(tv_three));
        ll_wxpay.setOnClickListener(new MyOnclickListner(tv_two));
    }


    private void initActionBar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    class MyOnclickListner implements View.OnClickListener {

        TextView tv_To_oval;


        public MyOnclickListner(TextView tv_To_oval) {

            this.tv_To_oval = tv_To_oval;
        }


        @Override public void onClick(View v) {
            temp3.setBackgroundDrawable(
                    getResources().getDrawable(R.drawable.bg_oval_gray_border));
            tv_To_oval.setBackgroundDrawable(
                    getResources().getDrawable(R.drawable.bg_oval_blue_small));
            temp3 = tv_To_oval;
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
}

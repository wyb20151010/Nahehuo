package app.nahehuo.com.ui.partner;

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
 * Created by WYB on 2016/2/24.
 */
public class PartnerMsgReportActivity extends AppCompatActivity  {

    protected Toolbar mToolbar;
    protected TextView tv_title;
    private Context mContext;
    private LinearLayout ll_one, ll_two, ll_three, ll_four, ll_five;
    private TextView tv_one, tv_two, tv_three, tv_four, tv_five;
    private TextView tv_temp;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_msg_report);
        mContext = this;
        initToolbar();
        initView();
    }


    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("举报");
        ll_one= (LinearLayout) findViewById(R.id.ll_one);
        ll_two= (LinearLayout) findViewById(R.id.ll_two);
        ll_three= (LinearLayout) findViewById(R.id.ll_three);
        ll_four= (LinearLayout) findViewById(R.id.ll_four);
        ll_five= (LinearLayout) findViewById(R.id.ll_five);
        tv_one= (TextView) findViewById(R.id.tv_one);
        tv_two= (TextView) findViewById(R.id.tv_two);
        tv_three= (TextView) findViewById(R.id.tv_three);
        tv_four= (TextView) findViewById(R.id.tv_four);
        tv_five= (TextView) findViewById(R.id.tv_five);
        ll_one.setOnClickListener(new MyOnclickListner(tv_one));
        ll_two.setOnClickListener(new MyOnclickListner(tv_two));
        ll_three.setOnClickListener(new MyOnclickListner(tv_three));
        ll_four.setOnClickListener(new MyOnclickListner(tv_four));
        ll_five.setOnClickListener(new MyOnclickListner(tv_five));
        tv_temp = tv_one;
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

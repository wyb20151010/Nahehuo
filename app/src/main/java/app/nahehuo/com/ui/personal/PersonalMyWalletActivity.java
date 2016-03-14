package app.nahehuo.com.ui.personal;

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
import app.nahehuo.com.adapter.PersonalPayAdapter;
import app.nahehuo.com.bean.PersonalPay;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyb on 2016/3/1.
 */
public class PersonalMyWalletActivity extends AppCompatActivity
        implements View.OnClickListener {

    private Toolbar mToolbar;
    private TextView tv_title;
    private PullToRefreshListView plv_record;
    private List<PersonalPay> mPersonalPays = new ArrayList<>();
    private Context mContext;
    private PersonalPayAdapter mPersonalPayAdapter;
    private Button btn_recharge, btn_draw_cash, btn_bank_card;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per_my_wallet);
        mContext = this;
        initData();
        initView();
        initToolbar();
    }


    private void initData() {

        mPersonalPays.add(
                new PersonalPay("红包获取", "2", "+15.33", "2015-02-14 17:23"));
        mPersonalPays.add(
                new PersonalPay("提现", "1", "-15.33", "2015-02-14 17:23"));
        mPersonalPays.add(
                new PersonalPay("红包获取", "2", "+15.33", "2015-02-14 17:23"));
    }


    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("我的钱包");

        mPersonalPayAdapter = new PersonalPayAdapter(mPersonalPays, mContext);
        plv_record = (PullToRefreshListView) findViewById(R.id.plv_record);
        plv_record.setMode(PullToRefreshBase.Mode.BOTH);
        plv_record.setAdapter(mPersonalPayAdapter);

        btn_recharge = (Button) findViewById(R.id.btn_recharge);
        btn_recharge.setOnClickListener(this);
        btn_draw_cash = (Button) findViewById(R.id.btn_draw_cash);
        btn_draw_cash.setOnClickListener(this);
        btn_bank_card = (Button) findViewById(R.id.btn_bank_card);
        btn_bank_card.setOnClickListener(this);
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
            case R.id.btn_recharge:
                startActivity(
                        new Intent(mContext, PerWalRechargeActivity.class));
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.btn_draw_cash:
                startActivity(
                        new Intent(mContext, PerWalDrawCashActivity.class));
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.btn_bank_card:
                startActivity(
                        new Intent(mContext, PerWalNoBankCardActivity.class));
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
        }
    }
}

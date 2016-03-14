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
import android.widget.LinearLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.ui.personal.popup.EnterPwdPopup;

/**
 * Created by WYB on 2016/3/1.
 */
public class PerWalDrawCashActivity extends AppCompatActivity
        implements View.OnClickListener {

    private Toolbar mToolbar;
    private TextView tv_title;
    private Context mContext;
    private LinearLayout ll_other_bank;
    private Button btn_draw_cash;
    private EnterPwdPopup mEnterPwdPopup;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per_draw_cash);
        mContext = this;
        initView();
        initToolbar();
    }


    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("余额提现");
        ll_other_bank = (LinearLayout) findViewById(R.id.ll_other_bank);
        ll_other_bank.setOnClickListener(this);
        btn_draw_cash = (Button) findViewById(R.id.btn_draw_cash);
        btn_draw_cash.setOnClickListener(this);
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
            case R.id.ll_other_bank:
                startActivity(
                        new Intent(mContext, PerWalBankCardListActivity.class));
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.btn_draw_cash:
                mEnterPwdPopup = new EnterPwdPopup(this);
                mEnterPwdPopup.showPopupWindow();
                mEnterPwdPopup.setListenter(
                        new EnterPwdPopup.OnClickListenter() {
                            @Override public void setOnOK(String pwd) {
                                //TODO 密码比较
                                startActivity(new Intent(mContext,
                                        PerWalDrawCashSuActivity.class));
                                mEnterPwdPopup.clearEditText();
                                mEnterPwdPopup.dismiss();
                            }


                            @Override public void setOnforgetPwd() {
                                startActivity(new Intent(mContext,
                                        PerWalSetPayPwdActivity.class));
                                overridePendingTransition(R.anim.push_left_in,
                                        R.anim.push_left_out);
                                mEnterPwdPopup.clearEditText();
                                mEnterPwdPopup.dismiss();
                            }
                        });
                break;
        }
    }
}

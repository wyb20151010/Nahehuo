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
import app.nahehuo.com.ui.personal.popup.BinderBankCardPopup;

/**
 * Created by wyb on 2016/3/1.
 */
public class PerWalAddBankCard2Activity extends AppCompatActivity
        implements View.OnClickListener {

    private Toolbar mToolbar;
    private TextView tv_title;
    private Context mContext;
    private Button btn_next;
    private BinderBankCardPopup mCardPopup;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per_wal_add_card_two_step);
        mContext = this;
        initView();
        initToolbar();
    }


    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("添加银行卡");
        btn_next = (Button) findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);
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
            case R.id.btn_next:
                mCardPopup = new BinderBankCardPopup(this);
                mCardPopup.showPopupWindow();
                mCardPopup.setCallBack(
                        new BinderBankCardPopup.SetOnClickCallBack() {
                            @Override public void setSave() {
                                startActivity(new Intent(mContext,
                                        PerWalBankCardListActivity.class));
                                overridePendingTransition(R.anim.push_left_in,
                                        R.anim.push_left_out);
                                mCardPopup.dismiss();
                            }


                            @Override public void setCancel() {
                                mCardPopup.dismiss();
                            }
                        });
                break;
        }
    }
}

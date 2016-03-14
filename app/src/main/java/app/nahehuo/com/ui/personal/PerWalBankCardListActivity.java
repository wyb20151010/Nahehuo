package app.nahehuo.com.ui.personal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.PerWalBankCardAdapter;
import app.nahehuo.com.bean.BankCard;
import app.nahehuo.com.ui.personal.popup.EnterPwdPopup;
import app.nahehuo.com.ui.personal.popup.SetPayPwdPopup;
import app.nahehuo.com.ui.personal.popup.UnBinder4BottomPopup;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/3/1.
 */
public class PerWalBankCardListActivity extends AppCompatActivity
        implements View.OnClickListener {

    private Toolbar mToolbar;
    private TextView tv_title;
    private Context mContext;
    private ListView lv_bank_card;
    private PerWalBankCardAdapter mCardAdapter;
    private List<BankCard> mCardList = new ArrayList<>();
    private UnBinder4BottomPopup mUnBinder4BottomPopup;
    private boolean isSettingCardPwd = true;
    private SetPayPwdPopup mSetPayPwdPopup;
    private EnterPwdPopup mEnterPwdPopup;
    private Button btn_add_card;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per_wallet_card_list);
        mContext = this;
        initData();
        mUnBinder4BottomPopup = new UnBinder4BottomPopup(this);
        mSetPayPwdPopup = new SetPayPwdPopup(this);
        mEnterPwdPopup = new EnterPwdPopup(this);
        initView();
        initToolbar();
    }


    private void initData() {
        mCardList.add(new BankCard("蜡笔小新", "中国工商银行 (**** **** **** 5209)"));
        mCardList.add(new BankCard("李洛克", "中国工商银行 (**** **** **** 5209)"));
        mCardList.add(new BankCard("李洛克", "中国工商银行 (**** **** **** 5209)"));
        mCardList.add(new BankCard("李洛克", "中国工商银行 (**** **** **** 5209)"));
        mCardList.add(new BankCard("蜡笔小新", "中国工商银行 (**** **** **** 5209)"));
        mCardList.add(new BankCard("李洛克", "中国工商银行 (**** **** **** 5209)"));
        mCardList.add(new BankCard("李洛克", "中国工商银行 (**** **** **** 5209)"));
        mCardList.add(new BankCard("李洛克", "中国工商银行 (**** **** **** 5209)"));
        mCardList.add(new BankCard("蜡笔小新", "中国工商银行 (**** **** **** 5209)"));
        mCardList.add(new BankCard("李洛克", "中国工商银行 (**** **** **** 5209)"));
        mCardList.add(new BankCard("李洛克", "中国工商银行 (**** **** **** 5209)"));
        mCardList.add(new BankCard("李洛克", "中国工商银行 (**** **** **** 5209)"));
    }


    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("我的银行卡");
        btn_add_card = (Button) findViewById(R.id.btn_add_card);
        btn_add_card.setOnClickListener(this);

        mCardAdapter = new PerWalBankCardAdapter(mCardList, mContext);
        lv_bank_card = (ListView) findViewById(R.id.lv_bank_card);
        lv_bank_card.setAdapter(mCardAdapter);
        lv_bank_card.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mUnBinder4BottomPopup.showPopupWindow();
                        mUnBinder4BottomPopup.setSetOnItemClickListener(
                                new UnBinder4BottomPopup.SetOnItemClickListener() {
                                    @Override
                                    public void setUnbinderBankCard() {
                                        if (isSettingCardPwd) {
                                            //TODO 解绑 输入支付密码
                                            mEnterPwdPopup.showPopupWindow();
                                            mEnterPwdPopup.setListenter(
                                                    new EnterPwdPopup.OnClickListenter() {
                                                        @Override
                                                        public void setOnOK(String pwd) {
                                                            Log.d("TAG", pwd);
                                                            //TODO 比较密码
                                                            mEnterPwdPopup.clearEditText();
                                                            mEnterPwdPopup.dismiss();
                                                        }


                                                        @Override
                                                        public void setOnforgetPwd() {
                                                            //TODO 设置支付密码
                                                            startActivity(
                                                                    new Intent(
                                                                            mContext,
                                                                            PerWalSetPayPwdActivity.class));
                                                            overridePendingTransition(
                                                                    R.anim.push_left_in,
                                                                    R.anim.push_left_out);
                                                            mEnterPwdPopup.clearEditText();
                                                            mEnterPwdPopup.dismiss();
                                                        }
                                                    });
                                        }
                                        else {
                                            mSetPayPwdPopup.showPopupWindow();
                                            mSetPayPwdPopup.setCallBack(
                                                    new SetPayPwdPopup.SetOnClickCallBack() {
                                                        @Override
                                                        public void setSave() {
                                                            startActivity(
                                                                    new Intent(
                                                                            mContext,
                                                                            PerWalSetPayPwdActivity.class));
                                                            overridePendingTransition(
                                                                    R.anim.push_left_in,
                                                                    R.anim.push_left_out);
                                                            mSetPayPwdPopup.dismiss();
                                                        }


                                                        @Override
                                                        public void setCancel() {
                                                            mSetPayPwdPopup.dismiss();
                                                        }
                                                    });
                                        }
                                        mUnBinder4BottomPopup.dismiss();
                                    }


                                    @Override public void setDismiss() {
                                        mUnBinder4BottomPopup.dismiss();
                                    }
                                });
                    }
                });
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
            case R.id.btn_add_card:
                startActivity(
                        new Intent(mContext, PerWalAddBankCardActivity.class));
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
        }
    }
}

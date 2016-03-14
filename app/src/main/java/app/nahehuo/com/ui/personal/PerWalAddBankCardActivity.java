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
import android.widget.EditText;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.ui.partner.popup.IDShowPopup;
import app.nahehuo.com.util.TextUtil;

/**
 * Created by wyb on 2016/3/1.
 */
public class PerWalAddBankCardActivity extends AppCompatActivity
        implements View.OnClickListener {

    private Toolbar mToolbar;
    private TextView tv_title;
    private Context mContext;
    private EditText et_card_number;
    private Button btn_next;
    private IDShowPopup mIDShowPopup;
    private TextView tv_expand;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per_wal_add_card);
        mContext = this;
        initView();
        initToolbar();
    }


    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("添加银行卡");
        et_card_number = (EditText) findViewById(R.id.et_card_number);
        tv_expand = (TextView) findViewById(R.id.tv_expand);
        btn_next = (Button) findViewById(R.id.btn_next);

        tv_expand.setOnClickListener(this);
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
            case R.id.tv_expand:
                if (!TextUtil.isEmpty(et_card_number.getText().toString())) {
                    mIDShowPopup = new IDShowPopup(this,
                            et_card_number.getText().toString());
                    mIDShowPopup.showPopupWindow();
                }

                break;
            case R.id.btn_next:
                startActivity(
                        new Intent(mContext, PerWalAddBankCard2Activity.class));
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
        }
    }
}

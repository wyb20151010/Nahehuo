package app.nahehuo.com.ui.partner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Contacts;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.ui.partner.popup.QRCodePopup;
import app.nahehuo.com.util.TextUtil;

/**
 * Created by WYB on 2016/2/17.
 */
public class PartnerSeaActivity extends AppCompatActivity
        implements View.OnClickListener {

    private Toolbar mToolbar;
    private TextView tv_title;
    private RelativeLayout rl_two_demin;
    private QRCodePopup mTwoDimenPopup;
    private EditText et_searchPartner;
    private Context mContext;
    private LinearLayout ll_phone;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sea_partner);
        mContext = this;
        initToolbar();
        initView();
    }


    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("找合伙人");
        rl_two_demin = (RelativeLayout) findViewById(R.id.rl_two_demin);
        rl_two_demin.setOnClickListener(this);
        et_searchPartner = (EditText) findViewById(R.id.et_searchPartner);
        et_searchPartner.setOnEditorActionListener(
                new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH &&
                                !TextUtil.isEmpty(et_searchPartner.getText()
                                                                  .toString())) {
                            Intent intent = new Intent(PartnerSeaActivity.this,
                                    PartnerSeaDeActivity.class);
                            intent.putExtra("searchdata",
                                    et_searchPartner.getText().toString());
                            startActivity(intent);
                            return true;
                        }
                        return false;
                    }
                });
        ll_phone= (LinearLayout) findViewById(R.id.ll_phone);
        ll_phone.setOnClickListener(this);
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
            case R.id.rl_two_demin:
                mTwoDimenPopup = new QRCodePopup(this, "王小二", "PHP开发工程师",
                        "哪合伙ID: n2333");
                mTwoDimenPopup.showPopupWindow();
                break;
            case R.id.ll_phone:
                Intent intent = new Intent();

                intent.setAction(Intent.ACTION_VIEW);

                intent.setData(Contacts.People.CONTENT_URI);

                startActivity(intent);
                break;
        }
    }
}

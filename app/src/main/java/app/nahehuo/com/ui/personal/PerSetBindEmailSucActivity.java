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
import app.nahehuo.com.util.TextUtil;

/**
 * Created by wyb on 2016/3/4.
 */
public class PerSetBindEmailSucActivity extends AppCompatActivity
        implements View.OnClickListener {

    private Toolbar mToolbar;
    private TextView tv_title;
    private Context mContext;
    private Button btn_ok;
    private Intent mIntent;
    private String email;
    private TextView tv_sub_title;
    private StringBuffer sb;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per_bind_email_suc);
        mContext = this;
        mIntent = getIntent();
        email = mIntent.getStringExtra("email");
        initView();
        initToolbar();
    }


    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("绑定成功");
        btn_ok = (Button) findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(this);
        tv_sub_title = (TextView) findViewById(R.id.tv_sub_title);
        sb = new StringBuffer();
        if (!TextUtil.isEmpty(email)) {
            sb.append("验证邮件已发送到邮箱");
            sb.append(email);
            sb.append(",请查收并验证邮箱");
            tv_sub_title.setText(sb.toString());
        }
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
            case R.id.btn_ok:
                startActivity(
                        new Intent(mContext, PerSetMyAccountActivity.class));
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
        }
    }
}

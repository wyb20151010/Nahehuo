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
import app.nahehuo.com.util.TextUtil;

/**
 * Created by wyb on 2016/3/4.
 */
public class PerSetBindEmailActivity extends AppCompatActivity
        implements View.OnClickListener {

    private Toolbar mToolbar;
    private TextView tv_title;
    private Context mContext;
    private Button btn_ok;
    private EditText et_email;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per_bind_email);
        mContext = this;
        initView();
        initToolbar();
    }


    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("绑定邮箱账号");
        btn_ok = (Button) findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(this);
        et_email= (EditText) findViewById(R.id.et_email);
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
                if(!TextUtil.isEmpty(et_email.getText().toString())){
                    Intent intent = new Intent(mContext,
                            PerSetBindEmailSucActivity.class);
                    intent.putExtra("email",et_email.getText().toString());
                    startActivity(intent);
                    overridePendingTransition(R.anim.push_left_in,
                            R.anim.push_left_out);
                }

                break;
        }
    }
}

package app.nahehuo.com.ui.personal;

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
 * Created by WYB on 2016/2/29.
 */
public class PerInfoIntroActivity extends AppCompatActivity {
    public static final int RESULT_OK = 1;
    private Toolbar mToolbar;
    private TextView tv_title;

    private EditText et_intro;
    private Button btn_login;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per_intro);
        initView();
        initToolbar();
    }


    private void initView() {

        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("一句话介绍自己");
        et_intro = (EditText) findViewById(R.id.et_intro);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (!TextUtil.isEmpty(et_intro.getText().toString())) {
                    Intent intent = new Intent();
                    intent.putExtra("data_return",
                            et_intro.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }
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
}

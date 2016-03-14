package app.nahehuo.com.ui.event;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.util.ShowToast;
import app.nahehuo.com.util.TextUtil;

/**
 * Created by WYB on 2016/1/27.
 */
public class EventWriteIntroActivity extends AppCompatActivity implements View.OnClickListener{
    protected Toolbar mToolbar;
    private EditText et_pro_write;
    private TextView tv_title;
    public static final int RESULT_OK = 2;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_write_intro);
        initToolBar();
        tv_title = (TextView) findViewById(R.id.tv_title);
        et_pro_write = (EditText) findViewById(R.id.et_pro_write);
        tv_title.setOnClickListener(this);
    }


    private void initToolBar() {
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
            case R.id.tv_title:
                if (!TextUtil.isEmpty(et_pro_write.getText().toString())) {
                    Intent intent = new Intent();
                    intent.putExtra("desc", et_pro_write.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }
                else {
                    ShowToast.Short("添点东西再保存吧(⊙o⊙)…");
                }
                break;
        }
    }
}

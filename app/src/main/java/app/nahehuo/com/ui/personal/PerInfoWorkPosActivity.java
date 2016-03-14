package app.nahehuo.com.ui.personal;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import app.nahehuo.com.R;

/**
 * Created by WYB on 2016/2/29.
 */
public class PerInfoWorkPosActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView tv_title;
    private static final int RESULT_OK = 1;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per_wok_pos);
        initView();
        initToolbar();
        //TODO
        /*Intent intent = new Intent();
        intent.putExtra("data_return", "test");
        setResult(RESULT_OK, intent);
        finish();*/
    }


    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("选择工作地点");
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

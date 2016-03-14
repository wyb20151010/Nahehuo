package app.nahehuo.com.ui.personal;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import app.nahehuo.com.R;

/**
 * Created by Administrator on 2016/3/1.
 */
public class PerInfoWantActivity extends AppCompatActivity{

    private Toolbar mToolbar;
    private TextView tv_title;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_we_want);
        initView();
        initToolbar();
    }

    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("我想得到");
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


    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save, menu);
        return super.onCreateOptionsMenu(menu);
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

package app.nahehuo.com.ui.partner;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import app.nahehuo.com.R;

/**
 * Created by WYB on 2016/2/25.
 */
public class PartnerComFriActivity extends AppCompatActivity {

    protected Toolbar mToolbar;
    protected TextView tv_title;
    private Context mContext;
    private ListView lv_com_fri;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_com_fri);
        mContext = this;
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("共同好友");
        initToolbar();
        lv_com_fri= (ListView) findViewById(R.id.lv_com_fri);
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

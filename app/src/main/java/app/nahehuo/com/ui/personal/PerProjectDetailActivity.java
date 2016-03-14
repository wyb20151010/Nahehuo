package app.nahehuo.com.ui.personal;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.PerProjectDetailAdapter;
import app.nahehuo.com.bean.PerProjectDetail;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyb on 2016/3/4.
 */
public class PerProjectDetailActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView tv_title;
    private Context mContext;
    private PullToRefreshListView plv_person;
    private List<PerProjectDetail> mPerProjectDetails = new ArrayList<>();
    private PerProjectDetailAdapter mAdapter;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per_project_detail);
        mContext = this;
        initData();
        initView();
        initToolbar();
    }


    private void initData() {
        mPerProjectDetails.add(
                new PerProjectDetail("王塬鑫", "Android开发", "13915170255",
                        "46552222@qq.com"));
        mPerProjectDetails.add(
                new PerProjectDetail("王塬鑫", "Android开发", "13915170255",
                        "46552222@qq.com"));
        mPerProjectDetails.add(
                new PerProjectDetail("王塬鑫", "Android开发", "13915170255",
                        "46552222@qq.com"));
    }


    private void initView() {

        mAdapter = new PerProjectDetailAdapter(mPerProjectDetails, mContext);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("约谈目录");
        plv_person = (PullToRefreshListView) findViewById(R.id.plv_person);
        plv_person.setAdapter(mAdapter);
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

package app.nahehuo.com.ui.personal;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.PerEventDetailAdapter;
import app.nahehuo.com.bean.PersonEvent;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyb on 2016/3/4.
 */
public class PerEventDetailActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView tv_title;
    private Context mContext;
    private PullToRefreshListView plv_person;
    private PerEventDetailAdapter mAdapter;
    private List<PersonEvent> mPersonEvents = new ArrayList<>();


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per_event_detail);
        mContext = this;
        initData();
        initView();
        initToolbar();
    }


    private void initData() {
        mPersonEvents.add(new PersonEvent("王桧", "android高级工程师"));
        mPersonEvents.add(new PersonEvent("王桧", "android高级工程师"));
        mPersonEvents.add(new PersonEvent("王桧", "IOS高级工程师"));
        mPersonEvents.add(new PersonEvent("王桧", "IOS高级工程师"));
        mPersonEvents.add(new PersonEvent("黎明", "android高级工程师"));
    }


    private void initView() {
        mAdapter = new PerEventDetailAdapter(mPersonEvents, mContext);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("报名管理");
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


    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.per_event, menu);
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

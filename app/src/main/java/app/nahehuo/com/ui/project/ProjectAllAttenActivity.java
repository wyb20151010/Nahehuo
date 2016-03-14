package app.nahehuo.com.ui.project;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.AttentationAdapter;
import app.nahehuo.com.bean.ProjectPersonDict;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/1/19.
 */
public class ProjectAllAttenActivity extends AppCompatActivity {

    private List<ProjectPersonDict> mPersonDicts = new ArrayList<>();
    private Context mContext;
    private PullToRefreshListView plv_attention;
    private Toolbar mToolbar;
    private TextView tv_title;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_allatten);
        mContext = this;
        initData();
        initPlv();
        initToolBar();
    }


    private void initToolBar() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        tv_title.setText("关注项目的人");
    }


    private void initData() {

        mPersonDicts.add(new ProjectPersonDict("王耀彬", "后台产品经理 - 今翌信息科技（上海）有限公司",
                "http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg"));
        mPersonDicts.add(new ProjectPersonDict("王浩", "后台产品经理 - 今翌信息科技（上海）有限公司",
                "http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg"));
        mPersonDicts.add(new ProjectPersonDict("王耀彬", "后台产品经理 - 今翌信息科技（上海）有限公司",
                "http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg"));
        mPersonDicts.add(new ProjectPersonDict("王浩", "后台产品经理 - 今翌信息科技（上海）有限公司",
                "http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg"));
        mPersonDicts.add(new ProjectPersonDict("王耀彬", "后台产品经理 - 今翌信息科技（上海）有限公司",
                "http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg"));
        mPersonDicts.add(new ProjectPersonDict("王浩", "后台产品经理 - 今翌信息科技（上海）有限公司",
                "http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg"));
        mPersonDicts.add(new ProjectPersonDict("王耀彬", "后台产品经理 - 今翌信息科技（上海）有限公司",
                "http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg"));
    }


    private void initPlv() {
        AttentationAdapter adapter = new AttentationAdapter(mPersonDicts,
                mContext);
        plv_attention = (PullToRefreshListView) findViewById(
                R.id.plv_all_atten);
        plv_attention.setMode(PullToRefreshBase.Mode.BOTH);
        plv_attention.setAdapter(adapter);
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

package app.nahehuo.com.ui.personal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.PersonalWkExpAdapter;
import app.nahehuo.com.bean.WorkExp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/29.
 */
public class PerInfoWkExpListActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView tv_title;
    private ListView lv_work_exp;
    private List<WorkExp> mWorkExps = new ArrayList<>();
    private Context mContext;
    private PersonalWkExpAdapter mPersonalWkExpAdapter;

    private String type;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per_wk_exp_list);
        mContext = this;
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        initData();
        initView();
        initToolbar();
    }


    private void initData() {
        if ("1".equals(type)) {
            mWorkExps.add(
                    new WorkExp("2014.3-至今", "IOS", "今翌信息科技(上海)有限公司", "1"));
            mWorkExps.add(
                    new WorkExp("2014.3-至今", "高级产品经理", "今翌信息科技(上海)有限公司", "1"));
            mWorkExps.add(
                    new WorkExp("2014.3-至今", "Android", "今翌信息科技(上海)有限公司", "1"));
            mWorkExps.add(
                    new WorkExp("2014.3-至今", "高级产品经理", "今翌信息科技(上海)有限公司", "1"));
        }
        else if ("2".equals(type)) {
            mWorkExps.add(new WorkExp("2014.3-至今", "计算机科学与技术", "清华大学", "2"));
            mWorkExps.add(new WorkExp("2014.3-至今", "心理学", "哈弗大学", "2"));
            mWorkExps.add(new WorkExp("2014.3-至今", "计算机科学与技术", "华东理工大学", "2"));
        }
    }


    private void initView() {
        mPersonalWkExpAdapter = new PersonalWkExpAdapter(mWorkExps, mContext);
        tv_title = (TextView) findViewById(R.id.tv_title);
        if ("1".equals(type)) {
            tv_title.setText("工作经历");
        }
        else if ("2".equals(type)) {
            tv_title.setText("教育经历");
        }
        lv_work_exp = (ListView) findViewById(R.id.lv_work_exp);
        lv_work_exp.setAdapter(mPersonalWkExpAdapter);
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
        getMenuInflater().inflate(R.menu.add, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.add:
                if ("1".equals(type)) {
                    startActivity(new Intent(mContext,
                            PerInfoWkExpDetailActivity.class));
                }
                else if ("2".equals(type)) {
                    startActivity(new Intent(mContext,
                            PerInfoEduExpDetailActivity.class));
                }

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

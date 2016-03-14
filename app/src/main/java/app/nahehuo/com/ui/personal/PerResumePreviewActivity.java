package app.nahehuo.com.ui.personal;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.PerMyResAdapter;
import app.nahehuo.com.bean.PersonWorkExp;
import app.nahehuo.com.view.MyListView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyb on 2016/3/3.
 */
public class PerResumePreviewActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView tv_title;
    private Context mContext;
    private MyListView mlv_work_exp, mlv_edu_exp;
    private List<PersonWorkExp> mPersonWorkExps = new ArrayList<>();
    private PerMyResAdapter mPerMyResAdapter;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per_my_resu_detail);
        mContext = this;
        initData();
        initView();
        initToolbar();
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


    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("简历预览");

        mPerMyResAdapter = new PerMyResAdapter(mPersonWorkExps, mContext);
        mlv_work_exp = (MyListView) findViewById(R.id.mlv_work_exp);
        mlv_edu_exp = (MyListView) findViewById(R.id.mlv_edu_exp);
        mlv_work_exp.setAdapter(mPerMyResAdapter);
        mlv_edu_exp.setAdapter(mPerMyResAdapter);
    }


    private void initData() {
        mPersonWorkExps.add(
                new PersonWorkExp("2014.3-至今", "高级产品经理", "今翌信息科技有限"));
        mPersonWorkExps.add(
                new PersonWorkExp("2014.3-至今", "高级产品经理", "今翌信息科技有限"));
        mPersonWorkExps.add(
                new PersonWorkExp("2014.3-至今", "高级产品经理", "今翌信息科技有限"));
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

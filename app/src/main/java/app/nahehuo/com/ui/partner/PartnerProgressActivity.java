package app.nahehuo.com.ui.partner;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.ProjectProgressAdapter;
import app.nahehuo.com.bean.TestData;
import app.nahehuo.com.view.MyListView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/2/23.
 */
public class PartnerProgressActivity extends AppCompatActivity {

    protected Toolbar mToolbar;
    protected TextView tv_title;
    private MyListView plv_progress;
    private List<TestData> TestDatas = new ArrayList<>();
    private Context mContext;
    private ProjectProgressAdapter mAdapter;
    private TextView tv_one, tv_two, tv_three;
    private int status = 1;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_progress);
        mContext=this;
        initToolbar();
        initData();
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("合伙人审核进度");
        mAdapter = new ProjectProgressAdapter(TestDatas, mContext);
        plv_progress = (MyListView) findViewById(R.id.plv_progress);
        plv_progress.setAdapter(mAdapter);
        plv_progress.postInvalidate();
        initProgress();
    }


    private void initProgress() {
        tv_one = (TextView) findViewById(R.id.tv_one);
        tv_two = (TextView) findViewById(R.id.tv_two);
        tv_three = (TextView) findViewById(R.id.tv_three);
        if (status == 1) {
            changeText2OvalBlue(tv_one);
            changeText2OvalBlueBorder(tv_two);
            changeText2OvalBlueBorder(tv_three);
        }
        else if (status == 2) {
            changeText2OvalBlue(tv_one);
            changeText2OvalBlue(tv_two);
            changeText2OvalBlueBorder(tv_three);
        }
        else {
            changeText2OvalBlue(tv_one);
            changeText2OvalBlue(tv_two);
            changeText2OvalBlue(tv_three);
        }
    }


    private void changeText2OvalBlueBorder(TextView tv) {
        tv.setBackgroundDrawable(
                getResources().getDrawable(R.drawable.bg_oval_blue_border));
        tv.setTextColor(getResources().getColor(R.color.jobtitlecolor));
    }


    private void changeText2OvalBlue(TextView tv) {
        tv.setBackgroundDrawable(
                getResources().getDrawable(R.drawable.bg_oval_blue));
        tv.setTextColor(getResources().getColor(R.color.white));
    }


    private void initData() {
        TestData testDataa = new TestData();
        testDataa.setTitle("项目谈成了~");
        testDataa.setContent("2015-12-11 16:30:31");
        TestDatas.add(testDataa);

        TestData testData0 = new TestData();
        testData0.setTitle("已有合伙人发起约谈吧~");
        testData0.setContent("2015-12-11 16:30:31");
        TestDatas.add(testData0);

        TestData testData = new TestData();
        testData.setTitle(
                "你项目审核通过了，公布周期为365天，等待合伙人发起约谈吧快点来人啊我们要融资，我们要风投我们是未来的阿里，是未来的微信，是未来的苹果~");
        testData.setContent("2015-12-11 16:30:31");
        TestDatas.add(testData);

        TestData testData1 = new TestData();
        testData1.setTitle("小二正在审核你的项目，皱着眉头想了很久~");
        testData1.setContent("2015-12-08 18:43:24");
        TestDatas.add(testData1);
        TestData testData2 = new TestData();
        testData2.setTitle("小二查看了你提交的项目，高兴得不得了~");
        testData2.setContent("2015-12-08 18:42:35");
        TestDatas.add(testData2);
    }


    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            setSupportActionBar(mToolbar);
            final ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayShowTitleEnabled(false);
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
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

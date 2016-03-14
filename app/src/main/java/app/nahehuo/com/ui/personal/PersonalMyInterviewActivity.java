package app.nahehuo.com.ui.personal;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.PerMyInterAdapter;
import app.nahehuo.com.bean.MyInter;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyb on 2016/3/2.
 */
public class PersonalMyInterviewActivity extends AppCompatActivity
        implements View.OnClickListener {

    private Toolbar mToolbar;
    private TextView tv_title;
    private Context mContext;
    private TextView temp;
    private LinearLayout ll_cursor;
    private RelativeLayout rl_event_recom, rl_event_time, rl_event_distance,
            rl_not_right;
    private TextView tv_event_recom, tv_event_time, tv_event_distance,
            tv_not_right;
    private int width;
    private PullToRefreshListView plv_my_iv;
    private List<MyInter> mMyInters = new ArrayList<>();
    private PerMyInterAdapter mPerMyInterAdapter;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per_my_iv);
        mContext = this;
        temp = new TextView(mContext);
        WindowManager wm = (WindowManager) getSystemService(
                Context.WINDOW_SERVICE);

        width = wm.getDefaultDisplay().getWidth();
        initData();
        initView();
        initToolbar();
    }


    private void initData() {
        mMyInters.add(
                new MyInter("高级PHP开发工程师", "今翌信息科技有限公司", "20k-30k", "13:03", "1",
                        "已投递"));
        mMyInters.add(
                new MyInter("移动产品经理", "腾讯科技", "20k-30k", "13:03", "2", "已投递"));
        mMyInters.add(
                new MyInter("高级PHP开发工程师", "今翌信息科技有限公司", "20k-30k", "13:03", "3",
                        "已投递"));
        mMyInters.add(
                new MyInter("高级PHP开发工程师", "今翌信息科技有限公司", "20k-30k", "13:03", "4",
                        "已投递"));
        mMyInters.add(
                new MyInter("高级PHP开发工程师", "今翌信息科技有限公司", "20k-30k", "13:03", "1",
                        "已投递"));
        mMyInters.add(
                new MyInter("高级PHP开发工程师", "今翌信息科技有限公司", "20k-30k", "13:03", "1",
                        "已投递"));
    }


    private void initView() {
        mPerMyInterAdapter = new PerMyInterAdapter(mMyInters, mContext);
        plv_my_iv = (PullToRefreshListView) findViewById(R.id.plv_my_iv);
        plv_my_iv.setMode(PullToRefreshBase.Mode.BOTH);
        plv_my_iv.setAdapter(mPerMyInterAdapter);
        ll_cursor = (LinearLayout) findViewById(R.id.ll_cursor);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("我的面试");
        rl_event_recom = (RelativeLayout) findViewById(R.id.rl_event_recom);
        rl_event_time = (RelativeLayout) findViewById(R.id.rl_event_time);
        rl_event_distance = (RelativeLayout) findViewById(
                R.id.rl_event_distance);
        rl_not_right = (RelativeLayout) findViewById(R.id.rl_not_right);
        tv_event_recom = (TextView) findViewById(R.id.tv_event_recom);
        tv_event_time = (TextView) findViewById(R.id.tv_event_time);
        tv_event_distance = (TextView) findViewById(R.id.tv_event_distance);
        tv_not_right = (TextView) findViewById(R.id.tv_not_right);
        rl_event_recom.setOnClickListener(this);
        rl_event_time.setOnClickListener(this);
        rl_event_distance.setOnClickListener(this);
        rl_not_right.setOnClickListener(this);
        fromStateTo(temp, tv_event_recom, 0);

        plv_my_iv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(
                        new Intent(mContext, PerInterviewDetailActivity.class));
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
            }
        });
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


    private void fromStateTo(TextView from, TextView to, int i) {
        TextView tv_to = (TextView) to;
        if (temp != tv_to) {
            temp = tv_to;
            from.setTextColor(getResources().getColor(R.color.textcolorgray));
            tv_to.setTextColor(getResources().getColor(R.color.colorPrimary));
            startAnim(i);
        }
    }


    private void startAnim(int i) {

        ObjectAnimator animator = ObjectAnimator.ofFloat(ll_cursor,
                "translationX", i);
        animator.setDuration(300);
        animator.start();
    }


    @Override public void onClick(View v) {
        switch (v.getId()) {

            case R.id.rl_event_recom:
                fromStateTo(temp, tv_event_recom, 0);
                break;
            case R.id.rl_event_time:
                fromStateTo(temp, tv_event_time, width / 4);
                break;
            case R.id.rl_event_distance:
                fromStateTo(temp, tv_event_distance, width * 2 / 4);
                break;
            case R.id.rl_not_right:
                fromStateTo(temp, tv_not_right, width * 3 / 4);
                break;
        }
    }
}

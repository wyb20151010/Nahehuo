package app.nahehuo.com.ui.partner;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.PartnerAllAdapter;
import app.nahehuo.com.bean.PartnerAll;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/2/19.
 */
public class PartnerAllActivity extends AppCompatActivity
        implements View.OnClickListener {

    private Toolbar mToolbar;
    private TextView tv_title;
    private Context mContext;
    private PullToRefreshListView plv_partner;
    private TextView temp;
    private LinearLayout ll_cursor;
    private RelativeLayout rl_expert, rl_tutor, rl_senior, rl_common;
    private TextView tv_expert, tv_tutor, tv_senior, tv_common;
    private int width;
    private List<PartnerAll> mPartnerAlls = new ArrayList<>();
    private PartnerAllAdapter mAdapter;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_all);
        mContext = this;
        temp = new TextView(mContext);
        WindowManager wm = (WindowManager) getSystemService(
                Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        initToolbar();
        initView();
        initData();
        initPlv();
    }


    private void initData() {
        mPartnerAlls.add(new PartnerAll("小米科技", "项目经理", "1"));
        mPartnerAlls.add(new PartnerAll("腾讯游戏", "开发主管", "2"));
        mPartnerAlls.add(new PartnerAll("小米科技", "项目经理", "3"));
        mPartnerAlls.add(new PartnerAll("小米科技", "项目经理", "2"));
    }


    private void initPlv() {
        mAdapter = new PartnerAllAdapter(mPartnerAlls, mContext);
        plv_partner = (PullToRefreshListView) findViewById(R.id.plv_partner);
        plv_partner.setAdapter(mAdapter);
    }


    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("全部合伙人");
        ll_cursor = (LinearLayout) findViewById(R.id.ll_cursor);
        rl_expert = (RelativeLayout) findViewById(R.id.rl_expert);
        rl_expert.setOnClickListener(this);
        rl_tutor = (RelativeLayout) findViewById(R.id.rl_tutor);
        rl_tutor.setOnClickListener(this);
        rl_senior = (RelativeLayout) findViewById(R.id.rl_senior);
        rl_senior.setOnClickListener(this);
        rl_common = (RelativeLayout) findViewById(R.id.rl_common);
        rl_common.setOnClickListener(this);
        tv_expert = (TextView) findViewById(R.id.tv_expert);
        tv_tutor = (TextView) findViewById(R.id.tv_tutor);
        tv_senior = (TextView) findViewById(R.id.tv_senior);
        tv_common = (TextView) findViewById(R.id.tv_common);
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
            case R.id.rl_expert:
                fromStateTo(temp, tv_expert, 0);
                break;
            case R.id.rl_tutor:
                fromStateTo(temp, tv_tutor, width / 4);
                break;
            case R.id.rl_senior:
                fromStateTo(temp, tv_senior, width * 2 / 4);
                break;
            case R.id.rl_common:
                fromStateTo(temp, tv_common, width * 3 / 4);
                break;
        }
    }
}

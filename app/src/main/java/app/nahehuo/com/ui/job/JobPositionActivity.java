package app.nahehuo.com.ui.job;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.JobListAdapter;
import app.nahehuo.com.base.GlobalVariables;
import app.nahehuo.com.bean.ListJob;
import app.nahehuo.com.bean.NetListJob;
import app.nahehuo.com.network.GsonCallBack;
import app.nahehuo.com.util.MyToast;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhy.http.okhttp.OkHttpUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/1/7.
 */
public class JobPositionActivity extends AppCompatActivity
        implements View.OnClickListener,
        PullToRefreshBase.OnRefreshListener<ListView> {

    protected Toolbar mToolbar;
    protected TextView tv_title;
    private PullToRefreshListView plv_job_list;
    private JobListAdapter mJobListAdapter;
    private Context mContext;

    private List<ListJob> mJobListDict = new ArrayList<>();
    private RelativeLayout rl_job_internship, rl_job_parttime;

    private int pageindex;// 页数
    private static final int JOB_LIST = 0;

    private Handler mHandler = new Handler() {
        @Override public void handleMessage(Message msg) {

            if (msg.what == JOB_LIST) {
                findJobList();
            }
            super.handleMessage(msg);
        }
    };


    private void findJobList() {
        plv_job_list.onRefreshComplete();
        if (pageindex == 1) {
            mJobListDict.clear();
        }
        OkHttpUtils.get()
                   .url(GlobalVariables.JOB_LIST)
                   .addParams("access_token", GlobalVariables.access_token)
                   .addParams("device", GlobalVariables.device)
                   .addParams("pageindex", pageindex + "")
                   .addParams("pagesize", GlobalVariables.pagesize + "")
                   .build()
                   .execute(new GsonCallBack<NetListJob>(NetListJob.class) {
                       @Override public void onResponse(NetListJob response) {
                           if (response.getCode() == 200) {
                               for (int i = 0;
                                    i < response.getData().size();
                                    i++) {
                                   ListJob job = new ListJob();
                                   job.setJid(
                                           response.getData().get(i).getJid());
                                   job.setPosition(response.getData()
                                                           .get(i)
                                                           .getPosition());
                                   job.setType(
                                           response.getData().get(i).getType());
                                   job.setProv(
                                           response.getData().get(i).getProv());
                                   job.setCity(
                                           response.getData().get(i).getCity());
                                   job.setWorkexp(response.getData()
                                                          .get(i)
                                                          .getWorkexp());
                                   job.setWagemin(response.getData()
                                                          .get(i)
                                                          .getWagemin());
                                   job.setWagemax(response.getData()
                                                          .get(i)
                                                          .getWagemax());
                                   job.setEdu(
                                           response.getData().get(i).getEdu());
                                   job.setAttraction(response.getData()
                                                             .get(i)
                                                             .getAttraction());
                                   job.setPublished(response.getData()
                                                            .get(i)
                                                            .getPublished());
                                   job.setCid(
                                           response.getData().get(i).getCid());
                                   job.setCompany(response.getData()
                                                          .get(i)
                                                          .getCompany());
                                   job.setLogo(
                                           response.getData().get(i).getLogo());
                                   job.setCstatus(response.getData()
                                                          .get(i)
                                                          .getCstatus());
                                   job.setFinancle(response.getData()
                                                           .get(i)
                                                           .getFinancle());
                                   job.setWebsite(response.getData()
                                                          .get(i)
                                                          .getWebsite());
                                   job.setPositiontype(response.getData()
                                                               .get(i)
                                                               .getPositiontype());
                                   job.setSize(
                                           response.getData().get(i).getSize());
                                   job.setIndustry(response.getData()
                                                           .get(i)
                                                           .getIndustry());
                                   mJobListDict.add(job);
                               }
                               mJobListAdapter.notifyDataSetChanged();
                           }
                           else {
                               MyToast.showToast(mContext,
                                       response.getMessage());
                           }
                           super.onResponse(response);
                       }
                   });
    }


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_position);
        mContext = this;

        tv_title = (TextView) findViewById(R.id.tv_title);
        initToolbar();
        tv_title.setText("职位");
        initPlv();
        initView();
        pageindex = 1;
        /*if (!TextUtils.isEmpty(GlobalVariables.UID)) {*/
        mHandler.sendEmptyMessage(JOB_LIST);
       /* }
        else {
            startActivity(new Intent(mContext, LoginActivity.class));
            overridePendingTransition(R.anim.push_left_in,
                    R.anim.push_left_out);
        }*/
    }


    private void initView() {
        rl_job_internship = (RelativeLayout) findViewById(
                R.id.rl_job_internship);
        rl_job_internship.setOnClickListener(this);
        rl_job_parttime = (RelativeLayout) findViewById(R.id.rl_job_parttime);
        rl_job_parttime.setOnClickListener(this);
    }


    private void initPlv() {
        plv_job_list = (PullToRefreshListView) findViewById(R.id.plv_job_list);
        plv_job_list.setMode(PullToRefreshBase.Mode.BOTH);
        plv_job_list.setOnRefreshListener(this);
        mJobListAdapter = new JobListAdapter(mContext, mJobListDict);
        plv_job_list.setAdapter(mJobListAdapter);
        plv_job_list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(mContext,
                                JobDetailActivity.class);

                        intent.putExtra("jid",
                                mJobListDict.get(position - 1).getJid() + "");
                        intent.putExtra("position",
                                mJobListDict.get(position - 1).getPosition());
                        startActivity(intent);
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


    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.job, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.refresh:
                startActivity(new Intent(mContext, JobSearchActivity.class));
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_job_internship:
                Intent intent = new Intent(mContext,
                        JobInternOrPartActivity.class);
                intent.putExtra("flag", 1);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.rl_job_parttime:
                Intent intent1 = new Intent(mContext,
                        JobInternOrPartActivity.class);
                intent1.putExtra("flag", 2);
                startActivity(intent1);
                overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
        }
    }


    @Override public void onRefresh(PullToRefreshBase<ListView> refreshView) {

        if (refreshView.isHeaderShown()) {
            pageindex = 1;
            mJobListDict.clear();
            mHandler.sendEmptyMessage(JOB_LIST);
        }
        else if (refreshView.isFooterShown()) {
            pageindex++;
            mHandler.sendEmptyMessage(JOB_LIST);
        }
    }
}

package app.nahehuo.com.ui.job;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.ui.job.fragment.InterviewExpFragment;
import app.nahehuo.com.ui.job.fragment.JobDetailFragment;

/**
 * Created by WYB on 2016/1/7.
 */
public class JobDetailActivity extends AppCompatActivity
        implements View.OnClickListener {

    protected Toolbar mToolbar;
    protected TextView tv_job, tv_company;
    private FrameLayout fl_job_detail;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);
        tv_job = (TextView) findViewById(R.id.tv_job);
        tv_job.setOnClickListener(this);
        tv_company = (TextView) findViewById(R.id.tv_company);
        tv_company.setOnClickListener(this);
        initToolbar();
        tv_job.setText("职位详情");
        tv_company.setText("面经(99+)");
        fl_job_detail = (FrameLayout) findViewById(R.id.fl_job_detail);
        replaceFragment(R.id.fl_job_detail, new JobDetailFragment());
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

/*
    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.job_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }*/


    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void replaceFragment(int id_content, Fragment fragment) {
        FragmentTransaction transaction
                = getSupportFragmentManager().beginTransaction();
        transaction.replace(id_content, fragment);
        transaction.commit();
    }


    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_job:
                replaceFragment(R.id.fl_job_detail, new JobDetailFragment());
                tv_company.setTextColor(
                        getResources().getColor(R.color.jobtitlecolor));
                tv_company.setTextSize(18);
                tv_job.setTextColor(getResources().getColor(R.color.white));
                tv_job.setTextSize(20);
                break;
            case R.id.tv_company:
                replaceFragment(R.id.fl_job_detail, new InterviewExpFragment());
                tv_company.setTextColor(getResources().getColor(R.color.white));
                tv_company.setTextSize(20);
                tv_job.setTextColor(
                        getResources().getColor(R.color.jobtitlecolor));
                tv_job.setTextSize(18);
                break;
        }
    }
}

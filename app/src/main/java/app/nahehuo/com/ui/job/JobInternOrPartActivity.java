package app.nahehuo.com.ui.job;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.ui.job.fragment.JobInternshipFragment;
import app.nahehuo.com.ui.job.fragment.JobParttimeFragment;

/**
 * Created by WYB on 2016/1/13.
 */
public class JobInternOrPartActivity extends AppCompatActivity
        implements View.OnClickListener {

    protected Toolbar mToolbar;
    protected TextView tv_job, tv_company;
    private FrameLayout fl_job_internship;
    private int flag;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_internship);
        tv_job = (TextView) findViewById(R.id.tv_job);
        tv_job.setOnClickListener(this);
        tv_company = (TextView) findViewById(R.id.tv_company);
        tv_company.setOnClickListener(this);
        initToolbar();
        tv_job.setText("实习圈");
        tv_company.setText("兼职圈");
        fl_job_internship = (FrameLayout) findViewById(R.id.fl_job_internship);
        Intent intent = getIntent();
        flag = intent.getIntExtra("flag", 0);
        if (flag == 1) {
            changeLeftTitle();
        }
        else {
            changeRightTitle();
        }
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
        }
        return super.onOptionsItemSelected(item);
    }


    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_job:
                changeLeftTitle();
                break;
            case R.id.tv_company:
                changeRightTitle();
                break;
        }
    }


    public void replaceFragment(int id_content, Fragment fragment) {
        FragmentTransaction transaction
                = getSupportFragmentManager().beginTransaction();
        transaction.replace(id_content, fragment);
        transaction.commit();
    }


    private void changeLeftTitle() {
        replaceFragment(R.id.fl_job_internship, new JobInternshipFragment());
        tv_company.setTextColor(getResources().getColor(R.color.jobtitlecolor));
        tv_company.setTextSize(18);
        tv_job.setTextColor(getResources().getColor(R.color.white));
        tv_job.setTextSize(20);
    }


    private void changeRightTitle() {
        replaceFragment(R.id.fl_job_internship, new JobParttimeFragment());
        tv_company.setTextColor(getResources().getColor(R.color.white));
        tv_company.setTextSize(20);
        tv_job.setTextColor(getResources().getColor(R.color.jobtitlecolor));
        tv_job.setTextSize(18);
    }
}

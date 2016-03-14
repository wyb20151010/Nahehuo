package app.nahehuo.com.ui.job;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.MyViewPagerAdapter;
import app.nahehuo.com.ui.job.fragment.JobSearchComFragment;
import app.nahehuo.com.ui.job.fragment.JobSearchPosFragment;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/1/5.
 */
public class JobSearchActivity extends AppCompatActivity{

    private String [] mTitles;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private MyViewPagerAdapter mMyViewPagerAdapter;
    private List<Fragment> mFragments=new ArrayList<>();
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_search);
        mTitles=getResources().getStringArray(R.array.job_search_title);
        initView();
        initFragment();
       /* configViews();*/

    }


    /*private void configViews() {
        mMyViewPagerAdapter=new MyViewPagerAdapter(getSupportFragmentManager
                (),mTitles,mFragments);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(mMyViewPagerAdapter);

        mTabLayout.setupWithViewPager(mViewPager);

    }*/


    private void initFragment() {
        JobSearchPosFragment searchPosFragment=new JobSearchPosFragment();
        mFragments.add(searchPosFragment);
        JobSearchComFragment searchComFragment=new JobSearchComFragment();
        mFragments.add(searchComFragment);

    }


    private void initView() {
        mViewPager= (ViewPager) findViewById(R.id.id_viewpager);
        mTabLayout= (TabLayout) findViewById(R.id.id_tablayout);
    }
}

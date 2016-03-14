package app.nahehuo.com.ui.personal;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.MyViewPagerAdapter;
import app.nahehuo.com.ui.personal.fragment.PersonalEduExpFragment;
import app.nahehuo.com.ui.personal.fragment.PersonalWorkExpFragment;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/2/26.
 */

public class PersonalMy2VipActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView tv_title;
    private String[] titles = { "工作经历", "教育经历" };
    private TabLayout mTabLayout;
    private ViewPager vp_content;
    private List<Fragment> mFragments = new ArrayList<>();
    private List<String> type;
    private MyViewPagerAdapter mMyViewPagerAdapter;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_to_vip);
        initToolbar();
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("经历认证");
        initFragment();
        initTabLayout();
    }


    private void initFragment() {
        mFragments.add(new PersonalWorkExpFragment());
        mFragments.add(new PersonalEduExpFragment());
    }


    private void initTabLayout() {
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        vp_content = (ViewPager) findViewById(R.id.vp_content);
        mMyViewPagerAdapter = new MyViewPagerAdapter(
                getSupportFragmentManager(), mFragments);
        for (int i = 0; i < titles.length; i++) {
            mMyViewPagerAdapter.addItem(titles[i]);
            mTabLayout.addTab(mTabLayout.newTab());
        }

        vp_content.setAdapter(mMyViewPagerAdapter);
        mTabLayout.setupWithViewPager(vp_content);
        vp_content.setOffscreenPageLimit(2);
        mTabLayout.setTabsFromPagerAdapter(mMyViewPagerAdapter);
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
}

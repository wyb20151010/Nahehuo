package app.nahehuo.com.ui.personal;

import android.content.Context;
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
import app.nahehuo.com.ui.personal.fragment.PerProjectForkFragment;
import app.nahehuo.com.ui.personal.fragment.PerProjectPubFragment;
import app.nahehuo.com.ui.personal.fragment.PerProjectTalkFragment;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyb on 2016/3/4.
 */
public class PersonalMyProjectActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView tv_title;
    private Context mContext;
    private List<Fragment> mFragments = new ArrayList<>();
    private MyViewPagerAdapter mMyViewPagerAdapter;
    private String[] titles = { "我发布的", "我约谈的", "我关注的" };

    private TabLayout mTabLayout;
    private ViewPager vp_content;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per_my_project);

        mContext = this;
        initData();
        initTabLayout();
        initView();
        initToolbar();
    }


    private void initTabLayout() {
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        vp_content = (ViewPager) findViewById(R.id.vp_content);
        mMyViewPagerAdapter = new MyViewPagerAdapter(
                getSupportFragmentManager(), mFragments);
        for (int i = 0; i < mFragments.size(); i++) {
            mMyViewPagerAdapter.addItem(titles[i]);
            mTabLayout.addTab(mTabLayout.newTab());
        }
        vp_content.setAdapter(mMyViewPagerAdapter);
        mTabLayout.setupWithViewPager(vp_content);
        vp_content.setOffscreenPageLimit(3);
        mTabLayout.setTabsFromPagerAdapter(mMyViewPagerAdapter);
    }


    private void initData() {
        mFragments.add(new PerProjectPubFragment());
        mFragments.add(new PerProjectTalkFragment());
        mFragments.add(new PerProjectForkFragment());
    }


    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("我的项目");
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

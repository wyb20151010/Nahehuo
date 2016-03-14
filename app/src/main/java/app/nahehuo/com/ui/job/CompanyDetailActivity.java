package app.nahehuo.com.ui.job;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.MyViewPagerAdapter;
import app.nahehuo.com.ui.job.fragment.CompCommentFragment;
import app.nahehuo.com.ui.job.fragment.CompHomePageFragment;
import app.nahehuo.com.ui.job.fragment.CompJobFragment;
import app.nahehuo.com.ui.job.fragment.CompProgressFragment;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/1/11.
 */
public class CompanyDetailActivity extends AppCompatActivity {

    private TextView tv_title;
    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager vp_content;
    private String[] titles = { "主页", "职位", "历程", "点评" };
    private MyViewPagerAdapter mMyViewPagerAdapter;
    private List<Fragment> mFragments = new ArrayList<>();
    private TagFlowLayout tfl_company_tag;
    private String[] tags={"D轮及以上","齐齐哈尔","本科以上","原油","电力","1000-9000人"};

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_detail);
        initToolBar();
        initFragment();
        initTabLayout();
        initView();
    }


    private void initView() {
        tfl_company_tag= (TagFlowLayout) findViewById(R.id.tfl_company_tag);
        tfl_company_tag.setAdapter(new TagAdapter(tags) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                LayoutInflater inflater
                        = (LayoutInflater) getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                View v = inflater.inflate(R.layout.item_tag, parent, false);
                TextView tv_tag = (TextView) v.findViewById(R.id.tv_tag);
                tv_tag.setText(tags[position]);
                return v;
            }
        });
    }


    private void initFragment() {
        mFragments.add(new CompHomePageFragment());
        mFragments.add(new CompJobFragment());
        mFragments.add(new CompProgressFragment());
        mFragments.add(new CompCommentFragment());
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
        vp_content.setOffscreenPageLimit(4);
        mTabLayout.setTabsFromPagerAdapter(mMyViewPagerAdapter);

    }


    private void initToolBar() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("企业主页");
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
    }


    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.job, menu);
        return super.onCreateOptionsMenu(menu);
    }
}

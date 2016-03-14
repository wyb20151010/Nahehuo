package app.nahehuo.com.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.MyViewPagerAdapter;
import app.nahehuo.com.ui.MainActivity;
import app.nahehuo.com.ui.partner.PartnerSeaActivity;
import app.nahehuo.com.ui.partner.fragment.PartnerMyFriFragment;
import app.nahehuo.com.ui.partner.fragment.PartnerRecUFragment;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2015/12/24.
 */
public class PartnerFragment extends Fragment {
    private Context mContext;
    private Toolbar mToolbar;
    private MainActivity mainActivity;

    private MyViewPagerAdapter mMyViewPagerAdapter;
    private String[] titles = { "我的好友", "为你推荐" };
    private TabLayout mTabLayout;
    private ViewPager vp_content;
    private List<Fragment> mFragments = new ArrayList<>();


    @Override public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof MainActivity) {
            mainActivity = (MainActivity) activity;
        }
        else {
            throw new IllegalArgumentException(
                    "The activity must be a MainActivity !");
        }
    }


    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_partner, null);
        mContext = getActivity();
        initData();
        initTabLayout(v);
        return v;
    }


    private void initData() {
        mFragments.add(new PartnerMyFriFragment());
        mFragments.add(new PartnerRecUFragment());
    }


    private void initTabLayout(View v) {
        mTabLayout = (TabLayout) v.findViewById(R.id.tabs);
        vp_content = (ViewPager) v.findViewById(R.id.vp_content);
        mMyViewPagerAdapter = new MyViewPagerAdapter(
                mainActivity.getSupportFragmentManager(), mFragments);
        for (int i = 0; i < titles.length; i++) {
            mMyViewPagerAdapter.addItem(titles[i]);
            mTabLayout.addTab(mTabLayout.newTab());
        }
        vp_content.setAdapter(mMyViewPagerAdapter);
        mTabLayout.setupWithViewPager(vp_content);
        vp_content.setOffscreenPageLimit(4);
        mTabLayout.setTabsFromPagerAdapter(mMyViewPagerAdapter);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.job, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                startActivity(new Intent(mContext, PartnerSeaActivity.class));
                mainActivity.overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

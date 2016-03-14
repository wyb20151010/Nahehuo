package app.nahehuo.com.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2015/6/29.
 */
public class MyViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<String> mTitles=new ArrayList<>();
    private List<Fragment> mFragments;

    public MyViewPagerAdapter(FragmentManager fm, List<Fragment> mFragments) {
        super(fm);
        this.mFragments=mFragments;
    }
    public void addItem(String title) {
        mTitles.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}

package app.nahehuo.com.ui.job;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import app.nahehuo.com.R;
import app.nahehuo.com.ui.job.fragment.CompanyFounder2Fragment;
import app.nahehuo.com.ui.job.fragment.CompanyFounderFragment;
import com.viewpagerindicator.CirclePageIndicator;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/1/14.
 */
public class CompanyFounderActivity extends AppCompatActivity {

    private ViewPager vp_content;
    private List<Fragment> mFragments = new ArrayList<>();
    private CompanyFragmentAdapter mFragmentAdapter;
    private CirclePageIndicator indicator;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_founder);
        initVp();
    }


    private void initVp() {
        indicator= (CirclePageIndicator) findViewById(R.id.indicator);
        mFragments.add(new CompanyFounderFragment());
        mFragments.add(new CompanyFounder2Fragment());
        mFragmentAdapter = new CompanyFragmentAdapter(
                getSupportFragmentManager());
        vp_content = (ViewPager) findViewById(R.id.vp_content);
        vp_content.setAdapter(mFragmentAdapter);

        indicator.setViewPager(vp_content);
        final float density = getResources().getDisplayMetrics().density;
        indicator.setBackgroundColor(0x00999999);
        indicator.setRadius(6 * density);
        indicator.setPageColor(0x88FFFFFF);
        indicator.setFillColor(0xFFFFFFFF);
        indicator.setStrokeColor(0xFFFFFFFF);

    }


    class CompanyFragmentAdapter extends FragmentPagerAdapter {

        public CompanyFragmentAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override public Fragment getItem(int position) {
            return mFragments.get(position);
        }


        @Override public int getCount() {
            return mFragments.size();
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }
    }
}

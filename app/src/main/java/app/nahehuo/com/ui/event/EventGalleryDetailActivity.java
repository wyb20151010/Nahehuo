package app.nahehuo.com.ui.event;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import app.nahehuo.com.R;
import com.viewpagerindicator.CirclePageIndicator;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/1/30.
 */
public class EventGalleryDetailActivity extends AppCompatActivity {

    private ViewPager vp_content;
    private List<Fragment> mFragments = new ArrayList<>();
    private EventFragmentAdapter mFragmentAdapter;
    private CirclePageIndicator indicator;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_gallery_detail);
        initVp();
    }


    private void initVp() {
        indicator = (CirclePageIndicator) findViewById(R.id.indicator);
        mFragments.add(EventAvaterFragment.newInstance("http://www.chinanews.com/cr/2015/0115/4190276742.jpeg"));
        mFragments.add(EventAvaterFragment.newInstance("http://cdn.nextshark.com/wp-content/uploads/2015/10/1661571_1454628484751021_517440285_n.jpg"));
        mFragments.add(EventAvaterFragment.newInstance("http://www.chinanews.com/cr/2015/0115/4190276742.jpeg"));
        mFragments.add(EventAvaterFragment.newInstance("http://www.chinanews.com/cr/2015/0115/4190276742.jpeg"));
        mFragments.add(EventAvaterFragment.newInstance("http://www.chinanews.com/cr/2015/0115/4190276742.jpeg"));
        mFragmentAdapter = new EventFragmentAdapter(
                getSupportFragmentManager());
        vp_content = (ViewPager) findViewById(R.id.vp_content);
        vp_content.setAdapter(mFragmentAdapter);
        indicator.setViewPager(vp_content);
        final float density = getResources().getDisplayMetrics().density;
        indicator.setBackgroundColor(0x00999999);
        indicator.setRadius(3 * density);
        indicator.setPageColor(0x88FFFFFF);
        indicator.setFillColor(0xFFFFFFFF);
        indicator.setStrokeColor(0xFFFFFFFF);
    }


    class EventFragmentAdapter extends FragmentPagerAdapter {

        public EventFragmentAdapter(FragmentManager fm) {
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

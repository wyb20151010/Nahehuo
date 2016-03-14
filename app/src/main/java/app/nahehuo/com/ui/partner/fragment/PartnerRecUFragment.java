package app.nahehuo.com.ui.partner.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.PartnerRecUAdapter;
import app.nahehuo.com.bean.PartnerVP;
import app.nahehuo.com.ui.MainActivity;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.viewpagerindicator.CirclePageIndicator;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/2/18.
 */
public class PartnerRecUFragment extends Fragment {

    private CirclePageIndicator indicator;
    private ViewPager vp_content_title;
    private PartnerRecUVPAdapter mAdapter;
    private List<PartnerVP> mPartnerVPs = new ArrayList<>();
    private MainActivity mainActivity;
    private PullToRefreshListView plv_reco;
    private PartnerRecUAdapter mRecUAdapter;


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
        View v = inflater.inflate(R.layout.fragment_part_rec_u, null);
        vp_content_title = (ViewPager) v.findViewById(R.id.vp_content_title);
        indicator = (CirclePageIndicator) v.findViewById(R.id.indicator);

        initView(v);
        return v;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViewPager();
    }


    private void initView(View v) {
        plv_reco = (PullToRefreshListView) v.findViewById(R.id.plv_reco);
        mRecUAdapter = new PartnerRecUAdapter();
        plv_reco.setAdapter(mRecUAdapter);
    }


    private void initViewPager() {
        mPartnerVPs.add(new PartnerVP("安安", "创始人CEO", "今翌信息科技(上海)有限公司"));
        mPartnerVPs.add(new PartnerVP("周韵", "创始人CEO", "今翌信息科技(上海)有限公司"));
        mAdapter = new PartnerRecUVPAdapter(getChildFragmentManager(),
                mPartnerVPs);
        vp_content_title.setAdapter(mAdapter);
        indicator.setViewPager(vp_content_title);
        final float density = getResources().getDisplayMetrics().density;
        indicator.setBackgroundColor(0x00999999);
        indicator.setRadius(4 * density);
        indicator.setPageColor(0x88FFFFFF);
        indicator.setFillColor(0xFF008ff3);
        indicator.setStrokeColor(0xFFFFFFFF);
    }


    private class PartnerRecUVPAdapter extends FragmentPagerAdapter {

        private List<PartnerVP> items;


        public PartnerRecUVPAdapter(FragmentManager fm, List<PartnerVP> mPartnerVPs) {
            super(fm);
            this.items = mPartnerVPs;
        }


        @Override public Fragment getItem(int position) {
            return PartnerTitleFragment.getInstance(items.get(position));
        }


        @Override public int getCount() {
            return items.size();
        }
    }
}

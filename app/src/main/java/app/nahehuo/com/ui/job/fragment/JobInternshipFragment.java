package app.nahehuo.com.ui.job.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.JobParttimeAdapter;
import app.nahehuo.com.bean.JobPartDict;
import app.nahehuo.com.ui.job.JobInternOrPartActivity;
import app.nahehuo.com.ui.job.JobPartTimeDetailActivity;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/1/13.
 */
public class JobInternshipFragment extends Fragment
        implements AdapterView.OnItemClickListener {

    private PullToRefreshListView plv_internship;
    private JobParttimeAdapter mParttimeAdapter;
    private List<JobPartDict> mJobPartDicts = new ArrayList<>();
    private Context mContext;
    private JobInternOrPartActivity mJobInternOrPartActivity;


    @Override public void onAttach(Activity activity) {
        if (activity instanceof JobInternOrPartActivity) {
            mJobInternOrPartActivity = (JobInternOrPartActivity) activity;
        }
        else {
            throw new IllegalArgumentException(
                    "The activity must be a JobInternOrPartActivity !");
        }
        super.onAttach(activity);
    }


    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_internship, null);
        mContext = getActivity();
        initPlv(v);
        return v;
    }


    private void initPlv(View v) {
        initData();
        mParttimeAdapter = new JobParttimeAdapter(mJobPartDicts, mContext);
        plv_internship = (PullToRefreshListView) v.findViewById(
                R.id.plv_internship);
        plv_internship.setMode(PullToRefreshBase.Mode.BOTH);
        plv_internship.setAdapter(mParttimeAdapter);
        plv_internship.setOnItemClickListener(this);
    }


    private void initData() {
        mJobPartDicts.add(
                new JobPartDict("哪合伙实习运营中心", "苏州", "300一天，12月30日-1月2号世贸人员活动礼仪",
                        "我们着力解决管党治党失之于宽、失之于松、失之于软的问题，使不敢腐的震慑作用充治党失之于宽、失之于松、失之于软的问题，使不敢腐的震慑作用充",
                        "2015-12-24 12:12", "321"));
        mJobPartDicts.add(
                new JobPartDict("哪合伙实习运营中心", "上海", "300一天，12月30日-1月2号世贸人员活动礼仪",
                        "我们着力解决管党治党失之于宽、失之于松、失之于软的问题，使不敢腐的震慑作用充治党失之于宽、失之于松、失之于软的问题，使不敢腐的震慑作用充",
                        "2015-12-24 12:12", "321"));
        mJobPartDicts.add(
                new JobPartDict("哪合伙实习运营中心", "上海", "300一天，12月30日-1月2号世贸人员活动礼仪",
                        "盛世财富中止正常投资理财业务，工作重心转向督促融资方尽快处置资产、实现变现",
                        "2015-12-24 12:12", "321"));
        mJobPartDicts.add(
                new JobPartDict("哪合伙实习运营中心", "北京", "300一天，12月30日-1月2号世贸人员活动礼仪",
                        "盛世财富中止正常投资理财业务，工作重心转向督促融资方尽快处置资产、实现变现",
                        "2015-12-24 12:12", "321"));
        mJobPartDicts.add(
                new JobPartDict("哪合伙实习运营中心", "上海", "300一天，12月30日-1月2号世贸人员活动礼仪",
                        "我们着力解决管党治党失之于宽、失之于松、失之于软的问题，使不敢腐的震慑作用充治党失之于宽、失之于松、失之于软的问题，使不敢腐的震慑作用充",
                        "2015-12-24 12:12", "321"));
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(mContext, JobPartTimeDetailActivity.class));
        mJobInternOrPartActivity.overridePendingTransition(R.anim.push_left_in,
                R.anim.push_left_out);
    }
}

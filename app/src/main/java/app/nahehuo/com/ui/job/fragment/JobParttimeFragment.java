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
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.JobParttimeAdapter;
import app.nahehuo.com.base.DBHelp;
import app.nahehuo.com.bean.DictCity;
import app.nahehuo.com.bean.JobPartDict;
import app.nahehuo.com.ui.Popup2List;
import app.nahehuo.com.ui.job.JobInternOrPartActivity;
import app.nahehuo.com.ui.job.JobPartTimeDetailActivity;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.DbUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/1/13.
 */
public class JobParttimeFragment extends Fragment
        implements AdapterView.OnItemClickListener, View.OnClickListener {

    private PullToRefreshListView plv_part_time;
    private JobParttimeAdapter mParttimeAdapter;
    private List<JobPartDict> mJobPartDicts = new ArrayList<>();
    private Context mContext;
    private JobInternOrPartActivity mJobInternOrPartActivity;
    private Button btn_login;
    private RelativeLayout rl_city;
    private TextView tv_city;
    private List<DictCity> mCities = new ArrayList<>();
    private List<DictCity> cities_one_level = new ArrayList<>();
    private List<DictCity> cities_two_level = new ArrayList<>();
    private DbUtils mDbUtils;
    private Popup2List mPopupCity;


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
        View v = inflater.inflate(R.layout.fragment_parttime, null);
        mContext = getActivity();
        mDbUtils = DBHelp.getInstance(mContext);
        initPlv(v);
       /* new Thread(new Runnable() {
            @Override public void run() {
                try {
                    mCities = mDbUtils.findAll(Selector.from(DictCity.class));
                    for (int i = 0; i < mCities.size(); i++) {
                        if (mCities.get(i).getLevel() == 1) {
                            cities_one_level.add(mCities.get(i));
                        }
                        else if (mCities.get(i).getLevel() == 2) {
                            cities_two_level.add(mCities.get(i));
                        }
                    }
                    mPopupCity = new Popup2List(mJobInternOrPartActivity,
                            cities_one_level, cities_two_level, "请选择期望城市");
                } catch (DbException e) {
                    e.printStackTrace();
                }
            }
        }).start();*/
        return v;
    }


    private void initPlv(View v) {
        initData();
        plv_part_time = (PullToRefreshListView) v.findViewById(
                R.id.plv_partner_time);
        mParttimeAdapter = new JobParttimeAdapter(mJobPartDicts, mContext);
        plv_part_time.setMode(PullToRefreshBase.Mode.BOTH);
        plv_part_time.setAdapter(mParttimeAdapter);
        plv_part_time.setOnItemClickListener(this);
        btn_login = (Button) v.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        rl_city = (RelativeLayout) v.findViewById(R.id.rl_city);
        tv_city = (TextView) v.findViewById(R.id.tv_city);
        rl_city.setOnClickListener(this);
    }


    private void initData() {
        mJobPartDicts.add(
                new JobPartDict("哪合伙兼职运营中心", "上海", "300一天，12月30日-1月2号世贸人员活动礼仪",
                        "我们着力解决管党治党失之于宽、失之于松、失之于软的问题，使不敢腐的震慑作用充治党失之于宽、失之于松、失之于软的问题，使不敢腐的震慑作用充",
                        "2015-12-24 12:12", "321"));
        mJobPartDicts.add(
                new JobPartDict("哪合伙兼职运营中心", "上海", "300一天，12月30日-1月2号世贸人员活动礼仪",
                        "我们着力解决管党治党失之于宽、失之于松、失之于软的问题，使不敢腐的震慑作用充治党失之于宽、失之于松、失之于软的问题，使不敢腐的震慑作用充",
                        "2015-12-24 12:12", "321"));
        mJobPartDicts.add(
                new JobPartDict("哪合伙兼职运营中心", "上海", "300一天，12月30日-1月2号世贸人员活动礼仪",
                        "盛世财富中止正常投资理财业务，工作重心转向督促融资方尽快处置资产、实现变现",
                        "2015-12-24 12:12", "321"));
        mJobPartDicts.add(
                new JobPartDict("哪合伙兼职运营中心", "上海", "300一天，12月30日-1月2号世贸人员活动礼仪",
                        "盛世财富中止正常投资理财业务，工作重心转向督促融资方尽快处置资产、实现变现",
                        "2015-12-24 12:12", "321"));
        mJobPartDicts.add(
                new JobPartDict("哪合伙兼职运营中心", "上海", "300一天，12月30日-1月2号世贸人员活动礼仪",
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


    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                btn_login.setVisibility(View.GONE);
                break;
            case R.id.rl_city:

                break;
        }
    }
}

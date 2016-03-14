package app.nahehuo.com.ui.job.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.GirdDropDownAdapter;
import app.nahehuo.com.adapter.JobParttimeAdapter;
import app.nahehuo.com.adapter.SelectAdapter;
import app.nahehuo.com.bean.DictCityData;
import app.nahehuo.com.bean.HeadItem;
import app.nahehuo.com.bean.JobPartDict;
import app.nahehuo.com.ui.job.JobInternOrPartActivity;
import app.nahehuo.com.ui.job.JobPartTimeDetailActivity;
import app.nahehuo.com.util.NetworkTools;
import app.nahehuo.com.view.DropDownMenu;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by WYB on 2016/1/13.
 */
public class JobParttimeFragment extends Fragment
        implements AdapterView.OnItemClickListener, View.OnClickListener {

    private JobParttimeAdapter mParttimeAdapter;
    private List<JobPartDict> mJobPartDicts = new ArrayList<>();
    private Context mContext;
    private JobInternOrPartActivity mJobInternOrPartActivity;
    private Button btn_enter_part;
    private DropDownMenu mDropDownMenu;
    private GirdDropDownAdapter indusAdapter;
    private SelectAdapter<DictCityData> cityAdapter;
    private HeadItem headers[] = { new HeadItem(false, "最新"),
            new HeadItem(false, "最热"), new HeadItem(true, "类型"),
            new HeadItem(true, "城市"), };
    private String citys[] = { "不限", "武汉", "北京", "上海", "成都", "广州", "深圳", "重庆",
            "天津", "西安", "南京", "杭州", "成都", "广州", "深圳", "重庆" };
    private String industries[] = { "计算机科学与技术", "机械制造和自动化", "网络技术", "社会主义" };
    private List<View> popupViews = new ArrayList<>();
    private PullToRefreshListView plv_part_time;

    private String addressID;
    private SelectAdapter<DictCityData> selectAdapterOne, selectAdapterTwo;
    private List<DictCityData> mCityData1 = new ArrayList<>();
    private List<DictCityData> mCityData2 = new ArrayList<>();
    private List<DictCityData> mCityData3 = new ArrayList<>();
    private ListView listViewAddressOne, listViewAddressTwo;
    private List<DictCityData> mCityDatas = new ArrayList<>();
    private static final int GET_CITY = 1;
    private Handler mHandler = new Handler() {
        @Override public void handleMessage(Message msg) {
            switch (msg.what) {
                case GET_CITY:
                   /* getCity();*/
                    break;
            }
            super.handleMessage(msg);
        }
    };


  /*  private void getCity() {
        HashMap<String, String> map = new HashMap<>();
        GsonRequest<CityData> request = new GsonRequest<CityData>(
                "http://api.zcspin.com/v2/getDictProvinceCity", map,
                CityData.class, new Response.Listener<CityData>() {
            @Override public void onResponse(CityData data) {
                for (int i = 0; i < data.getCities().size(); i++) {
                    DictCityData cityData = new DictCityData();
                    cityData.setId(data.getCities().get(i).getId());
                    cityData.setLevel(data.getCities().get(i).getLevel());
                    cityData.setName(data.getCities().get(i).getName());
                    cityData.setUpid(data.getCities().get(i).getUpid());
                    mCityDatas.add(cityData);
                }
                for (int i = 0;
                     mCityDatas != null && i < mCityDatas.size();
                     i++) {
                    if (mCityDatas.get(i).getLevel().equals("1")) {
                        DictCityData dictCity = new DictCityData();
                        dictCity.setId(mCityDatas.get(i).getId());
                        dictCity.setLevel(mCityDatas.get(i).getLevel());
                        dictCity.setName(mCityDatas.get(i).getName());
                        dictCity.setUpid(mCityDatas.get(i).getUpid());
                        mCityData1.add(dictCity);
                    }
                    else if (mCityDatas.get(i).getLevel().equals("2")) {
                        DictCityData dictCity = new DictCityData();
                        dictCity.setId(mCityDatas.get(i).getId());
                        dictCity.setLevel(mCityDatas.get(i).getLevel());
                        dictCity.setName(mCityDatas.get(i).getName());
                        dictCity.setUpid(mCityDatas.get(i).getUpid());
                        mCityData2.add(dictCity);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override public void onErrorResponse(VolleyError volleyError) {

            }
        });
        mQueue.add(request);
    }*/


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
       /* mQueue = Volley.newRequestQueue(mContext);*/
        btn_enter_part = (Button) v.findViewById(R.id.btn_enter_part);
        btn_enter_part.setOnClickListener(this);
        initData();
        initDropDown(inflater, v);
        initPlv(v);
        return v;
    }


    private void initDropDown(LayoutInflater inflater, View v) {
        mDropDownMenu = (DropDownMenu) v.findViewById(R.id.dropDownMenu);
        final ListView newView = (ListView) inflater.inflate(
                R.layout.item_cityview, null);
        popupViews.add(newView);
        final ListView hotView = (ListView) inflater.inflate(
                R.layout.item_cityview, null);
        popupViews.add(hotView);
        final ListView cateView = (ListView) inflater.inflate(
                R.layout.item_cityview, null);
        indusAdapter = new GirdDropDownAdapter(mContext,
                Arrays.asList(industries));
        cateView.setDividerHeight(0);
        cateView.setAdapter(indusAdapter);
        popupViews.add(cateView);
        cateView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                indusAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0
                                         ? headers[1].getTitle()
                                         : industries[position]);
                mDropDownMenu.closeMenu();
            }
        });
        final View cityView = inflater.inflate(R.layout.pop_selector_two, null);
        listViewAddressOne = (ListView) cityView.findViewById(
                R.id.list_two_left);
        listViewAddressTwo = (ListView) cityView.findViewById(
                R.id.list_two_right);
        listViewAddressOne.setDividerHeight(0);
        listViewAddressTwo.setDividerHeight(0);
        selectAdapterOne = new SelectAdapter<>(mContext, mCityData1);
        selectAdapterTwo = new SelectAdapter<>(mContext, mCityData2);
        listViewAddressOne.setAdapter(selectAdapterOne);
        listViewAddressTwo.setAdapter(selectAdapterTwo);

        popupViews.add(cityView);
        listViewAddressOne.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mCityData3.clear();
                        selectAdapterOne.setCheckItem(position);
                        addressID = mCityData1.get(position).getId();
                        for (int i = 0; i < mCityData2.size(); i++) {
                            if (addressID.equals(mCityData2.get(i).getUpid())) {
                                DictCityData data = new DictCityData();
                                data.setName(mCityData2.get(i).getName());
                                data.setUpid(mCityData2.get(i).getUpid());
                                data.setLevel(mCityData2.get(i).getLevel());
                                data.setId(mCityData2.get(i).getId());
                                mCityData3.add(data);
                            }
                        }
                        selectAdapterTwo = new SelectAdapter<DictCityData>(mContext,
                                mCityData3);
                        listViewAddressTwo.setAdapter(selectAdapterTwo);

                        listViewAddressTwo.setOnItemClickListener(
                                new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        selectAdapterTwo.setCheckChildItem(
                                                position);
                                        mDropDownMenu.setTabText(position == 0
                                                                 ? headers[1].getTitle()
                                                                 : mCityData3.get(
                                                                         position)
                                                                             .getName());
                                        mDropDownMenu.closeMenu();
                                    }
                                });
                    }
                });

        View content = inflater.inflate(R.layout.footview, null);
        plv_part_time = (PullToRefreshListView) content.findViewById(
                R.id.plv_project_list);
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews,
                content);
    }


    private void initPlv(View v) {
        mParttimeAdapter = new JobParttimeAdapter(mJobPartDicts, mContext);
        plv_part_time.setMode(PullToRefreshBase.Mode.BOTH);
        plv_part_time.setAdapter(mParttimeAdapter);
        plv_part_time.setOnItemClickListener(this);
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
        if (NetworkTools.isNetworkAvailable(mContext)) {
            mHandler.sendEmptyMessage(GET_CITY);
        }
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
            case R.id.btn_enter_part:
                btn_enter_part.setVisibility(View.GONE);
                break;
        }
    }
}

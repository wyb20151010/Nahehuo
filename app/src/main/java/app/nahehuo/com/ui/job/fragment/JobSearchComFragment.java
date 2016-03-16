package app.nahehuo.com.ui.job.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.JobSearchComAdapter;
import app.nahehuo.com.base.GlobalVariables;
import app.nahehuo.com.bean.CompanySearch;
import app.nahehuo.com.bean.NetSearchCompany;
import app.nahehuo.com.network.GsonCallBack;
import app.nahehuo.com.ui.job.JobSearchActivity;
import app.nahehuo.com.util.HideSoftInputUtil;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhy.http.okhttp.OkHttpUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/1/5.
 */
public class JobSearchComFragment extends Fragment
        implements PullToRefreshBase.OnRefreshListener<ListView> {

    private Context mContext;
    private JobSearchActivity mJobDetailActivity;
    private PullToRefreshListView plv_company;
    private LinearLayout ll_content;

    private List<CompanySearch> mCompanySearches = new ArrayList<>();
    private JobSearchComAdapter mComAdapter;

    private final static int SEARCH_COMPANY = 0;
    private int pagerIndex = 1;
    private String keyWord = "";
    private Handler mHandler = new Handler() {

        @Override public void handleMessage(Message msg) {
            switch (msg.what) {
                case SEARCH_COMPANY:
                    searchCompany();
                    break;
            }
            super.handleMessage(msg);
        }
    };


    private void searchCompany() {
        plv_company.onRefreshComplete();
        if (pagerIndex == 1) {
            mCompanySearches.clear();
        }
        OkHttpUtils.get()
                   .url(GlobalVariables.COMPANY_SEARCH)
                   .addParams("access_token", GlobalVariables.access_token)
                   .addParams("device", GlobalVariables.device)
                   .addParams("keyword", keyWord)
                   .addParams("pageindex", pagerIndex + "")
                   .addParams("pagesize", GlobalVariables.pagesize)
                   .build()
                   .execute(new GsonCallBack<NetSearchCompany>(
                           NetSearchCompany.class) {
                       @Override
                       public void onResponse(NetSearchCompany response) {
                           if (response.getCode() == 200) {
                               for (int i = 0;
                                    i < response.getData().size();
                                    i++) {
                                   CompanySearch search = new CompanySearch();
                                   if (!TextUtils.isEmpty(response.getData()
                                                                  .get(i)
                                                                  .getName())) {
                                       search.setName(response.getData()
                                                              .get(i)
                                                              .getName()
                                                              .equals("0")
                                                      ? "行业未知"
                                                      : response.getData()
                                                                .get(i)
                                                                .getName());
                                   }

                                   if (!TextUtils.isEmpty(response.getData()
                                                                  .get(i)
                                                                  .getCity()) ||
                                           !TextUtils.isEmpty(response.getData()
                                                                      .get(i)
                                                                      .getProv())) {
                                       search.setCity(response.getData()
                                                              .get(i)
                                                              .getProv() +
                                               response.getData()
                                                       .get(i)
                                                       .getCity());
                                   }

                                   if (!TextUtils.isEmpty(response.getData()
                                                                  .get(i)
                                                                  .getFinancle())) {
                                       search.setFinance(response.getData()
                                                                 .get(i)
                                                                 .getFinancle()
                                                                 .equals("0")
                                                         ? "未融资"
                                                         : response.getData()
                                                                   .get(i)
                                                                   .getFinancle());
                                   }
                                   if (!TextUtils.isEmpty(response.getData()
                                                                  .get(i)
                                                                  .getSize())) {
                                       search.setSize(response.getData()
                                                              .get(i)
                                                              .getSize()
                                                              .equals("0")
                                                      ? "1-10人"
                                                      : response.getData()
                                                                .get(i)
                                                                .getSize());
                                   }
                                   if (!TextUtils.isEmpty(response.getData()
                                                                  .get(i)
                                                                  .getIndustry())) {
                                       search.setIndustry(response.getData()
                                                                  .get(i)
                                                                  .getIndustry()
                                                                  .equals("0")
                                                          ? "行业未知"
                                                          : response.getData()
                                                                    .get(i)
                                                                    .getIndustry());
                                   }
                                   mCompanySearches.add(search);
                               }
                               mComAdapter.notifyDataSetChanged();
                               plv_company.setVisibility(View.VISIBLE);
                               ll_content.setVisibility(View.INVISIBLE);
                           }
                           else  {
                               plv_company.setVisibility(View.INVISIBLE);
                               ll_content.setVisibility(View.VISIBLE);
                           }
                           HideSoftInputUtil.hideSoftKeyboard(
                                   mJobDetailActivity);
                           super.onResponse(response);
                       }
                   });
    }


    @Override public void onAttach(Activity activity) {
        if (activity instanceof JobSearchActivity) {
            mJobDetailActivity = (JobSearchActivity) activity;
        }
        else {
            throw new IllegalArgumentException(
                    "The activity must be a JobSearchActivity !");
        }
        super.onAttach(activity);
    }


    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(
                R.layout.fragment_company_search_pos, null);
        mContext = getActivity();
        plv_company = (PullToRefreshListView) contentView.findViewById(
                R.id.plv_company);
        plv_company.setMode(PullToRefreshBase.Mode.BOTH);
        mComAdapter = new JobSearchComAdapter(mCompanySearches, mContext);
        plv_company.setAdapter(mComAdapter);
        plv_company.setOnRefreshListener(this);
        mHandler.sendEmptyMessage(SEARCH_COMPANY);
        ll_content = (LinearLayout) contentView.findViewById(R.id.ll_content);
        mJobDetailActivity.setConvertData(new JobSearchActivity.ConvertData() {
            @Override public void convertData(String data) {
                keyWord = data;
                mHandler.sendEmptyMessage(SEARCH_COMPANY);
            }
        });
        return contentView;
    }


    @Override public void onRefresh(PullToRefreshBase<ListView> refreshView) {
        if (refreshView.isHeaderShown()) {
            pagerIndex = 1;
            mCompanySearches.clear();
            mHandler.sendEmptyMessage(SEARCH_COMPANY);
        }
        else if (refreshView.isFooterShown()) {
            pagerIndex++;
            mHandler.sendEmptyMessage(SEARCH_COMPANY);
        }
    }
}

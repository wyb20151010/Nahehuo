package app.nahehuo.com.ui.job.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.PerMyInterAdapter;
import app.nahehuo.com.base.GlobalVariables;
import app.nahehuo.com.bean.MyInter;
import app.nahehuo.com.bean.NetJobSearch;
import app.nahehuo.com.network.GsonCallBack;
import app.nahehuo.com.ui.job.JobSearchActivity;
import app.nahehuo.com.util.HideSoftInputUtil;
import app.nahehuo.com.util.MyToast;
import app.nahehuo.com.util.VeDate;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhy.http.okhttp.OkHttpUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/1/5.
 */
public class JobSearchPosFragment extends Fragment
        implements PullToRefreshBase.OnRefreshListener<ListView> {

    private Context mContext;
    private JobSearchActivity mJobDetailActivity;
    private PullToRefreshListView plv_job;
    private LinearLayout ll_no_data;
    private final static int SEARCH_JOB = 0;
    private String keyWord = "";
    private int pagerIndex = 1;
    private List<MyInter> mMyInters = new ArrayList<>();
    private PerMyInterAdapter mPerMyInterAdapter;

    private Handler mHandler = new Handler() {

        @Override public void handleMessage(Message msg) {
            switch (msg.what) {
                case SEARCH_JOB:
                    searchJob();
                    break;
            }
            super.handleMessage(msg);
        }
    };


    private void searchJob() {
        plv_job.onRefreshComplete();
        if (pagerIndex == 1) {
            mMyInters.clear();
        }
        OkHttpUtils.get()
                   .url(GlobalVariables.JOB_SEARCH)
                   .addParams("access_token", GlobalVariables.access_token)
                   .addParams("device", GlobalVariables.device)
                   .addParams("keyword", keyWord)
                   .addParams("pageindex", pagerIndex + "")
                   .addParams("pagesize", GlobalVariables.pagesize)
                   .build()
                   .execute(new GsonCallBack<NetJobSearch>(NetJobSearch.class) {
                       @Override public void onResponse(NetJobSearch response) {
                           if (response.getCode() == 200) {
                               for (int i = 0;
                                    i < response.getData().size();
                                    i++) {
                                   MyInter myInter = new MyInter();
                                   myInter.setCompany(response.getData()
                                                              .get(i)
                                                              .getCompany());
                                   myInter.setSalary(response.getData()
                                                             .get(i)
                                                             .getWagemin() +
                                                   "k-" + response.getData()
                                                                  .get(i)
                                                                  .getWagemax() +
                                                   "k");
                                   myInter.setPosition(response.getData()
                                                               .get(i)
                                                               .getPosition());
                                   myInter.setTime(VeDate.formatHelpReplyTime(
                                           response.getData()
                                                   .get(i)
                                                   .getPublished()));
                                   myInter.setLogo(
                                           response.getData().get(i).getLogo());
                                   mMyInters.add(myInter);
                               }
                               mPerMyInterAdapter.notifyDataSetChanged();
                               plv_job.setVisibility(View.VISIBLE);
                               ll_no_data.setVisibility(View.INVISIBLE);
                           }
                           else if (response.getCode() == 4016) {
                               ll_no_data.setVisibility(View.VISIBLE);
                               plv_job.setVisibility(View.INVISIBLE);
                           }
                           else {
                               MyToast.showToast(mContext,
                                       response.getMessage());
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

        View contentView = inflater.inflate(R.layout.fragment_job_search_pos,
                null);
        mContext = getActivity();
        ll_no_data = (LinearLayout) contentView.findViewById(R.id.ll_no_data);

        plv_job = (PullToRefreshListView) contentView.findViewById(
                R.id.plv_job);
        mPerMyInterAdapter = new PerMyInterAdapter(mMyInters, mContext);
        plv_job.setMode(PullToRefreshBase.Mode.BOTH);
        plv_job.setOnRefreshListener(this);
        plv_job.setAdapter(mPerMyInterAdapter);
        mHandler.sendEmptyMessage(SEARCH_JOB);
        mJobDetailActivity.setConvertData2(new JobSearchActivity.ConvertData2
                () {
            @Override public void convertData(String data) {
                keyWord = data;
                mHandler.sendEmptyMessage(SEARCH_JOB);
            }
        });

        return contentView;
    }


    @Override public void onRefresh(PullToRefreshBase<ListView> refreshView) {
        if (refreshView.isHeaderShown()) {
            pagerIndex = 1;
            mMyInters.clear();
            mHandler.sendEmptyMessage(SEARCH_JOB);
        }
        else if (refreshView.isFooterShown()) {
            pagerIndex++;
            mHandler.sendEmptyMessage(SEARCH_JOB);
        }
    }
}

package app.nahehuo.com.ui.job.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.CompanyJobAdapter;
import app.nahehuo.com.base.GlobalVariables;
import app.nahehuo.com.bean.CompanyJobDict;
import app.nahehuo.com.bean.NetCompanyJob;
import app.nahehuo.com.interfa.LoadMoreListener;
import app.nahehuo.com.network.GsonCallBack;
import app.nahehuo.com.ui.job.CompanyDetailActivity;
import app.nahehuo.com.util.VeDate;
import app.nahehuo.com.view.AutoLoadRecyclerView;
import com.zhy.http.okhttp.OkHttpUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/1/11.
 */
public class CompJobFragment extends Fragment {

    private AutoLoadRecyclerView recycler_view;
    private SwipeRefreshLayout srl_comp_job;

    private CompanyDetailActivity mDetailActivity;
    private CompanyJobAdapter mCompanyJobAdapter;
    private Context mContext;
    private List<CompanyJobDict> mCompanyJobDicts = new ArrayList<>();
    private int pageindex;
    private String mCid;
    private final static int COMPANY_JOB_LIST = 0;
    private Handler mHandler = new Handler() {
        @Override public void handleMessage(Message msg) {
            switch (msg.what) {
                case COMPANY_JOB_LIST:
                    findCompanyJobList();
                    break;
            }
            super.handleMessage(msg);
        }
    };


    private void findCompanyJobList() {
        if(pageindex==1){
            mCompanyJobDicts.clear();
        }
        OkHttpUtils.get()
                   .url(GlobalVariables.COMPANY_JOB_LIST)
                   .addParams("access_token", GlobalVariables.access_token)
                   .addParams("device", GlobalVariables.device)
                   .addParams("cid", mCid)
                   .addParams("pageindex", pageindex + "")
                   .addParams("pagesize", GlobalVariables.pagesize)
                   .build()
                   .execute(new GsonCallBack<NetCompanyJob>(
                           NetCompanyJob.class) {
                       @Override
                       public void onResponse(NetCompanyJob response) {
                           if (response.getCode() == 200) {
                               for (int i = 0;
                                    i < response.getData().size();
                                    i++) {
                                   CompanyJobDict jobDict
                                           = new CompanyJobDict();
                                   jobDict.setJob_name(response.getData()
                                                               .get(i)
                                                               .getPosition());
                                   StringBuffer sb = new StringBuffer();
                                   if (!TextUtils.isEmpty(response.getData()
                                                                  .get(i)
                                                                  .getProv())) {
                                       sb.append(response.getData()
                                                         .get(i)
                                                         .getProv());
                                   }
                                   if (!TextUtils.isEmpty(response.getData()
                                                                  .get(i)
                                                                  .getCity())) {
                                       sb.append(response.getData()
                                                         .get(i)
                                                         .getCity());
                                   }
                                   sb.append(" | ");
                                   sb.append(response.getData()
                                                     .get(i)
                                                     .getWorkexp()
                                                     .equals("0")
                                             ? "工作经验不限"
                                             : response.getData()
                                                       .get(i)
                                                       .getWorkexp());
                                   sb.append(" | ");
                                   sb.append(response.getData()
                                                     .get(i)
                                                     .getEdu()
                                                     .equals("0")
                                             ? "学历不限"
                                             : response.getData()
                                                       .get(i)
                                                       .getEdu());
                                   jobDict.setJob_need(sb.toString());
                                   jobDict.setJob_time(
                                           VeDate.formatHelpReplyTime(
                                                   response.getData()
                                                           .get(i)
                                                           .getPublished()));
                                   jobDict.setJob_wage(response.getData()
                                                               .get(i)
                                                               .getWagemin() +
                                           "k-" + response.getData()
                                                          .get(i)
                                                          .getWagemax() + "k");
                                   mCompanyJobDicts.add(jobDict);
                               }
                               mCompanyJobAdapter.notifyDataSetChanged();
                               if(srl_comp_job.isRefreshing()){
                                   srl_comp_job.setRefreshing(false);
                               }
                           }
                           else {
                               if(srl_comp_job.isRefreshing()){
                                   srl_comp_job.setRefreshing(false);
                               }
                               /*MyToast.showToast(mContext,
                                       response.getMessage());*/
                           }
                           super.onResponse(response);
                       }
                   });
    }


    @Override public void onAttach(Activity activity) {
        if (activity instanceof CompanyDetailActivity) {
            mDetailActivity = (CompanyDetailActivity) activity;
        }
        else {
            throw new IllegalArgumentException(
                    "The activity must be a CompanyDetailActivity !");
        }
        super.onAttach(activity);
    }


    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_comp_job, null);
        mContext = getActivity();
        initView(v);
        mDetailActivity.setConvertToComJob(
                new CompanyDetailActivity.ConvertToComJob() {
                    @Override public void convertToComJob(String cid) {
                        mCid = cid;
                        Log.d("TAG", "CompJobFragment" + mCid);
                        mHandler.sendEmptyMessage(COMPANY_JOB_LIST);
                    }
                });

        return v;
    }


    private void initView(View v) {
        pageindex=1;
        recycler_view = (AutoLoadRecyclerView) v.findViewById(
                R.id.recycler_view);
        srl_comp_job = (SwipeRefreshLayout) v.findViewById(R.id.srl_comp_job);
        mCompanyJobAdapter = new CompanyJobAdapter(mCompanyJobDicts, mContext);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_view.setLayoutManager(layoutManager);
        recycler_view.setAdapter(mCompanyJobAdapter);
        srl_comp_job.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override public void onRefresh() {
                        pageindex = 1;
                        mCompanyJobDicts.clear();
                        mHandler.sendEmptyMessage(COMPANY_JOB_LIST);
                    }
                });
        recycler_view.setLoadMoreListener(new LoadMoreListener() {
            @Override public void loadMore() {
                pageindex++;
                mHandler.sendEmptyMessage(COMPANY_JOB_LIST);
            }
        });
    }
}

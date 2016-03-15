package app.nahehuo.com.ui.job.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import app.nahehuo.com.R;
import app.nahehuo.com.base.GlobalVariables;
import app.nahehuo.com.bean.NetJobSearch;
import app.nahehuo.com.network.GsonCallBack;
import app.nahehuo.com.ui.job.JobSearchActivity;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by WYB on 2016/1/5.
 */
public class JobSearchPosFragment extends Fragment {

    private Context mContext;
    private JobSearchActivity mJobDetailActivity;
    private PullToRefreshListView plv_job;

    private final static int SEARCH_JOB = 0;
    private String keyWord = "";
    private int pagerIndex=1;

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
        OkHttpUtils.get().url(GlobalVariables.JOB_SEARCH)
                .addParams("access_token",GlobalVariables.access_token)
                .addParams("device",GlobalVariables.device)
                .addParams("keyword",keyWord)
                .addParams("pageindex",pagerIndex+"")
                .addParams("pagesize",GlobalVariables.pagesize)
                .build()
                .execute(new GsonCallBack<NetJobSearch>(NetJobSearch.class){
                    @Override public void onResponse(NetJobSearch response) {
                        if(response.getCode()==200){
                            Log.d("TAG",response.getData().get(1).getCompany());
                        }
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
        plv_job = (PullToRefreshListView) contentView.findViewById(
                R.id.plv_job);
        mJobDetailActivity.setConvertData(new JobSearchActivity.ConvertData() {
            @Override public void convertData(String data) {
                keyWord = data;
                mHandler.sendEmptyMessage(SEARCH_JOB);
            }
        });

        return contentView;
    }
}

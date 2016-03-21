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
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.base.GlobalVariables;
import app.nahehuo.com.bean.CompHistory;
import app.nahehuo.com.bean.NetCompProgress;
import app.nahehuo.com.interfa.LoadMoreListener;
import app.nahehuo.com.network.GsonCallBack;
import app.nahehuo.com.ui.job.CompanyDetailActivity;
import app.nahehuo.com.util.TextUtil;
import app.nahehuo.com.view.AutoLoadRecyclerView;
import com.zhy.http.okhttp.OkHttpUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/1/11.
 */
public class CompProgressFragment extends Fragment {

    private SwipeRefreshLayout srl_comp_history;
    private AutoLoadRecyclerView recycler_view;
    private List<CompHistory> mCompHistories = new ArrayList<>();
    private Context mContext;
    private CompProgressAdapter mAdapter;
    private CompanyDetailActivity mCompanyDetailActivity;
    private String mCid;
    private int pageindex;
    private final static int COMPANY_HISTORY = 0;

    private Handler mHandler = new Handler() {
        @Override public void handleMessage(Message msg) {
            switch (msg.what) {
                case COMPANY_HISTORY:
                    findCompanyHistory();
                    break;
            }
            super.handleMessage(msg);
        }
    };


    private void findCompanyHistory() {
        if (pageindex == 1) {
            mCompHistories.clear();
        }
        OkHttpUtils.get()
                   .url(GlobalVariables.COMPANY_HISTORY)
                   .addParams("access_token", GlobalVariables.access_token)
                   .addParams("device", GlobalVariables.device)
                   .addParams("cid", mCid)
                   .addParams("pageindex", pageindex + "")
                   .addParams("pagesize", "5")
                   .build()
                   .execute(new GsonCallBack<NetCompProgress>(
                           NetCompProgress.class) {
                       @Override
                       public void onResponse(NetCompProgress response) {
                           if (response.getCode() == 200) {
                               for (int i = 0;
                                    i < response.getData().size();
                                    i++) {
                                   CompHistory history = new CompHistory();
                                   history.setContent(response.getData()
                                                              .get(i)
                                                              .getTitle());
                                   history.setContent1(response.getData()
                                                               .get(i)
                                                               .getContent());
                                   StringBuffer sb = new StringBuffer();
                                   sb.append(
                                           response.getData().get(i).getDate());
                                   sb.append(" ");
                                   sb.append(
                                           response.getData().get(i).getWeek());

                                   history.setTitle(sb.toString());
                                   mCompHistories.add(history);
                               }
                               mAdapter.notifyDataSetChanged();
                               if (srl_comp_history.isRefreshing()) {
                                   srl_comp_history.setRefreshing(false);
                               }
                           }
                           else {
                               if (srl_comp_history.isRefreshing()) {
                                   srl_comp_history.setRefreshing(false);
                               }
                           }
                           super.onResponse(response);
                       }
                   });
    }


    @Override public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof CompanyDetailActivity) {
            mCompanyDetailActivity = (CompanyDetailActivity) activity;
        }
        else {
            throw new IllegalArgumentException(
                    "The activity must be a CompanyDetailActivity !");
        }
    }


    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_comp_progress, null);
        pageindex = 1;
        mContext = getActivity();
        mAdapter = new CompProgressAdapter(mCompHistories);
        recycler_view = (AutoLoadRecyclerView) v.findViewById(
                R.id.recycler_view);
        srl_comp_history = (SwipeRefreshLayout) v.findViewById(
                R.id.srl_comp_history);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_view.setLayoutManager(layoutManager);
        recycler_view.setAdapter(mAdapter);
        mCompanyDetailActivity.setComHistory(
                new CompanyDetailActivity.ConvertToComHistory() {
                    @Override public void convertToComHistory(String cid) {
                        mCid = cid;
                        mHandler.sendEmptyMessage(COMPANY_HISTORY);
                    }
                });
        srl_comp_history.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override public void onRefresh() {
                        pageindex = 1;
                        mCompHistories.clear();
                        mHandler.sendEmptyMessage(COMPANY_HISTORY);
                    }
                });
        recycler_view.setLoadMoreListener(new LoadMoreListener() {
            @Override public void loadMore() {
                pageindex++;
                mHandler.sendEmptyMessage(COMPANY_HISTORY);
            }
        });
        return v;
    }


    class CompProgressAdapter
            extends RecyclerView.Adapter<CompProgressAdapter.ViewHolder> {

        private List<CompHistory> mTestDatas;


        public CompProgressAdapter(List<CompHistory> testDatas) {
            mTestDatas = testDatas;
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(mContext)
                                   .inflate(R.layout.item_comp_progress, parent,
                                           false);
            return new ViewHolder(v);
        }


        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            CompHistory item = mTestDatas.get(position);
            if (!TextUtil.isEmpty(item.getContent1())) {
                holder.tv_content1.setVisibility(View.VISIBLE);
                holder.tv_content1.setText(item.getContent1());
            }
            holder.tv_title.setText(item.getTitle());
            holder.tv_content.setText(item.getContent());

            if (!TextUtil.isEmpty(item.getContent1())) {
                holder.tv_content1.setText(item.getContent1());
            }
        }


        @Override public int getItemCount() {
            return mTestDatas.size();
        }


        class ViewHolder extends RecyclerView.ViewHolder {
            TextView tv_title;
            TextView tv_content;
            TextView tv_content1;
            View view;


            public ViewHolder(View v) {
                super(v);
                tv_title = (TextView) v.findViewById(R.id.tv_title);
                tv_content1 = (TextView) v.findViewById(R.id.tv_content1);
                tv_content = (TextView) v.findViewById(R.id.tv_content);

                view = v.findViewById(R.id.view);
            }
        }
    }
}

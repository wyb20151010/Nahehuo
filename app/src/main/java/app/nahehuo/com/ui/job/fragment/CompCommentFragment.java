package app.nahehuo.com.ui.job.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.CompCommentAdapter;
import app.nahehuo.com.base.GlobalVariables;
import app.nahehuo.com.bean.CompanyCommentDict;
import app.nahehuo.com.bean.NetComImpression;
import app.nahehuo.com.bean.NetCompComment;
import app.nahehuo.com.bean.TagItem;
import app.nahehuo.com.interfa.LoadMoreListener;
import app.nahehuo.com.network.GsonCallBack;
import app.nahehuo.com.ui.job.CompanyDetailActivity;
import app.nahehuo.com.ui.job.WriteInterExpActivity;
import app.nahehuo.com.util.VeDate;
import app.nahehuo.com.view.AutoLoadRecyclerView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/1/11.
 */
public class CompCommentFragment extends Fragment
        implements TagFlowLayout.OnTagClickListener, View.OnClickListener {

    private TagFlowLayout tfl_company_tag;
    private List<TagItem> mTagItems = new ArrayList<>();
    private List<String> tags=new ArrayList<>();
    private Context mContext;
    private List<CompanyCommentDict> mCommentDicts = new ArrayList<>();
    private CompCommentAdapter mCompCommentAdapter;
    private TagAdapter<List<TagItem>> tagAdapter;

    private AutoLoadRecyclerView recycler_view;
    private SwipeRefreshLayout swipe_refresh_widget;
    private Button btn_comp_comment;
    private CompanyDetailActivity mCompanyDetailActivity;
    private String mCid;
    private int pageindex;
    private final static int COMPANY_IMPRESSION_LIST = 0;
    private final static int COMPANY_COMMENT_LIST = 1;
    private LinearLayout ll_sdsd;
    private Handler mHandler = new Handler() {
        @Override public void handleMessage(Message msg) {
            switch (msg.what) {
                case COMPANY_COMMENT_LIST:
                    findCommentList();
                    break;
                case COMPANY_IMPRESSION_LIST:
                    findImpressionList();
                    break;
            }
            super.handleMessage(msg);
        }
    };


    private void findImpressionList() {
        mTagItems.clear();
        OkHttpUtils.get()
                   .url(GlobalVariables.COMPANY_IMPRESSION)
                   .addParams("access_token", GlobalVariables.access_token)
                   .addParams("device", GlobalVariables.device)
                   .addParams("cid", mCid)
                   .build()
                   .execute(new GsonCallBack<NetComImpression>(
                           NetComImpression.class) {
                       @Override
                       public void onResponse(NetComImpression response) {
                           if (response.getCode() == 200) {
                               for (int i = 0;
                                    i < response.getData().size();
                                    i++) {
                                   TagItem item = new TagItem();
                                   item.setNumber(response.getData()
                                                          .get(i)
                                                          .getEffectnum());
                                   item.setContent(
                                           response.getData().get(i).getName());
                                   item.setUnselected(true);
                                   mTagItems.add(item);
                                   tags.add(response.getData().get(i).getName
                                           ());
                               }
                               tagAdapter.notifyDataChanged();
                           }else {
                               ll_sdsd.setVisibility(View.GONE);
                           }
                           super.onResponse(response);
                       }
                   });
    }


    private void findCommentList() {

        OkHttpUtils.get()
                   .url(GlobalVariables.COMPANY_COMMENT)
                   .addParams("access_token", GlobalVariables.access_token)
                   .addParams("device", GlobalVariables.device)
                   .addParams("cid", mCid)
                   .addParams("pageindex", pageindex + "")
                   .addParams("pagesize", GlobalVariables.pagesize)
                   .build()
                   .execute(new GsonCallBack<NetCompComment>(
                           NetCompComment.class) {
                       @Override
                       public void onResponse(NetCompComment response) {
                           if (response.getCode() == 200) {
                               for (int i = 0;
                                    i < response.getData().size();
                                    i++) {
                                   CompanyCommentDict commentDict
                                           = new CompanyCommentDict();
                                   commentDict.setAvater(response.getData()
                                                                 .get(i)
                                                                 .getAvatar());
                                   commentDict.setComment_time(
                                           VeDate.formatHelpReplyTime(
                                                   response.getData()
                                                           .get(i)
                                                           .getCreated()));
                                   StringBuffer sb = new StringBuffer();
                                   sb.append("描述相符:");
                                   sb.append(response.getData()
                                                     .get(i)
                                                     .getMatchindex());
                                   sb.append(" 公司环境:");
                                   sb.append(response.getData()
                                                     .get(i)
                                                     .getEnvindex());
                                   sb.append(" 工作氛围:");
                                   sb.append(response.getData()
                                                     .get(i)
                                                     .getAuraindex());
                                   commentDict.setScore(sb.toString());
                                   commentDict.setUsername(response.getData()
                                                                   .get(i)
                                                                   .getUsername());
                                   commentDict.setComment_content(
                                           response.getData()
                                                   .get(i)
                                                   .getContent());
                                   commentDict.setAnony(response.getData()
                                                                .get(i)
                                                                .getAnony());
                                   mCommentDicts.add(commentDict);
                               }
                               mCompCommentAdapter.notifyDataSetChanged();
                               if (swipe_refresh_widget.isRefreshing()) {
                                   swipe_refresh_widget.setRefreshing(false);
                               }
                           }
                           else {
                               if (swipe_refresh_widget.isRefreshing()) {
                                   swipe_refresh_widget.setRefreshing(false);
                               }
                           }
                           mHandler.sendEmptyMessage(COMPANY_IMPRESSION_LIST);
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
        View v = inflater.inflate(R.layout.fragment_comp_comment, null);
        pageindex = 1;
        mContext = getActivity();
        btn_comp_comment = (Button) v.findViewById(R.id.btn_comp_comment);
        btn_comp_comment.setOnClickListener(this);
        ll_sdsd= (LinearLayout) v.findViewById(R.id.ll_sdsd);
        initTagFlowLayout(v);
        initPlv(v);
        mCompanyDetailActivity.setComComment(
                new CompanyDetailActivity.ConvertToComComment() {
                    @Override public void convertToComComment(String cid) {
                        mCid = cid;
                        mHandler.sendEmptyMessage(COMPANY_COMMENT_LIST);

                    }
                });
        return v;
    }


    private void initPlv(View v) {
        mCompCommentAdapter = new CompCommentAdapter(mCommentDicts, mContext);
        recycler_view = (AutoLoadRecyclerView) v.findViewById(
                R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_view.setLayoutManager(manager);
        recycler_view.setAdapter(mCompCommentAdapter);
        swipe_refresh_widget = (SwipeRefreshLayout) v.findViewById(
                R.id.swipe_refresh_widget);
        swipe_refresh_widget.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override public void onRefresh() {
                        pageindex = 1;
                        mCommentDicts.clear();
                        mHandler.sendEmptyMessage(COMPANY_COMMENT_LIST);
                    }
                });
        recycler_view.setLoadMoreListener(new LoadMoreListener() {
            @Override public void loadMore() {
                pageindex++;
                mHandler.sendEmptyMessage(COMPANY_COMMENT_LIST);
            }
        });
    }


    private void initTagFlowLayout(View v) {
        tfl_company_tag = (TagFlowLayout) v.findViewById(R.id.tfl_company_tag);
        tagAdapter = new TagAdapter(mTagItems) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                TagItem item = mTagItems.get(position);
                LayoutInflater inflater
                        = (LayoutInflater) mContext.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                View v = inflater.inflate(R.layout.item_tag_red_gray, parent,
                        false);
                TextView tv_tag = (TextView) v.findViewById(R.id.tv_tag);
                TextView tv_number = (TextView) v.findViewById(R.id.tv_number);
                tv_tag.setText(item.getContent());
                tv_number.setText(item.getNumber() + "");
                return v;
            }
        };
        tfl_company_tag.setAdapter(tagAdapter);
        tfl_company_tag.setOnTagClickListener(this);
    }


    @Override
    public boolean onTagClick(View view, int position, FlowLayout parent) {
        TextView textView = (TextView) view.findViewById(R.id.tv_number);
        TagItem item = mTagItems.get(position);
        boolean unselected = item.isUnselected();
        if (unselected) {
            textView.setText((item.getNumber() + 1) + "");
            item.setNumber(item.getNumber() + 1);
            unselected = false;
            item.setUnselected(unselected);
        }
        else {
            textView.setText((item.getNumber() - 1) + "");
            item.setNumber(item.getNumber() - 1);
            unselected = true;
            item.setUnselected(unselected);
        }
        return true;
    }


    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_comp_comment:

                Intent intent=new Intent(mContext, WriteInterExpActivity.class);
                Bundle bundle=new Bundle();
                String [] mTags=new String[tags.size()];
                for(int i=0;i<tags.size();i++){
                    mTags[i]=tags.get(i);
                }
                bundle.putStringArray("tags",mTags);
                bundle.putString("cid",mCid);
                intent.putExtras(bundle);
                startActivity(intent);
                mCompanyDetailActivity.overridePendingTransition(
                        R.anim.push_left_in, R.anim.push_left_out);
                break;
        }
    }
}

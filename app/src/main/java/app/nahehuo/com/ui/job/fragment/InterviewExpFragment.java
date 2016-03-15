package app.nahehuo.com.ui.job.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.InterviewExpAdapter;
import app.nahehuo.com.base.GlobalVariables;
import app.nahehuo.com.bean.CompanyCommentDict;
import app.nahehuo.com.bean.NetInterviewExp;
import app.nahehuo.com.network.GetCallBack;
import app.nahehuo.com.network.HttpConnectService;
import app.nahehuo.com.ui.job.JobDetailActivity;
import app.nahehuo.com.ui.job.WriteInterExpActivity2;
import app.nahehuo.com.util.MyToast;
import app.nahehuo.com.util.VeDate;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by WYB on 2016/1/8.
 */
public class InterviewExpFragment extends Fragment
        implements View.OnClickListener,
        GetCallBack,
        PullToRefreshBase.OnRefreshListener<ListView> {

    private PullToRefreshListView plv_inter_exp;
    private InterviewExpAdapter mInterviewExpAdapter;

    private Context mContext;
    private FloatingActionButton fab_comment;
    private JobDetailActivity mJobDetailActivity;
    private List<CompanyCommentDict> mCommentDicts = new ArrayList<>();
    private int pageindex;
    private String jid;
    private static final int FIND_INTERVIEW = 0;
    private Handler mHandler = new Handler() {
        @Override public void handleMessage(Message msg) {
            switch (msg.what) {
                case FIND_INTERVIEW:
                    findInterview();
                    break;
            }
            super.handleMessage(msg);
        }
    };


    @Override public void onAttach(Activity activity) {
        if (activity instanceof JobDetailActivity) {
            mJobDetailActivity = (JobDetailActivity) activity;
        }
        else {
            throw new IllegalArgumentException(
                    "The activity must be a JobDetailActivity !");
        }
        super.onAttach(activity);
    }


    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_interview_exp, null);
        mContext = getActivity();
        Bundle bundle;
        if (getArguments() != null) {
            bundle = getArguments();
            if (!TextUtils.isEmpty(bundle.getString("jid"))) {
                jid = bundle.getString("jid");
            }
        }

        Log.d("TAG", "InterviewExpFragment:" + jid);

        initPlv(v);
        initView(v);
        pageindex = 1;
        mHandler.sendEmptyMessage(FIND_INTERVIEW);

        return v;
    }


    private void initView(View v) {
        fab_comment = (FloatingActionButton) v.findViewById(R.id.fab_comment);
        fab_comment.setOnClickListener(this);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setHasOptionsMenu(true);
    }


    private void initPlv(View v) {
        plv_inter_exp = (PullToRefreshListView) v.findViewById(
                R.id.plv_inter_exp);
        plv_inter_exp.setMode(PullToRefreshBase.Mode.BOTH);
        plv_inter_exp.setOnRefreshListener(this);
        mInterviewExpAdapter = new InterviewExpAdapter(mCommentDicts, mContext);

        plv_inter_exp.setAdapter(mInterviewExpAdapter);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.empty, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_comment:
                startActivity(
                        new Intent(mContext, WriteInterExpActivity2.class));
                mJobDetailActivity.overridePendingTransition(
                        R.anim.push_left_in, R.anim.push_left_out);
                break;
        }
    }


    private void findInterview() {
        HttpConnectService connectService = new HttpConnectService();
        connectService.setUrl(GlobalVariables.JOB_INTERVIEW + "?access_token=" +
                GlobalVariables.access_token + "&device=" +
                GlobalVariables.device + "&jid=" + jid + "&pageindex=" +
                pageindex + "&pagesize=" + GlobalVariables.pagesize);
        connectService.setResultCode(FIND_INTERVIEW);
        connectService.connectGet(mContext, this, "");
    }


    @Override public void getCallBack(int resultCode, String result) {
        plv_inter_exp.onRefreshComplete();
        if (pageindex == 1) {
            mCommentDicts.clear();
        }
        try {
            Gson gson = new Gson();
            NetInterviewExp response = gson.fromJson(result,
                    NetInterviewExp.class);
            JSONObject jsonObject = new JSONObject(result);
            int code = jsonObject.getInt("code");
            if (code == 200) {
                if (resultCode == FIND_INTERVIEW) {
                    for (int i = 0;
                         i < response.getData().getInterview().size();
                         i++) {
                        CompanyCommentDict dict = new CompanyCommentDict();
                        dict.setComment_time(VeDate.formatHelpReplyTime(response
                                .getData()
                                .getInterview()
                                .get(i)
                                .getCreated()));
                        dict.setComment_title(response.getData()
                                                      .getInterview()
                                                      .get(i)
                                                      .getOneword());
                        dict.setComment_content(response.getData()
                                                        .getInterview()
                                                        .get(i)
                                                        .getDescp());
                        dict.setUsername(response.getData()
                                                 .getInterview()
                                                 .get(i)
                                                 .getUsername());
                        StringBuffer buffer = new StringBuffer();
                        buffer.append("职位相符:");
                        buffer.append(response.getData()
                                              .getInterview()
                                              .get(i)
                                              .getScore());
                        buffer.append(" 面试打分:");
                        buffer.append(response.getData()
                                              .getInterview()
                                              .get(i)
                                              .getJobmatch());
                        dict.setScore(buffer.toString());
                        if ("接到offer并入职( ^_^ )".equals(response.getData()
                                                               .getInterview()
                                                               .get(i)
                                                               .getResult())) {
                            dict.setState(0);
                        }
                        else if ("未接到offer ⊙︿⊙".equals(response.getData()
                                                               .getInterview()
                                                               .get(i)
                                                               .getResult())) {
                            dict.setState(1);
                        }
                        else {
                            dict.setState(2);
                        }
                        dict.setAvater(response.getData()
                                               .getInterview()
                                               .get(i)
                                               .getAvatar());
                        mCommentDicts.add(dict);
                    }
                    mInterviewExpAdapter.notifyDataSetChanged();
                }
            }
            else {
                MyToast.showToast(mContext, response.getMessage());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override public void onRefresh(PullToRefreshBase<ListView> refreshView) {
        if (refreshView.isHeaderShown()) {
            pageindex = 1;
            mCommentDicts.clear();
            mHandler.sendEmptyMessage(FIND_INTERVIEW);
        }
        else if (refreshView.isFooterShown()) {
            pageindex++;
            mHandler.sendEmptyMessage(FIND_INTERVIEW);
        }
    }
}

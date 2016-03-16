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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.InterviewExpAdapter;
import app.nahehuo.com.application.MyApplication;
import app.nahehuo.com.base.GlobalVariables;
import app.nahehuo.com.bean.CompanyCommentDict;
import app.nahehuo.com.bean.NetInterviewExp;
import app.nahehuo.com.bean.NetJobDetail;
import app.nahehuo.com.network.GetCallBack;
import app.nahehuo.com.network.GsonCallBack;
import app.nahehuo.com.network.HttpConnectService;
import app.nahehuo.com.network.JsonObjectCallback;
import app.nahehuo.com.ui.job.JobDeliverySuccessActivity;
import app.nahehuo.com.ui.job.JobDetailActivity;
import app.nahehuo.com.ui.job.WriteInterExpActivity2;
import app.nahehuo.com.ui.job.popup.PopupCommon;
import app.nahehuo.com.util.MyToast;
import app.nahehuo.com.util.VeDate;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.victor.loading.rotate.RotateLoading;
import com.zhy.http.okhttp.OkHttpUtils;
import java.util.ArrayList;
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
    private TextView tv_position, tv_company;
    private ImageView iv_logo;
    private Context mContext;
    private FloatingActionButton fab_comment;
    private JobDetailActivity mJobDetailActivity;
    private RotateLoading mLoading;
    private LinearLayout ll_content;

    private ArrayList<CompanyCommentDict> mCommentDicts = new ArrayList<>();
    private int pageindex;
    private String jid, position;
    private int isapply;
    private PopupCommon mPopupCommon, mPopupCommon1;
    private static final int FIND_INTERVIEW = 0;
    private static final int SHOW_JOB_DETAIL = 1;
    private static final int APPLY_JOB = 2;

    private Handler mHandler = new Handler() {
        @Override public void handleMessage(Message msg) {
            switch (msg.what) {
                case FIND_INTERVIEW:
                    findInterview();
                    break;
                case SHOW_JOB_DETAIL:
                    showJobDetail();
                    break;
                case APPLY_JOB:
                    applyJob();
                    break;
            }
            super.handleMessage(msg);
        }
    };


    private void applyJob() {
        OkHttpUtils.get()
                   .url(GlobalVariables.JOB_APPLY)
                   .addParams("access_token", GlobalVariables.access_token)
                   .addParams("device", GlobalVariables.device)
                   .addParams("jid", jid)
                   .build()
                   .execute(new JsonObjectCallback() {
                       @Override public void onResponse(String response) {
                           try {
                               JSONObject jsonObject = new JSONObject(response);
                               if (jsonObject.getInt("code") == 200) {
                                   startActivity(new Intent(mContext,
                                           JobDeliverySuccessActivity.class));
                               }
                               else {
                                   mPopupCommon1.showPopupWindow();
                                   mPopupCommon1.setSetOnSelect(
                                           new PopupCommon.SetOnSelect() {
                                               @Override
                                               public void onCancel() {
                                                   mPopupCommon1.dismiss();
                                               }


                                               @Override public void onOk() {
                                                   mPopupCommon1.dismiss();
                                                   //TODO 编辑简历
                                               }
                                           });
                               }
                           } catch (JSONException e) {
                               e.printStackTrace();
                           }
                           super.onResponse(response);
                       }
                   });
    }


    private void showJobDetail() {
        OkHttpUtils.get()
                   .url(GlobalVariables.JOB_DETAIL)
                   .addParams("access_token", GlobalVariables.access_token)
                   .addParams("device", GlobalVariables.device)
                   .addParams("jid", jid)
                   .addParams("uid", GlobalVariables.UID)
                   .build()
                   .execute(new GsonCallBack<NetJobDetail>(NetJobDetail.class) {
                       @Override public void onResponse(NetJobDetail response) {
                           if (response.getCode() == 200) {
                               isapply = response.getData()
                                                 .getJob()
                                                 .getIsapply();
                           }
                           else {
                               MyToast.showToast(mContext,
                                       response.getMessage());
                           }
                           super.onResponse(response);
                       }
                   });
    }


    //网络请求
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
                        dict.setAnony(response.getData()
                                              .getInterview()
                                              .get(i)
                                              .getAnony());
                        mCommentDicts.add(dict);
                    }

                    mInterviewExpAdapter.notifyDataSetChanged();
                }
                changeHeadData(response);

            }
            else {
                MyToast.showToast(mContext, response.getMessage());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void changeHeadData(NetInterviewExp response) {
        tv_position.setText(position);
        tv_company.setText(response.getData().getCompany().getCompany());
        ImageLoader.getInstance()
                   .displayImage(response.getData().getCompany().getLogo(),
                           iv_logo, MyApplication.getDisplayDefaultOption());
    }


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
        mPopupCommon = new PopupCommon(mJobDetailActivity, "投递此职位才能发布面试经验",
                "看上这个职位了吗？", "关闭", "投递简历");
        mPopupCommon1 = new PopupCommon(mJobDetailActivity, "简历未完善，不能投递",
                "完善简历才能投递心仪职位", "再逛一会", "去完善");
        Bundle bundle;
        if (getArguments() != null) {
            bundle = getArguments();
            if (!TextUtils.isEmpty(bundle.getString("jid"))) {
                jid = bundle.getString("jid");
                position = bundle.getString("position");
            }
        }
        initPlv(v);
        initView(v);
        pageindex = 1;
        mHandler.sendEmptyMessage(FIND_INTERVIEW);
        mHandler.sendEmptyMessage(SHOW_JOB_DETAIL);
        return v;
    }


    private void initView(View v) {
        fab_comment = (FloatingActionButton) v.findViewById(R.id.fab_comment);
        fab_comment.setOnClickListener(this);
        tv_position = (TextView) v.findViewById(R.id.tv_position);
        tv_company = (TextView) v.findViewById(R.id.tv_company);
        iv_logo = (ImageView) v.findViewById(R.id.iv_logo);
        mLoading= (RotateLoading) v.findViewById(R.id.loading);
        ll_content= (LinearLayout) v.findViewById(R.id.ll_content);
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
                if (isapply == 0) {
                    //TODO 颠倒为了进去
                    Intent intent = new Intent(mContext,
                            WriteInterExpActivity2.class);
                    intent.putExtra("jid", jid);
                    startActivity(intent);
                    mJobDetailActivity.overridePendingTransition(
                            R.anim.push_left_in, R.anim.push_left_out);
                }
                else {
                    //TODO 进入写面试经验的页面
                    mPopupCommon.showPopupWindow();
                    mPopupCommon.setSetOnSelect(new PopupCommon.SetOnSelect() {
                        @Override public void onCancel() {
                            mPopupCommon.dismiss();
                        }


                        @Override public void onOk() {
                            mHandler.sendEmptyMessage(APPLY_JOB);
                            mPopupCommon.dismiss();
                        }
                    });
                }

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


    @Override public void onRefresh(PullToRefreshBase<ListView> refreshView) {
        if (refreshView.isHeaderShown()) {
            pageindex = 1;
            mCommentDicts.clear();
            mHandler.sendEmptyMessage(FIND_INTERVIEW);
        }
        else if (refreshView.isFooterShown() ) {
            if (mCommentDicts.size() < 15) {
                pageindex = 1;
                mCommentDicts.clear();
                mHandler.sendEmptyMessage(FIND_INTERVIEW);
            }
            else {
                pageindex++;
                mHandler.sendEmptyMessage(FIND_INTERVIEW);
            }
        }
    }
}

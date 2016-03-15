package app.nahehuo.com.ui.job.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.application.MyApplication;
import app.nahehuo.com.base.GlobalVariables;
import app.nahehuo.com.bean.NetJobDetail;
import app.nahehuo.com.network.GsonCallBack;
import app.nahehuo.com.network.JsonObjectCallback;
import app.nahehuo.com.ui.job.JobDeliverySuccessActivity;
import app.nahehuo.com.ui.job.JobDetailActivity;
import app.nahehuo.com.ui.job.popup.PopupCommon;
import app.nahehuo.com.util.MyToast;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import io.techery.properratingbar.ProperRatingBar;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by WYB on 2016/1/8.
 */
public class JobDetailFragment extends Fragment
        implements View.OnClickListener {

    private TagFlowLayout tfl_job_detail;
    private TextView tv_job_re, tv_job_type, tv_job_location, tv_wage,
            tv_work_year, tv_city, tv_company_name, tv_industry, tv_scale,
            tv_finance, tv_website;
    private Button btn_delivery_resume;
    private ImageView iv_logo;
    private ProperRatingBar upperRatingBar;

    private TagAdapter<String> adapter;
    private Context mContext;
    private List<String> attractions = new ArrayList<>();
    private String jid;
    private final static int SHOW_JOB_DETAIL = 0;
    private final static int COLLECT_JOB = 1;
    private final static int APPLY_JOB = 2;
    private boolean isApply;
    private PopupCommon mPopupCommon;
    private JobDetailActivity mJobDetailActivity;

    private Handler mHandler = new Handler() {

        @Override public void handleMessage(Message msg) {
            if (msg.what == SHOW_JOB_DETAIL) {
                showJobDetail();
            }
            else if (msg.what == COLLECT_JOB) {
                collectJob();
            }
            else if (msg.what == APPLY_JOB) {
                applyJob();
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
                    "The activity must be a JobInternOrPartActivity !");
        }
        super.onAttach(activity);
    }


    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_job_detail, null);
        mContext = getActivity();
        mPopupCommon = new PopupCommon(mJobDetailActivity, "简历未完善，不能投递",
                "完善简历才能投递心仪职位", "再逛一会", "去完善");
        initView(v);
        initTagFlowLayout();
        Bundle bundle;
        if (getArguments() != null) {
            bundle = getArguments();
            if (!TextUtils.isEmpty(bundle.getString("jid"))) {
                jid = bundle.getString("jid");
            }
        }
        else {
            jid = GlobalVariables.last_jid;
        }
        showJobDetail();
        mHandler.sendEmptyMessage(SHOW_JOB_DETAIL);
        return v;
    }


    private void initView(View v) {
        tfl_job_detail = (TagFlowLayout) v.findViewById(R.id.tfl_job_detail);
        tv_job_re = (TextView) v.findViewById(R.id.tv_job_re);
        btn_delivery_resume = (Button) v.findViewById(R.id.btn_delivery_resume);
        tv_job_type = (TextView) v.findViewById(R.id.tv_job_type);
        tv_job_location = (TextView) v.findViewById(R.id.tv_job_location);
        tv_wage = (TextView) v.findViewById(R.id.tv_wage);
        tv_work_year = (TextView) v.findViewById(R.id.tv_work_year);
        tv_city = (TextView) v.findViewById(R.id.tv_city);
        tv_company_name = (TextView) v.findViewById(R.id.tv_company_name);
        tv_industry = (TextView) v.findViewById(R.id.tv_industry);
        tv_scale = (TextView) v.findViewById(R.id.tv_scale);
        tv_finance = (TextView) v.findViewById(R.id.tv_finance);
        tv_website = (TextView) v.findViewById(R.id.tv_website);
        upperRatingBar = (ProperRatingBar) v.findViewById(R.id.upperRatingBar);
        iv_logo = (ImageView) v.findViewById(R.id.iv_logo);

        btn_delivery_resume.setOnClickListener(this);
    }


    private void initTagFlowLayout() {
        adapter = new TagAdapter<String>(attractions) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                LayoutInflater inflater
                        = (LayoutInflater) mContext.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                View v = inflater.inflate(R.layout.item_tag, parent, false);
                TextView tv_tag = (TextView) v.findViewById(R.id.tv_tag);
                tv_tag.setText(attractions.get(position));
                return v;
            }
        };
        tfl_job_detail.setAdapter(adapter);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override public void onResume() {
        GlobalVariables.last_jid = jid;
        super.onResume();
    }


    @Override public void onStop() {
        GlobalVariables.last_jid = jid;
        super.onStop();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.job_detail, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                break;
            case R.id.collect:
                if (isApply) {

                }
                else {
                    mHandler.sendEmptyMessage(COLLECT_JOB);
                }

                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_delivery_resume:
                mHandler.sendEmptyMessage(APPLY_JOB);

                break;
        }
    }


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
                                   mPopupCommon.showPopupWindow();
                                   mPopupCommon.setSetOnSelect(
                                           new PopupCommon.SetOnSelect() {
                                               @Override
                                               public void onCancel() {
                                                   mPopupCommon.dismiss();
                                               }


                                               @Override public void onOk() {
                                                   mPopupCommon.dismiss();
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


    private void collectJob() {
        OkHttpUtils.get()
                   .url(GlobalVariables.JOB_COLLECT)
                   .addParams("access_token", GlobalVariables.access_token)
                   .addParams("device", GlobalVariables.device)
                   .addParams("jid", jid)
                   .build()
                   .execute(new JsonObjectCallback() {
                       @Override public void onResponse(String response) {
                           try {
                               JSONObject jsonObject = new JSONObject(response);
                               if (jsonObject.getInt("code") == 200) {
                                   MyToast.showToast(mContext,
                                           jsonObject.getString("message"));
                               }
                               else {
                                   MyToast.showToast(mContext,
                                           jsonObject.getString("message"));
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
                               initData(response);
                           }
                           else {
                               MyToast.showToast(mContext,
                                       response.getMessage());
                           }
                           super.onResponse(response);
                       }
                   });
    }


    private void initData(NetJobDetail response) {
        tv_job_type.setText(response.getData().getJob().getPosition());
        tv_job_location.setText(response.getData().getCompany().getCompany());
        tv_wage.setText(response.getData().getJob().getWagemin() + "k-" +
                response.getData().getJob().getWagemax() +
                "k");
        tv_work_year.setText(
                "0".equals(response.getData().getJob().getWorkexp())
                ? "工作经验不限"
                : response.getData().getJob().getWorkexp());
        tv_city.setText(response.getData().getJob().getProv() +
                response.getData().getJob().getCity());

        attractions.clear();
        attractions.add(
                response.getData().getJob().getType() == 1 ? "全职" : "实习");
        attractions.add(response.getData().getJob().getPositiontype());
        attractions.add("0".equals(response.getData().getJob().getEdu())
                        ? "教育经验不限"
                        : response.getData().getJob().getEdu());
        if (response.getData().getJob().getAttraction().contains("，")) {
            for (String each : response.getData()
                                       .getJob()
                                       .getAttraction()
                                       .split("，")) {
                attractions.add(each);
            }
        }
        else if (response.getData().getJob().getAttraction().contains("、")) {
            for (String each : response.getData()
                                       .getJob()
                                       .getAttraction()
                                       .split("、")) {
                attractions.add(each);
            }
        }
        adapter.notifyDataChanged();

        ImageLoader.getInstance()
                   .displayImage(response.getData().getCompany().getLogo(),
                           iv_logo, MyApplication.getDisplayDefaultOption());
        tv_job_re.setText(response.getData().getJob().getDescp());
        tv_company_name.setText(response.getData().getCompany().getCompany());
        tv_industry.setText(
                "行业 " + response.getData().getCompany().getIndustry());
        tv_scale.setText("规模 " +
                ("0".equals(response.getData().getCompany().getSize())
                 ? "1-10人"
                 : response.getData().getCompany().getSize()));
        tv_finance.setText("融资 " +
                ("0".equals(response.getData().getCompany().getFinancle())
                 ? "未融资"
                 : response.getData().getCompany().getFinancle()));
        if (!TextUtils.isEmpty(response.getData().getCompany().getWebsite())) {
            tv_website.setText(
                    "网站 " + response.getData().getCompany().getWebsite());
        }
        else {
            tv_website.setText("网站 暂无");
        }
        if (response.getData().getJob().getIsapply() != 0) {
            btn_delivery_resume.setText("已投递");
            btn_delivery_resume.setClickable(false);
        }
        upperRatingBar.setRating(
                response.getData().getCompany().getAvgcomment());
        isApply = response.getData().getJob().getIsapply() == 0 ? false : true;
    }
}

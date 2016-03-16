package app.nahehuo.com.ui.job.fragment;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.CompanyContentAdapter;
import app.nahehuo.com.adapter.CompanyTitleAdapter;
import app.nahehuo.com.base.GlobalVariables;
import app.nahehuo.com.bean.JobContentDict;
import app.nahehuo.com.bean.NetCompanyList;
import app.nahehuo.com.bean.NetRecomCompany;
import app.nahehuo.com.bean.RecomJob;
import app.nahehuo.com.network.GsonCallBack;
import app.nahehuo.com.ui.MainActivity;
import app.nahehuo.com.ui.job.CompanyDetailActivity;
import app.nahehuo.com.util.MyToast;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.victor.loading.rotate.RotateLoading;
import com.zhy.http.okhttp.OkHttpUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2015/12/24.
 */
public class JobCompanyFragement extends Fragment
        implements AdapterView.OnItemClickListener,
        PullToRefreshBase.OnRefreshListener<ListView>,
        View.OnClickListener {

    private Context mContext;
    private MainActivity mainActivity;
    private RecyclerView rv_title;
    private PullToRefreshListView plv_company_job;
    private RotateLoading mLoading;
    private RelativeLayout rl_content;
    private RelativeLayout rl_company_title;
    private LinearLayout ll_company_title;
    private TextView tv_city, tv_finance, tv_industry, tv_size;

    private CompanyTitleAdapter mCompanyTitleAdapter;
    private CompanyContentAdapter mCompanyContentAdapter;
    private List<RecomJob> mJobListDicts = new ArrayList<>();
    private List<JobContentDict> mJobContentDicts = new ArrayList<>();
    private boolean isHideShowing = false;
    private boolean isShowing = false;
    private final static int RECOMMEND_COMPANY = 0;
    private final static int COMMPANY_LIST = 1;
    private int pageIndex;
    private String city, finance, industry, size;

    private Handler mHandler = new Handler() {
        @Override public void handleMessage(Message msg) {
            switch (msg.what) {
                case RECOMMEND_COMPANY:
                    findRecomCompany();
                    break;
                case COMMPANY_LIST:
                    findCompanyList();
                    break;
            }
            super.handleMessage(msg);
        }
    };


    private void findCompanyList() {
        plv_company_job.onRefreshComplete();
        if (pageIndex == 1) {
            mJobContentDicts.clear();
        }
        OkHttpUtils.get()
                   .url(GlobalVariables.COMPANY_SEARCH)
                   .addParams("access_token", GlobalVariables.access_token)
                   .addParams("device", GlobalVariables.device)
                   .addParams("area", "")
                   .addParams("financle", "")
                   .addParams("industry", "")
                   .addParams("size", "")
                   .addParams("pageindex", pageIndex + "")
                   .addParams("pagesize", GlobalVariables.pagesize)
                   .build()
                   .execute(new GsonCallBack<NetCompanyList>(
                           NetCompanyList.class) {
                       @Override
                       public void onResponse(NetCompanyList response) {
                           if (response.getCode() == 200) {
                               for (int i = 0;
                                    i < response.getData().size();
                                    i++) {
                                   JobContentDict contentDict
                                           = new JobContentDict();
                                   contentDict.setAvatar(
                                           response.getData().get(i).getLogo());
                                   contentDict.setPosition(
                                           response.getData().get(i).getName());
                                   List<String> tags = new ArrayList<String>();
                                   if (!TextUtils.isEmpty(response.getData()
                                                                  .get(i)
                                                                  .getIndustry())) {
                                       tags.add(response.getData()
                                                        .get(i)
                                                        .getIndustry()
                                                        .equals("0")
                                                ? "行业未知"
                                                : response.getData()
                                                          .get(i)
                                                          .getIndustry());
                                   }

                                   tags.add(response.getData()
                                                    .get(i)
                                                    .getSize()
                                                    .equals("0")
                                            ? "0-10人"
                                            : response.getData()
                                                      .get(i)
                                                      .getSize());
                                   tags.add(response.getData()
                                                    .get(i)
                                                    .getFinancle()
                                                    .equalsIgnoreCase("0")
                                            ? "未融资"
                                            : response.getData()
                                                      .get(i)
                                                      .getFinancle());
                                   if (!TextUtils.isEmpty(response.getData()
                                                                  .get(i)
                                                                  .getProv()) ||
                                           !TextUtils.isEmpty(response.getData()
                                                                      .get(i)
                                                                      .getCity())) {
                                       tags.add(response.getData()
                                                        .get(i)
                                                        .getProv() +
                                               response.getData()
                                                       .get(i)
                                                       .getCity());
                                   }

                                   contentDict.setTags(tags);
                                   contentDict.setCid(
                                           response.getData().get(i).getCid());
                                   mJobContentDicts.add(contentDict);
                               }
                               mCompanyContentAdapter.notifyDataSetChanged();
                               mLoading.stop();
                               rl_content.setVisibility(View.VISIBLE);
                           }
                           else {
                               MyToast.showToast(mContext,
                                       response.getMessage());
                           }
                           super.onResponse(response);
                       }
                   });
    }


    private void findRecomCompany() {
        OkHttpUtils.get()
                   .url(GlobalVariables.RECOMMEND_COMPANY)
                   .addParams("access_token", GlobalVariables.access_token)
                   .addParams("device", GlobalVariables.device)
                   .build()
                   .execute(new GsonCallBack<NetRecomCompany>(
                           NetRecomCompany.class) {
                       @Override
                       public void onResponse(NetRecomCompany response) {
                           if (response.getCode() == 200) {
                               for (int i = 0;
                                    i < response.getData().size();
                                    i++) {
                                   RecomJob recomJob = new RecomJob();
                                   recomJob.setCompany(
                                           response.getData().get(i).getName());
                                   recomJob.setLogo(
                                           response.getData().get(i).getLogo());
                                   recomJob.setCid(response.getData().get(i)
                                           .getCid());
                                   mJobListDicts.add(recomJob);
                               }
                               mCompanyTitleAdapter.notifyDataSetChanged();
                               mHandler.sendEmptyMessage(COMMPANY_LIST);
                           }
                           super.onResponse(response);
                       }
                   });
    }


    @Override public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof MainActivity) {
            mainActivity = (MainActivity) activity;
        }
        else {
            throw new IllegalArgumentException(
                    "The activity must be a MainActivity !");
        }
    }


    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_company, null);
        mContext = getActivity();

        pageIndex = 1;
        tv_city = (TextView) v.findViewById(R.id.tv_city);
        tv_finance = (TextView) v.findViewById(R.id.tv_finance);
        tv_industry = (TextView) v.findViewById(R.id.tv_industry);
        tv_size = (TextView) v.findViewById(R.id.tv_size);
        tv_city.setOnClickListener(this);
        tv_finance.setOnClickListener(this);
        tv_industry.setOnClickListener(this);
        tv_size.setOnClickListener(this);

        rl_content = (RelativeLayout) v.findViewById(R.id.rl_content);
        mLoading = (RotateLoading) v.findViewById(R.id.loading);
        mLoading.start();
        rv_title = (RecyclerView) v.findViewById(R.id.rv_title);
        mCompanyTitleAdapter = new CompanyTitleAdapter(mContext, mJobListDicts);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_title.setLayoutManager(linearLayoutManager);
        rv_title.setAdapter(mCompanyTitleAdapter);

        rl_company_title = (RelativeLayout) v.findViewById(
                R.id.rl_company_title);
        ll_company_title = (LinearLayout) v.findViewById(R.id.ll_company_title);

        initPlv(v);
        mHandler.sendEmptyMessage(RECOMMEND_COMPANY);
        mCompanyTitleAdapter.setSetOnItemClickListener(
                new CompanyTitleAdapter.SetOnItemClickListener() {
                    @Override public void setOnItemClickListener(int position) {
                        Intent intent = new Intent(mContext,
                                CompanyDetailActivity.class);
                        intent.putExtra("cid",
                                mJobListDicts.get(position).getCid());
                        startActivity(intent);
                        mainActivity.overridePendingTransition(
                                R.anim.push_left_in, R.anim.push_left_out);
                    }
                });
        return v;
    }


    private void initPlv(final View v) {
        mCompanyContentAdapter = new CompanyContentAdapter(mContext,
                mJobContentDicts);
        plv_company_job = (PullToRefreshListView) v.findViewById(
                R.id.plv_company_job);
        plv_company_job.setMode(PullToRefreshBase.Mode.BOTH);
        plv_company_job.setAdapter(mCompanyContentAdapter);
        plv_company_job.setOnRefreshListener(this);
        plv_company_job.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent intent = new Intent(mContext,
                                CompanyDetailActivity.class);
                        intent.putExtra("cid",
                                mJobContentDicts.get(position - 1).getCid());
                        startActivity(intent);
                        mainActivity.overridePendingTransition(
                                R.anim.push_left_in, R.anim.push_left_out);
                    }
                });

        plv_company_job.setOnScrollListener(new AbsListView.OnScrollListener() {
            boolean isIdle;
            int firstItem;


            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (isIdle = (scrollState == SCROLL_STATE_IDLE)) {
                    firstItem = 0;
                }
            }


            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                firstItem = firstVisibleItem;
                if (firstItem > 1) {
                    hideTitleAnim();
                }
            }
        });
    }


    private void showTitleAnim() {
        if (!isShowing) {
            rl_company_title.setVisibility(View.VISIBLE);
            ObjectAnimator animator = ObjectAnimator.ofFloat(rl_company_title,
                    "translationY", 0);
            animator.setDuration(300);
            animator.start();
            animator.addListener(new Animator.AnimatorListener() {
                @Override public void onAnimationStart(Animator animation) {

                }


                @Override public void onAnimationEnd(Animator animation) {
                    isShowing = true;
                    isHideShowing = false;
                }


                @Override public void onAnimationCancel(Animator animation) {

                }


                @Override public void onAnimationRepeat(Animator animation) {

                }
            });
        }
    }


    private void hideTitleAnim() {
        if (!isHideShowing) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(rl_company_title,
                    "translationY", -500);
            animator.setDuration(300);
            animator.start();
            animator.addListener(new Animator.AnimatorListener() {
                @Override public void onAnimationStart(Animator animation) {

                }


                @Override public void onAnimationEnd(Animator animation) {
                    rl_company_title.setVisibility(View.GONE);
                    isHideShowing = true;
                    isShowing = false;
                }


                @Override public void onAnimationCancel(Animator animation) {

                }


                @Override public void onAnimationRepeat(Animator animation) {

                }
            });
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(mContext, CompanyDetailActivity.class));
    }


    @Override public void onRefresh(PullToRefreshBase<ListView> refreshView) {
        if (refreshView.isHeaderShown()) {
            mJobContentDicts.clear();
            pageIndex = 1;
            mHandler.sendEmptyMessage(COMMPANY_LIST);
            showTitleAnim();
        }
        else if (refreshView.isFooterShown()) {
            pageIndex++;
            mHandler.sendEmptyMessage(COMMPANY_LIST);
        }
    }


    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_city:
                break;
            case R.id.tv_finance:
                break;
            case R.id.tv_industry:
                break;
            case R.id.tv_size:
                break;
        }
    }
}

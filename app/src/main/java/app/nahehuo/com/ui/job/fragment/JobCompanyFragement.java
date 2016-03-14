package app.nahehuo.com.ui.job.fragment;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.CompanyContentAdapter;
import app.nahehuo.com.adapter.CompanyTitleAdapter;
import app.nahehuo.com.bean.JobContentDict;
import app.nahehuo.com.bean.RecomJob;
import app.nahehuo.com.ui.MainActivity;
import app.nahehuo.com.ui.job.CompanyDetailActivity;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by WYB on 2015/12/24.
 */
public class JobCompanyFragement extends Fragment
        implements AdapterView.OnItemClickListener {

    private RecyclerView rv_title;
    private Context mContext;
    private MainActivity mainActivity;
    private List<RecomJob> mJobListDicts = new ArrayList<>();
    private List<JobContentDict> mJobContentDicts = new ArrayList<>();
    private PullToRefreshListView plv_company_job;
    private int pageIndex = 1;
    private CompanyContentAdapter mCompanyContentAdapter;
    private RelativeLayout rl_company_title;
    private LinearLayout ll_company_title;
    private boolean isHideShowing = false;
    private boolean isShowing = false;


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
        initData();
        rv_title = (RecyclerView) v.findViewById(R.id.rv_title);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_title.setLayoutManager(linearLayoutManager);
        rv_title.setAdapter(new CompanyTitleAdapter(mContext, mJobListDicts));
        rl_company_title = (RelativeLayout) v.findViewById(
                R.id.rl_company_title);
        ll_company_title = (LinearLayout) v.findViewById(R.id.ll_company_title);

        initPlv(v);
        return v;
    }


    private void initPlv(final View v) {
        mCompanyContentAdapter = new CompanyContentAdapter(mContext,
                mJobContentDicts);
        plv_company_job = (PullToRefreshListView) v.findViewById(
                R.id.plv_company_job);
        plv_company_job.setMode(PullToRefreshBase.Mode.BOTH);
        plv_company_job.setAdapter(mCompanyContentAdapter);
        plv_company_job.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        startActivity(new Intent(mContext,
                                CompanyDetailActivity.class));
                        mainActivity.overridePendingTransition(
                                R.anim.push_left_in, R.anim.push_left_out);
                    }
                });

        plv_company_job.setAdapter(mCompanyContentAdapter);
        plv_company_job.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                showTitleAnim();
                plv_company_job.onRefreshComplete();
            }


            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                plv_company_job.onRefreshComplete();
            }
        });
       /* plv_company_job.setOnRefreshListener(
                new PullToRefreshBase.OnRefreshListener<ListView>() {
                    @Override
                    public void onRefresh(PullToRefreshBase<ListView> refreshView) {

                    }
                });*/
  /*      plv_company_job.setOnScrollListener(
                new RecyclerView.OnScrollListener() {

                    boolean isIdle;
                    int scrollY;


                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        scrollY += dy;
                        if (scrollY > 4) {
                            hideTitleAnim();
                        }
                        else {
                            showTitleAnim();
                        }
                        super.onScrolled(recyclerView, dx, dy);
                    }


                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                        if (isIdle = (newState ==
                                RecyclerView.SCROLL_STATE_IDLE)) {
                            scrollY = 0;
                        }
                        super.onScrollStateChanged(recyclerView, newState);
                    }
                });*/
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


    private void initData() {
        RecomJob jobListDict = new RecomJob();
        jobListDict.setLogo(
                "http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg");
        jobListDict.setCompany("今翌信息科技有限公司");
        jobListDict.setPosition("高级PHP开发工程师");
        mJobListDicts.add(jobListDict);
        RecomJob jobListDict1 = new RecomJob();
        jobListDict1.setLogo(
                "http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg");
        jobListDict1.setCompany("腾讯科技有限公司");
        jobListDict1.setPosition("中级微信开发工程师");
        mJobListDicts.add(jobListDict1);
        RecomJob jobListDict2 = new RecomJob();
        jobListDict2.setLogo(
                "http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg");
        jobListDict2.setCompany("百度科技有限公司");
        jobListDict2.setPosition("初级IOS开发工程师");
        mJobListDicts.add(jobListDict2);

        RecomJob jobListDict3 = new RecomJob();
        jobListDict3.setLogo(
                "http://www.nahehuo.com/thumb/a/f/f9/32499_middle.jpg");
        jobListDict3.setCompany("福建省金冠银商品经营有限公司");
        jobListDict3.setPosition("渠道部投资顾问");
        mJobListDicts.add(jobListDict3);
        RecomJob jobListDict4 = new RecomJob();
        jobListDict4.setLogo(
                "http://www.nahehuo.com/thumb/a/f/f9/32499_middle.jpg");
        jobListDict4.setCompany("福建省金冠银商品经营有限公司");
        jobListDict4.setPosition("渠道部投资顾问");
        mJobListDicts.add(jobListDict4);

        JobContentDict jobContentDict = new JobContentDict();
        jobContentDict.setPosition("渠道部投资顾问");
        jobContentDict.setAvatar(
                "http://www.nahehuo.com/thumb/a/f/f9/32499_middle.jpg");
        jobContentDict.setTags(Arrays.asList(
                new String[] { "五险一金", "员工持股", "团队旅游", "全职", "技术研发", "本科及以上",
                        "人傻钱多速来" }));
        mJobContentDicts.add(jobContentDict);

        JobContentDict jobContentDict1 = new JobContentDict();
        jobContentDict1.setPosition("渠道部投资顾问");
        jobContentDict1.setAvatar(
                "http://www.nahehuo.com/thumb/a/f/f9/32499_middle.jpg");
        jobContentDict1.setTags(
                Arrays.asList(new String[] { "全职", "技术研发", "本科及以上", "美女多" }));
        mJobContentDicts.add(jobContentDict1);

        JobContentDict jobContentDict2 = new JobContentDict();
        jobContentDict2.setPosition("渠道部投资顾问");
        jobContentDict2.setAvatar(
                "http://www.nahehuo.com/thumb/a/f/f9/32499_middle.jpg");
        jobContentDict2.setTags(
                Arrays.asList(new String[] { "团队旅游", "全职", "技术研发", "本科及以上" }));
        mJobContentDicts.add(jobContentDict2);

        JobContentDict jobContentDict3 = new JobContentDict();
        jobContentDict3.setPosition("渠道部投资顾问");
        jobContentDict3.setAvatar(
                "http://www.nahehuo.com/thumb/a/f/f9/32499_middle.jpg");
        jobContentDict3.setTags(
                Arrays.asList(new String[] { "团队旅游", "全职", "技术研发", "本科及以上" }));
        mJobContentDicts.add(jobContentDict3);

        JobContentDict jobContentDict4 = new JobContentDict();
        jobContentDict4.setPosition("渠道部投资顾问");
        jobContentDict4.setAvatar(
                "http://www.nahehuo.com/thumb/a/f/f9/32499_middle.jpg");
        jobContentDict4.setTags(
                Arrays.asList(new String[] { "团队旅游", "全职", "技术研发", "本科及以上" }));
        mJobContentDicts.add(jobContentDict4);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(mContext, CompanyDetailActivity.class));
    }
}

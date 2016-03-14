package app.nahehuo.com.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.ui.MainActivity;
import app.nahehuo.com.ui.job.fragment.JobCompanyFragement;
import app.nahehuo.com.ui.job.fragment.JobSwipeFragment;

/**
 * Created by WYB on 2015/12/28.
 */
public class JobToolBarFragment extends Fragment
        implements View.OnClickListener {

    private MainActivity mainActivity;
    private TextView tv_job, tv_company;
    private int textWidth;
    private Context mContext;


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
        View v = inflater.inflate(R.layout.toolbar_job, null);
        mContext = getActivity();
        replaceFragment(R.id.fl_job_content, new JobSwipeFragment());
        initToolBar(v);
        initView(v);
        return v;
    }


    private void initView(View v) {
        tv_job = (TextView) v.findViewById(R.id.tv_job);
        tv_job.setOnClickListener(this);
        tv_company = (TextView) v.findViewById(R.id.tv_company);
        tv_company.setOnClickListener(this);
    }


    private void initToolBar(View v) {
        Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar);
        mainActivity.setSupportActionBar(toolbar);
        ActionBar actionBar = mainActivity.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayShowTitleEnabled(false);
    }

    /*

    private void calculateWidth() {
        tv_job.getViewTreeObserver()
              .addOnGlobalLayoutListener(
                      new ViewTreeObserver.OnGlobalLayoutListener() {
                          @Override public void onGlobalLayout() {
                              textWidth = tv_job.getWidth();
                              Log.d("TAG", "" + textWidth);
                              LinearLayout.LayoutParams layoutParams
                                      = new LinearLayout.LayoutParams(
                                      LinearLayout.LayoutParams.MATCH_PARENT,
                                      LinearLayout.LayoutParams.WRAP_CONTENT);
                              layoutParams.width = textWidth;
                              cursor.setLayoutParams(layoutParams);
                              tv_job.getViewTreeObserver()
                                    .removeGlobalOnLayoutListener(this);
                          }
                      });
    }
    */


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_job:
                replaceFragment(R.id.fl_job_content, new JobSwipeFragment());
                tv_company.setTextColor(
                        getResources().getColor(R.color.jobtitlecolor));
                tv_company.setTextSize(18);
                tv_job.setTextColor(getResources().getColor(R.color.white));
                tv_job.setTextSize(20);
                break;
            case R.id.tv_company:
                replaceFragment(R.id.fl_job_content, new JobCompanyFragement());
                tv_company.setTextColor(getResources().getColor(R.color.white));
                tv_company.setTextSize(20);
                tv_job.setTextColor(
                        getResources().getColor(R.color.jobtitlecolor));
                tv_job.setTextSize(18);
                break;
        }
    }


  /*  private void startAnim2() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(cursor, "translationX",
                0);
        animator.setDuration(300);
        animator.start();
        animator.addListener(new Animator.AnimatorListener() {
            @Override public void onAnimationStart(Animator animation) {

            }


            @Override public void onAnimationEnd(Animator animation) {

            }


            @Override public void onAnimationCancel(Animator animation) {

            }


            @Override public void onAnimationRepeat(Animator animation) {

            }
        });
    }


    private void startAnim() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(cursor, "translationX",
                DpPxUtil.dip2px(mContext, 20) + textWidth);
        animator.setDuration(300);
        animator.start();
        animator.addListener(new Animator.AnimatorListener() {
            @Override public void onAnimationStart(Animator animation) {

            }


            @Override public void onAnimationEnd(Animator animation) {

            }


            @Override public void onAnimationCancel(Animator animation) {

            }


            @Override public void onAnimationRepeat(Animator animation) {

            }
        });
    }
*/


    public void replaceFragment(int id_content, Fragment fragment) {
        FragmentTransaction transaction
                = mainActivity.getSupportFragmentManager().beginTransaction();
        transaction.replace(id_content, fragment);
        transaction.commit();
    }
}

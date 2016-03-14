package app.nahehuo.com.ui.partner.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.ui.partner.PartnerApplyActivity;
import app.nahehuo.com.ui.partner.PartnerBaseInfoActivity;
import app.nahehuo.com.ui.partner.PartnerContactWayActivity;
import app.nahehuo.com.ui.partner.PartnerEduExpActivity;
import app.nahehuo.com.ui.partner.PartnerWorkExpActivity;
import app.nahehuo.com.util.DpPxUtil;

/**
 * Created by WYB on 2016/2/23.
 */
public class PartnerApply1Fragment extends Fragment
        implements View.OnClickListener {

    private LinearLayout ll_title;
    private PartnerApplyActivity mApplyActivity;
    private int width;
    private CardView cv_base_info, cv_contact_way, cv_work_exp, cv_edu_exp;
    private Context mContext;
    private int type;
    private Toolbar mToolbar;
    private TextView tv_title;

    @Override public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof PartnerApplyActivity) {
            mApplyActivity = (PartnerApplyActivity) activity;
        }
        else {
            throw new IllegalArgumentException(
                    "The activity must be a MainActivity !");
        }
    }


    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments().containsKey(PartnerApplyActivity.TYPE)){
            type=getArguments().getInt(PartnerApplyActivity.TYPE);
        }
    }


    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_partner_apply1, null);
        mContext = getActivity();
        WindowManager wm = (WindowManager) mApplyActivity.getSystemService(
                Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        initView(view);
        initToolbar(view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }


    private void initToolbar(View v) {
        mToolbar = (Toolbar) v.findViewById(R.id.toolbar);
        mApplyActivity.setSupportActionBar(mToolbar);
        final ActionBar actionBar = mApplyActivity.getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initView(View v) {
        tv_title = (TextView) v.findViewById(R.id.tv_title);
        tv_title.setText("申请成为合伙人");
        ll_title = (LinearLayout) v.findViewById(R.id.ll_title);
        ll_title.getViewTreeObserver()
                .addOnGlobalLayoutListener(
                        new ViewTreeObserver.OnGlobalLayoutListener() {
                            @Override public void onGlobalLayout() {
                                LinearLayout.LayoutParams layoutParams
                                        = new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                        LinearLayout.LayoutParams.MATCH_PARENT);
                                layoutParams.width = (width -
                                        DpPxUtil.dip2px(mContext, 20)) * type /
                                        4;
                                ll_title.setLayoutParams(layoutParams);
                                ll_title.getViewTreeObserver()
                                        .removeGlobalOnLayoutListener(this);
                            }
                        });
        cv_base_info = (CardView) v.findViewById(R.id.cv_base_info);
        cv_base_info.setOnClickListener(this);
        cv_contact_way = (CardView) v.findViewById(R.id.cv_contact_way);
        cv_contact_way.setOnClickListener(this);
        cv_work_exp = (CardView) v.findViewById(R.id.cv_work_exp);
        cv_work_exp.setOnClickListener(this);
        cv_edu_exp = (CardView) v.findViewById(R.id.cv_edu_exp);
        cv_edu_exp.setOnClickListener(this);
    }


    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cv_base_info:
                startActivity(
                        new Intent(mContext, PartnerBaseInfoActivity.class));
                mApplyActivity.overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.cv_contact_way:
                startActivity(
                        new Intent(mContext, PartnerContactWayActivity.class));
                mApplyActivity.overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.cv_work_exp:
                startActivity(
                        new Intent(mContext, PartnerWorkExpActivity.class));
                mApplyActivity.overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.cv_edu_exp:
                startActivity(
                        new Intent(mContext, PartnerEduExpActivity.class));
                mApplyActivity.overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
        }
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mApplyActivity.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

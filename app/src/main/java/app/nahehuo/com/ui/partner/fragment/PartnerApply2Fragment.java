package app.nahehuo.com.ui.partner.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.ui.partner.PartnerApplyActivity;
import app.nahehuo.com.ui.partner.PartnerSwiLevelActivity;
import app.nahehuo.com.ui.partner.popup.HelpShowPopup;
import app.nahehuo.com.ui.partner.popup.IDShowPopup;
import app.nahehuo.com.util.TextUtil;

/**
 * Created by WYB on 2016/2/23.
 */
public class PartnerApply2Fragment extends Fragment
        implements View.OnClickListener {

    private Toolbar mToolbar;
    private TextView tv_title;
    private PartnerApplyActivity mApplyActivity;
    private ImageView iv_enlarge, iv_help;
    private EditText et_id;
    private IDShowPopup mIDShowPopup;
    private HelpShowPopup mHelpShowPopup;
    private Context mContext;


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


    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_partner_apply2, null);
        mContext = getActivity();
        initView(v);
        initToolbar(v);
        return v;
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
        iv_enlarge = (ImageView) v.findViewById(R.id.iv_enlarge);
        iv_enlarge.setOnClickListener(this);
        iv_help = (ImageView) v.findViewById(R.id.iv_help);
        iv_help.setOnClickListener(this);
        et_id = (EditText) v.findViewById(R.id.et_id);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.save, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                startActivity(
                        new Intent(mContext, PartnerSwiLevelActivity.class));
                break;
            case android.R.id.home:
                mApplyActivity.finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_enlarge:
                if (!TextUtil.isEmpty(et_id.getText().toString())) {
                    mIDShowPopup = new IDShowPopup(mApplyActivity,
                            et_id.getText().toString());
                    mIDShowPopup.showPopupWindow();
                }
                break;
            case R.id.iv_help:
                mHelpShowPopup = new HelpShowPopup(mApplyActivity);
                mHelpShowPopup.showPopupWindow();
                break;
        }
    }
}

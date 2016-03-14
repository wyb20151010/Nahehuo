package app.nahehuo.com.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import app.nahehuo.com.R;
import app.nahehuo.com.ui.MainActivity;
import app.nahehuo.com.ui.partner.PartnerInfoActivity;
import app.nahehuo.com.ui.personal.PersonalMy2VipActivity;
import app.nahehuo.com.ui.personal.PersonalMyCollectActivity;
import app.nahehuo.com.ui.personal.PersonalMyEventActivity;
import app.nahehuo.com.ui.personal.PersonalMyProjectActivity;
import app.nahehuo.com.ui.personal.PersonalMyResuActivity;
import app.nahehuo.com.ui.personal.PersonalMySetActivity;
import app.nahehuo.com.ui.personal.PersonalMyWalletActivity;
import app.nahehuo.com.ui.personal.PersonalMyQRCodeActivity;
import app.nahehuo.com.ui.personal.PersonalMyInterviewActivity;

/**
 * Created by WYB on 2015/12/24.
 */
public class PersonalFragment extends Fragment implements View.OnClickListener {

    private Context mContext;
    private Toolbar mToolbar;
    private MainActivity mainActivity;
    private RelativeLayout rl_qr_code, rl_to_vip, rl_wallet, rl_collect,rl_set;
    private LinearLayout ll_person, ll_interview, ll_resume, ll_project,
            ll_event;


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
        View v = inflater.inflate(R.layout.fragment_personal, null);
        mContext = getActivity();
        initView(v);
        return v;
    }


    private void initView(View v) {
        rl_set= (RelativeLayout) v.findViewById(R.id.rl_set);
        rl_set.setOnClickListener(this);
        rl_collect = (RelativeLayout) v.findViewById(R.id.rl_collect);
        rl_collect.setOnClickListener(this);
        rl_qr_code = (RelativeLayout) v.findViewById(R.id.rl_qr_code);
        rl_qr_code.setOnClickListener(this);
        rl_to_vip = (RelativeLayout) v.findViewById(R.id.rl_to_vip);
        rl_to_vip.setOnClickListener(this);
        ll_person = (LinearLayout) v.findViewById(R.id.ll_person);
        ll_person.setOnClickListener(this);
        rl_wallet = (RelativeLayout) v.findViewById(R.id.rl_wallet);
        rl_wallet.setOnClickListener(this);
        ll_interview = (LinearLayout) v.findViewById(R.id.ll_interview);
        ll_resume = (LinearLayout) v.findViewById(R.id.ll_resume);
        ll_project = (LinearLayout) v.findViewById(R.id.ll_project);
        ll_event = (LinearLayout) v.findViewById(R.id.ll_event);
        ll_interview.setOnClickListener(this);
        ll_resume.setOnClickListener(this);
        ll_project.setOnClickListener(this);
        ll_event.setOnClickListener(this);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.my, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_qr_code:
                startActivity(
                        new Intent(mContext, PersonalMyQRCodeActivity.class));
                mainActivity.overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.rl_to_vip:
                startActivity(
                        new Intent(mContext, PersonalMy2VipActivity.class));
                mainActivity.overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.ll_person:
                Intent intent = new Intent(mContext, PartnerInfoActivity.class);
                intent.putExtra("Homepage", true);
                startActivity(intent);
                mainActivity.overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.rl_wallet:
                startActivity(
                        new Intent(mContext, PersonalMyWalletActivity.class));
                mainActivity.overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.ll_interview:
                startActivity(new Intent(mContext,
                        PersonalMyInterviewActivity.class));
                mainActivity.overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.ll_resume:
                startActivity(
                        new Intent(mContext, PersonalMyResuActivity.class));
                mainActivity.overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.ll_project:
                startActivity(
                        new Intent(mContext, PersonalMyProjectActivity.class));
                mainActivity.overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.ll_event:
                startActivity(
                        new Intent(mContext, PersonalMyEventActivity.class));
                mainActivity.overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.rl_collect:
                startActivity(
                        new Intent(mContext, PersonalMyCollectActivity.class));
                mainActivity.overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.rl_set:
                startActivity(new Intent(mContext, PersonalMySetActivity.class));
                mainActivity.overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;

        }
    }
}

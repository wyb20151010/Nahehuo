package app.nahehuo.com.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.ui.MainActivity;

/**
 * Created by WYB on 2015/12/24.
 */
public class TabFragment extends Fragment implements View.OnClickListener {

    private LinearLayout ll_job, ll_project, ll_event, ll_partner, ll_personal;
    private ImageView iv_job, iv_project, iv_event, iv_partner, iv_personal;
    private TextView tv_job, tv_project, tv_event, tv_partner, tv_personal;
    private Fragment mContent = new Fragment();
    private Context mContext;
    private TextView textView;
    private ImageView iv_temp;
    private int index_temp = 0;
    private MainActivity mainActivity;
    private int[] main_select_drawbables = { R.drawable.job_click,
            R.drawable.project_click, R.drawable.event_click,
            R.drawable.partner_click, R.drawable.my_click };

    private int[] main_drawbables = { R.drawable.job, R.drawable.project,
            R.drawable.event, R.drawable.partner, R.drawable.my };


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


    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottomtoolbar, null);
        mContext = getActivity();
        textView = new TextView(mContext);
        iv_temp = new ImageView(mContext);
        initView(view);
        return view;
    }


    private void initView(View view) {
        ll_job = (LinearLayout) view.findViewById(R.id.layout_tab1);
        ll_job.setOnClickListener(this);
        ll_project = (LinearLayout) view.findViewById(R.id.layout_tab2);
        ll_project.setOnClickListener(this);
        ll_event = (LinearLayout) view.findViewById(R.id.layout_tab3);
        ll_event.setOnClickListener(this);
        ll_partner = (LinearLayout) view.findViewById(R.id.layout_tab4);
        ll_partner.setOnClickListener(this);
        ll_personal = (LinearLayout) view.findViewById(R.id.layout_tab5);
        ll_personal.setOnClickListener(this);
        iv_job = (ImageView) view.findViewById(R.id.toolbar_tab1);
        iv_project = (ImageView) view.findViewById(R.id.toolbar_tab2);
        iv_event = (ImageView) view.findViewById(R.id.toolbar_tab3);
        iv_partner = (ImageView) view.findViewById(R.id.toolbar_tab4);
        iv_personal = (ImageView) view.findViewById(R.id.toolbar_tab5);
        tv_job = (TextView) view.findViewById(R.id.toolbar_text1);
        tv_project = (TextView) view.findViewById(R.id.toolbar_text2);
        tv_event = (TextView) view.findViewById(R.id.toolbar_text3);
        tv_partner = (TextView) view.findViewById(R.id.toolbar_text4);
        tv_personal = (TextView) view.findViewById(R.id.toolbar_text5);
        changeCenterFragment(mContent, new JobFragment());
        changeTextColor(textView, tv_job);
        changeImage(0, iv_temp, iv_job);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    public void setTab5() {
        replaceFragment(R.id.fragment_container, new PersonalFragment());
        changeImage(4, iv_temp, iv_personal);
        changeTextColor(textView, tv_personal);
        replaceFragment(R.id.toolbar1, new PersonalToolBarFragment());
    }


    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_tab1:
                replaceFragment(R.id.fragment_container, new JobFragment());
                changeTextColor(textView, tv_job);
                changeImage(0, iv_temp, iv_job);
                replaceFragment(R.id.toolbar1, new JobToolBarFragment());
                break;
            case R.id.layout_tab2:
                replaceFragment(R.id.fragment_container, new ProjectFragment());
                changeImage(1, iv_temp, iv_project);
                changeTextColor(textView, tv_project);
                replaceFragment(R.id.toolbar1, new ProjectToolBarFragment());
                break;
            case R.id.layout_tab3:
                replaceFragment(R.id.fragment_container, new EventFragment());
                changeImage(2, iv_temp, iv_event);
                changeTextColor(textView, tv_event);
                replaceFragment(R.id.toolbar1, new EventToolBarFragment());
                break;
            case R.id.layout_tab4:
                replaceFragment(R.id.fragment_container, new PartnerFragment());
                changeImage(3, iv_temp, iv_partner);
                changeTextColor(textView, tv_partner);
                replaceFragment(R.id.toolbar1, new PartnerToolBarFragment());
                break;
            case R.id.layout_tab5:
                replaceFragment(R.id.fragment_container,
                        new PersonalFragment());
                changeImage(4, iv_temp, iv_personal);
                changeTextColor(textView, tv_personal);
                replaceFragment(R.id.toolbar1, new PersonalToolBarFragment());
                break;
        }
    }


    private void changeImage(int index, ImageView from, ImageView to) {
        if (iv_temp != to) {
            iv_temp = to;
            from.setImageResource(main_drawbables[index_temp]);
            to.setImageResource(main_select_drawbables[index]);
            index_temp = index;
        }
    }


    private void changeTextColor(TextView from, TextView to) {

        if (textView != to) {
            textView = to;
            from.setTextColor(getResources().getColor(R.color.textcolorgray));
            to.setTextColor(getResources().getColor(R.color.blue));
        }
    }


    private void changeCenterFragment(Fragment from, Fragment to) {
        if (mContent != to) {
            mContent = to;
            FragmentTransaction transaction
                    = getFragmentManager().beginTransaction();
            if (!to.isAdded()) { // 先判断是否被add过
                transaction.hide(from)
                           .add(R.id.fragment_container, to)
                           .commit(); // 隐藏当前的fragment，add下一个到Activity中
            }
            else {
                transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
        }
    }


    public void replaceFragment(int id_content, Fragment fragment) {
        FragmentTransaction transaction
                = mainActivity.getSupportFragmentManager().beginTransaction();
        transaction.replace(id_content, fragment);
        transaction.commit();
    }


    @Override public void onDestroy() {
        super.onDestroy();
    }
}

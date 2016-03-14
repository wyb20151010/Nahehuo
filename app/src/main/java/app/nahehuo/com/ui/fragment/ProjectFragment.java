package app.nahehuo.com.ui.fragment;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.FindPartnerAdapter;
import app.nahehuo.com.adapter.GirdDropDownAdapter;
import app.nahehuo.com.bean.ProjectListDict;
import app.nahehuo.com.eventbus.ToolBarEvent;
import app.nahehuo.com.ui.MainActivity;
import app.nahehuo.com.ui.project.ProjectDetailActivity;
import app.nahehuo.com.ui.project.ProjectPubActivity;
import app.nahehuo.com.ui.project.ProjectSearchActivity;
import app.nahehuo.com.ui.project.popup.MenuPopup;
import app.nahehuo.com.view.CardItemViewProject;
import app.nahehuo.com.view.CardSlidePanelProject;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.victor.loading.rotate.RotateLoading;
import com.yyydjk.library.DropDownMenu;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by WYB on 2015/12/24.
 */
public class ProjectFragment extends Fragment implements View.OnClickListener {

    private MainActivity mainActivity;
    private CardSlidePanelProject mCardSlidePanel;
    private List<ProjectListDict> mProjectListDicts = new ArrayList<>();
    private FrameLayout fl_project_content;
    private CardSlidePanelProject.CardSwitchListener mSwitchListener;
    private PullToRefreshListView plv_project_list;
    private LinearLayout ll_find_partner;
    private Context context;
    private TextView temp;
    private LinearLayout ll_cursor;
    private int width, height;
    private MenuPopup mMenuPopup;
    private DropDownMenu mDropDownMenu;
    private String headers[] = { "城市","行业" };
    private String citys[] = { "不限", "武汉", "北京", "上海", "成都", "广州", "深圳", "重庆",
            "天津", "西安", "南京", "杭州" , "成都", "广州", "深圳", "重庆"};
    private String industries[]={"计算机科学与技术","机械制造和自动化","网络技术","社会主义"};
    private String constellations[] = {"不限", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "双鱼座"};
    private String sexs[] = {"不限", "男", "女"};

    private GirdDropDownAdapter cityAdapter;
    private List<View> popupViews = new ArrayList<>();
    private LinearLayout ll_content;
    private RotateLoading mLoading;

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
        EventBus.getDefault().register(this);
    }


    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_project, null);
        context = getActivity();
        fl_project_content = (FrameLayout) v.findViewById(
                R.id.fl_project_content);
        ll_find_partner = (LinearLayout) v.findViewById(R.id.ll_find_partner);
        plv_project_list = (PullToRefreshListView) v.findViewById(
                R.id.plv_project_list);
        temp = new TextView(context);
        ll_cursor = (LinearLayout) v.findViewById(R.id.ll_cursor);
        WindowManager wm = (WindowManager) getContext().getSystemService(
                Context.WINDOW_SERVICE);

        width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();
        initData();
        initDropDownMenu(inflater, v);
        initPlv();
        initCardSlidePanel(v);

        return v;
    }


    private void initDropDownMenu(LayoutInflater inflater, View v) {
        ll_content = (LinearLayout) v.findViewById(R.id.ll_content);
        mDropDownMenu = (DropDownMenu) v.findViewById(R.id.dropDownMenu);
        final ListView cityView = (ListView) inflater.inflate(R.layout.item_cityview,null);
        cityAdapter = new GirdDropDownAdapter(context, Arrays.asList(citys));
        cityView.setDividerHeight(0);
        cityView.setAdapter(cityAdapter);
        popupViews.add(cityView);
        cityView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cityAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(
                        position == 0 ? headers[0] : citys[position]);
                mDropDownMenu.closeMenu();
            }
        });

        final ListView indView = (ListView) inflater.inflate(R.layout
                .item_cityview,null);
        cityAdapter = new GirdDropDownAdapter(context, Arrays.asList(industries));
        indView.setDividerHeight(0);
        indView.setAdapter(cityAdapter);
        popupViews.add(indView);
        indView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cityAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(
                        position == 0 ? headers[1] : industries[position]);
                mDropDownMenu.closeMenu();
            }
        });
        View content = inflater.inflate(R.layout.footview, null);
        plv_project_list = (PullToRefreshListView) content.findViewById(
                R.id.plv_project_list);
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews,
                content);
    }


    public void onEventMainThread(ToolBarEvent item) {

    }


    private void initPlv() {
        plv_project_list.setMode(PullToRefreshBase.Mode.BOTH);
        FindPartnerAdapter mAdapter = new FindPartnerAdapter(mProjectListDicts,
                context);
        plv_project_list.setAdapter(mAdapter);
        plv_project_list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mainActivity.startActivity(new Intent(context,
                                ProjectDetailActivity.class));
                        mainActivity.overridePendingTransition(
                                R.anim.push_left_in, R.anim.push_left_out);
                    }
                });
    }


    private void initCardSlidePanel(View v) {

        mCardSlidePanel = (CardSlidePanelProject) v.findViewById(
                R.id.image_slide_panel);
        mCardSlidePanel.fillData(mProjectListDicts);
        mSwitchListener = new CardSlidePanelProject.CardSwitchListener() {
            @Override public void onShow(int index) {

            }


            @Override public void onCardVanish(int index, int type) {

            }


            @Override public void onItemClick(View cardImageView, int index) {

            }


            @Override public void onFinished() {
                mCardSlidePanel.setVisibility(View.INVISIBLE);
                ll_find_partner.setVisibility(View.VISIBLE);
                EventBus.getDefault().post(new ToolBarEvent("找合伙"));
            }
        };
        mCardSlidePanel.setCardSwitchListener(mSwitchListener);

        List<CardItemViewProject> cardItemViews
                = mCardSlidePanel.getSelectedViews();
        for (int i = 0; i < cardItemViews.size(); i++) {
            cardItemViews.get(i)
                         .findViewById(R.id.ll_content)
                         .setOnClickListener(new View.OnClickListener() {
                             @Override public void onClick(View v) {
                                 Intent intent = new Intent(context,
                                         ProjectDetailActivity.class);
                                 startActivity(intent);
                                 mainActivity.overridePendingTransition(
                                         R.anim.push_left_in,
                                         R.anim.push_left_out);
                             }
                         });
        }
    }


    /*
     Arrays.asList(
        new TagItemColor[] { new TagItemColor(0xfff07669, "上海"),
                new TagItemColor(0xff58c490, "寻求合伙人"),
                new TagItemColor(0xff6dcff6, "城市合伙人") }*/


    private void initData() {
        mProjectListDicts.add(new ProjectListDict("寻皮革网---千亿级项目招募省级城市合伙人",
                "千亿级潜力市场的项目，皮革行业B2B+O2O+金融服务", "樱桃小丸子", "后台产品经理-今翌信息科技（上海）有限公司",
                "上海", "就差写安卓的了"));
        mProjectListDicts.add(new ProjectListDict("寻皮革网---千亿级项目招募省级城市合伙人",
                "千亿级潜力市场的项目，皮革行业B2B+O2O+金融服务", "樱桃小丸子", "后台产品经理-今翌信息科技（上海）有限公司",
                "上海", "寻求合伙人"));
        mProjectListDicts.add(new ProjectListDict("寻皮革网---千亿级项目招募省级城市合伙人",
                "千亿级潜力市场的项目，皮革行业B2B+O2O+金融服务", "樱桃小丸子", "后台产品经理-今翌信息科技（上海）有限公司",
                "上海", "就差写PHP的了"));
        mProjectListDicts.add(new ProjectListDict("寻皮革网---千亿级项目招募省级城市合伙人",
                "千亿级潜力市场的项目，皮革行业B2B+O2O+金融服务", "樱桃小丸子", "后台产品经理-今翌信息科技（上海）有限公司",
                "上海", "就差写代码的了"));
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.project, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            /*case R.id.open_pro:
                mMenuPopup = new MenuPopup(mainActivity);
                mMenuPopup.showPopupWindow(tv_pro_state);
                break;*/

            case R.id.start_pro:
                startActivity(new Intent(context, ProjectPubActivity.class));
                mainActivity.overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.search_pro:
                startActivity(new Intent(context, ProjectSearchActivity.class));
                mainActivity.overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
           /* case R.id.open_pro:
                mMenuPopup=new MenuPopup(mainActivity);
                mMenuPopup.showPopupWindow(mainActivity.findViewById(R.id.open_pro));*/
              /*  LinearLayout ll_start_pro= (LinearLayout) mMenuPopup.getPopupView()
                                                     .findViewById(
                                                             R.id.ll_start_pro);
                ll_start_pro.setOnClickListener(this);
                LinearLayout ll_search_pro= (LinearLayout) mMenuPopup.getPopupView()
                                                                    .findViewById(
                                                                            R.id.ll_search_pro);
                ll_search_pro.setOnClickListener(this);

                LinearLayout ll_my_pro= (LinearLayout) mMenuPopup.getPopupView()
                                                                     .findViewById(
                                                                             R.id.ll_my_pro);
                ll_my_pro.setOnClickListener(this);

                LinearLayout ll_apply_partner= (LinearLayout) mMenuPopup.getPopupView()
                                                                 .findViewById(
                                                                         R.id.ll_apply_partner);
                ll_apply_partner.setOnClickListener(this);*/
                /*break;*/
        }
        return super.onOptionsItemSelected(item);
    }


    @Override public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Override public void onClick(View v) {
        switch (v.getId()) {

            case R.id.ll_start_pro:
                startActivity(new Intent(context, ProjectPubActivity.class));
                mainActivity.overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.ll_search_pro:
                startActivity(new Intent(context, ProjectSearchActivity.class));
                mainActivity.overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
        }
    }


    private void fromStateTo(TextView from, TextView to, int i) {
        TextView tv_to = (TextView) to;
        if (temp != tv_to) {
            temp = tv_to;
            from.setTextColor(getResources().getColor(R.color.textcolorgray));
            tv_to.setTextColor(getResources().getColor(R.color.colorPrimary));
            startAnim(i);
        }
    }


    private void startAnim(int i) {

        ObjectAnimator animator = ObjectAnimator.ofFloat(ll_cursor,
                "translationX", i);
        animator.setDuration(300);
        animator.start();
    }
}

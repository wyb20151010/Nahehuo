package app.nahehuo.com.ui.fragment;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.FindPartnerAdapter;
import app.nahehuo.com.base.GlobalVariables;
import app.nahehuo.com.bean.NetProjectRecom;
import app.nahehuo.com.bean.ProjectListDict;
import app.nahehuo.com.eventbus.ToolBarEvent;
import app.nahehuo.com.network.GsonCallBack;
import app.nahehuo.com.ui.MainActivity;
import app.nahehuo.com.ui.project.ProjectDetailActivity;
import app.nahehuo.com.ui.project.ProjectPubActivity;
import app.nahehuo.com.ui.project.ProjectSearchActivity;
import app.nahehuo.com.view.CardItemViewProject;
import app.nahehuo.com.view.CardSlidePanelProject;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.victor.loading.rotate.RotateLoading;
import com.zhy.http.okhttp.OkHttpUtils;
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
    private List<View> popupViews = new ArrayList<>();
    private LinearLayout ll_content;
    private RotateLoading mLoading;
    private FindPartnerAdapter mAdapter;
    private RelativeLayout rl_city, rl_indus, rl_type, rl_status;
    private TextView tv_city, tv_indus, tv_type, tv_status;

    private final static int PROJECT_RECOMMEND = 0;

    private int width;
    private Handler mHandler = new Handler() {
        @Override public void handleMessage(Message msg) {
            switch (msg.what) {
                case PROJECT_RECOMMEND:
                    recommendProject();
                    break;
            }
            super.handleMessage(msg);
        }
    };


    private void recommendProject() {
        OkHttpUtils.get()
                   .url(GlobalVariables.PROJECT_RECOMMEND)
                   .addParams("access_token", GlobalVariables.access_token)
                   .addParams("device", GlobalVariables.device)
                   .build()
                   .execute(new GsonCallBack<NetProjectRecom>(
                           NetProjectRecom.class) {
                       @Override
                       public void onResponse(NetProjectRecom response) {
                           if (response.getCode() == 200) {
                               for (int i = 0;
                                    i < response.getData().size();
                                    i++) {
                                   ProjectListDict dict = new ProjectListDict();
                                   dict.setProjectComName(response.getData()
                                                                  .get(i)
                                                                  .getUsername());
                                   dict.setProjectComPosition(response.getData()
                                                                      .get(i)
                                                                      .getCompany());
                                   dict.setProjectPic(
                                           response.getData().get(i).getPic());
                                   dict.setPersonPic(response.getData()
                                                             .get(i)
                                                             .getAvatar());
                                   List<String> tags = new ArrayList<String>();
                                   tags.add(
                                           response.getData().get(i).getArea());
                                   tags.add("寻求合伙人");
                                   tags.addAll(Arrays.asList(response.getData()
                                                                     .get(i)
                                                                     .getTag()
                                                                     .split(",")));
                                   dict.setProjectTagList(tags);
                                   mProjectListDicts.add(dict);
                               }
                               mCardSlidePanel.fillData(mProjectListDicts);
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


    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        initData();
        initView(v);
        initCursor(v);
        initCardSlidePanel(v);
        initPlv();
        mHandler.sendEmptyMessage(PROJECT_RECOMMEND);
        return v;
    }


    private void initView(View v) {
        rl_city = (RelativeLayout) v.findViewById(R.id.rl_city);
        rl_indus = (RelativeLayout) v.findViewById(R.id.rl_indus);
        rl_type = (RelativeLayout) v.findViewById(R.id.rl_type);
        rl_status = (RelativeLayout) v.findViewById(R.id.rl_status);
        tv_city = (TextView) v.findViewById(R.id.tv_city);
        tv_indus = (TextView) v.findViewById(R.id.tv_indus);
        tv_type = (TextView) v.findViewById(R.id.tv_type);
        tv_status = (TextView) v.findViewById(R.id.tv_status);
        rl_city.setOnClickListener(this);
        rl_indus.setOnClickListener(this);
        rl_type.setOnClickListener(this);
        rl_status.setOnClickListener(this);
    }


    private void initCursor(View v) {
        WindowManager wm = (WindowManager) getContext().getSystemService(
                Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        ll_cursor = (LinearLayout) v.findViewById(R.id.ll_cursor);
        ll_cursor.getLayoutParams().width = width / 4;
        fromStateTo(temp, tv_city, 0);
    }


    private void initPlv() {
        plv_project_list.setMode(PullToRefreshBase.Mode.BOTH);
        mAdapter = new FindPartnerAdapter(mProjectListDicts, context);
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
        }
        return super.onOptionsItemSelected(item);
    }


    @Override public void onDestroy() {
        super.onDestroy();
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
            case R.id.rl_city:
                fromStateTo(temp, tv_city, 0);
                break;
            case R.id.rl_indus:
                fromStateTo(temp, tv_indus, width / 4);
                break;
            case R.id.rl_type:
                fromStateTo(temp, tv_type, width * 2 / 4);
                break;
            case R.id.rl_status:
                fromStateTo(temp, tv_status, width * 3 / 4);
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

package app.nahehuo.com.ui.event;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.EventSearchAdapter;
import app.nahehuo.com.base.GlobalVariables;
import app.nahehuo.com.bean.EventSearchList;
import app.nahehuo.com.bean.NetEventList;
import app.nahehuo.com.network.GsonCallBack;
import app.nahehuo.com.util.MyToast;
import app.nahehuo.com.util.VeDate;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhy.http.okhttp.OkHttpUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/1/27.
 */
public class EventSearchActivity extends AppCompatActivity
        implements View.OnClickListener,
        PullToRefreshBase.OnRefreshListener<ListView> {

    private RelativeLayout rl_event_city, rl_event_time, rl_event_theme;
    private TextView tv_event_city, tv_event_time, tv_event_theme;
    private TextView temp;
    private Context mContext;
    private LinearLayout ll_cursor;
    private int width;
    private PullToRefreshListView flv_show_content;
    private List<EventSearchList> mListDicts = new ArrayList<>();
    private EventSearchAdapter mSearchAdapter;

    //推荐 0 不推荐，1推荐默认 1
    private int recommend = 1;
    private String area;
    private long time;
    private String theme;
    private String keyword;
    private int pageindex;

    private final static int EVENT_SEARCH = 0;
    private Handler mHandler = new Handler() {
        @Override public void handleMessage(Message msg) {
            switch (msg.what) {
                case EVENT_SEARCH:
                    eventSearch();
                    break;
            }
            super.handleMessage(msg);
        }
    };


    private void eventSearch() {
        OkHttpUtils.get()
                   .url(GlobalVariables.EVENT_SEARCH)
                   .addParams("access_token", GlobalVariables.access_token)
                   .addParams("device", GlobalVariables.device)
                   .addParams("recommend", recommend + "")
                   .addParams("area", area)
                   .addParams("time", time + "")
                   .addParams("theme", theme)
                   .addParams("keyword", keyword)
                   .addParams("pageindex", pageindex + "")
                   .addParams("pagesize", GlobalVariables.pagesize)
                   .build()
                   .execute(new GsonCallBack<NetEventList>(NetEventList.class) {
                       @Override public void onResponse(NetEventList response) {
                           if (response.getCode() == 200) {
                               for (int i = 0;
                                    i < response.getData().size();
                                    i++) {
                                   EventSearchList searchList
                                           = new EventSearchList();
                                   searchList.setIv_event_pic(
                                           response.getData().get(i).getLogo());
                                   searchList.setTv_event_title(
                                           response.getData()
                                                   .get(i)
                                                   .getTitle());
                                   searchList.setTv_event_location(
                                           response.getData().get(i).getProv() +
                                                   response.getData()
                                                           .get(i)
                                                           .getCity());
                                   searchList.setTv_event_number(
                                           response.getData()
                                                   .get(i)
                                                   .getEventnum() + "");
                                   searchList.setType(response.getData()
                                                              .get(i)
                                                              .getEventprice());
                                   StringBuffer sb1 = new StringBuffer();
                                   sb1.append(VeDate.formatEventTime(
                                           response.getData()
                                                   .get(i)
                                                   .getStarted()));
                                   sb1.append(" 至 ");
                                   sb1.append(VeDate.formatEventTime(
                                           response.getData()
                                                   .get(i)
                                                   .getEnded()));
                                   searchList.setTv_event_time(sb1.toString());
                                   mListDicts.add(searchList);
                               }
                               mSearchAdapter.notifyDataSetChanged();
                           }
                           else {
                               MyToast.showToast(mContext,
                                       response.getMessage());
                           }
                           super.onResponse(response);
                       }
                   });
    }


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_search);
        mContext = this;
        pageindex = 1;
        temp = new TextView(this);
        WindowManager wm = (WindowManager) getSystemService(
                Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        initView();
        initFlv();
        mHandler.sendEmptyMessage(EVENT_SEARCH);
    }


    private void initFlv() {

        mSearchAdapter = new EventSearchAdapter(mContext, mListDicts);
        flv_show_content.setMode(PullToRefreshBase.Mode.BOTH);
        flv_show_content.setAdapter(mSearchAdapter);
        flv_show_content.setOnRefreshListener(this);
    }


    private void initView() {
        flv_show_content = (PullToRefreshListView) findViewById(
                R.id.flv_show_content);
        ll_cursor = (LinearLayout) findViewById(R.id.ll_cursor);
        rl_event_city = (RelativeLayout) findViewById(R.id.rl_event_city);
        rl_event_time = (RelativeLayout) findViewById(R.id.rl_event_time);
        rl_event_theme = (RelativeLayout) findViewById(R.id.rl_event_theme);
        tv_event_city = (TextView) findViewById(R.id.tv_event_city);
        tv_event_time = (TextView) findViewById(R.id.tv_event_time);
        tv_event_theme = (TextView) findViewById(R.id.tv_event_theme);
        rl_event_city.setOnClickListener(this);
        rl_event_time.setOnClickListener(this);
        rl_event_theme.setOnClickListener(this);
    }


    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_event_city:
                fromStateTo(temp, tv_event_city, 0);
                break;
            case R.id.rl_event_time:
                fromStateTo(temp, tv_event_time, width * 1 / 3);
                break;
            case R.id.rl_event_theme:
                fromStateTo(temp, tv_event_theme, width * 2 / 3);
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


    @Override public void onRefresh(PullToRefreshBase<ListView> refreshView) {
        if (refreshView.isHeaderShown()) {
            pageindex = 1;
            mListDicts.clear();
            mHandler.sendEmptyMessage(EVENT_SEARCH);
        }
        else if (refreshView.isFooterShown()) {
            pageindex++;
            mHandler.sendEmptyMessage(EVENT_SEARCH);
        }
    }
}

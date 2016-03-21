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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.EventListAdapter;
import app.nahehuo.com.base.GlobalVariables;
import app.nahehuo.com.bean.EventListDict;
import app.nahehuo.com.bean.NetEventList;
import app.nahehuo.com.network.GsonCallBack;
import app.nahehuo.com.ui.MainActivity;
import app.nahehuo.com.ui.event.EventDetailActivity;
import app.nahehuo.com.ui.event.EventPubActivity;
import app.nahehuo.com.ui.event.EventSearchActivity;
import app.nahehuo.com.util.MyToast;
import app.nahehuo.com.util.VeDate;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhy.http.okhttp.OkHttpUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2015/12/24.
 */
public class EventFragment extends Fragment implements View.OnClickListener,
        PullToRefreshBase.OnRefreshListener {

    private Context mContext;
    private MainActivity mainActivity;
    private RelativeLayout rl_event_recom, rl_event_time, rl_event_distance;
    private TextView tv_event_recom, tv_event_time, tv_event_distance;
    private TextView temp;
    private LinearLayout ll_cursor;
    private int width;
    private PullToRefreshListView plv_event_list;
    private EventListAdapter mEventListAdapter;
    private List<EventListDict> mListDicts = new ArrayList<>();
    private final static int FIND_EVENT_LIST = 0;

    private int pageindex;
    //推荐 0 不推荐，1推荐默认 1
    private int recommend=1;
    private long time;
    private String distance;
    private Handler mHandler = new Handler() {
        @Override public void handleMessage(Message msg) {
            switch (msg.what) {
                case FIND_EVENT_LIST:
                    findEventList();
                    break;
            }
            super.handleMessage(msg);
        }
    };


    private void findEventList() {

        plv_event_list.onRefreshComplete();
        if(pageindex==1){
            mListDicts.clear();
        }
        OkHttpUtils.get()
                   .url(GlobalVariables.EVENT_LIST)
                   .addParams("access_token", GlobalVariables.access_token)
                   .addParams("device", GlobalVariables.device)
                   .addParams("recommend", recommend + "")
                   .addParams("time", time + "")
                  /* .addParams("distance", distance)*/
                   .addParams("pageindex", pageindex + "")
                   .addParams("pagesize", GlobalVariables.pagesize)
                   .build()
                   .execute(new GsonCallBack<NetEventList>(NetEventList.class) {
                       @Override public void onResponse(NetEventList response) {
                           if (response.getCode() == 200) {
                               for (int i = 0;
                                    i < response.getData().size();
                                    i++) {
                                   EventListDict event = new EventListDict();
                                   event.setIv_per_avater(response.getData()
                                                                  .get(i)
                                                                  .getAvatar());
                                   event.setTv_event_title(response.getData()
                                                                   .get(i)
                                                                   .getTitle());
                                   event.setTv_per_name(response.getData()
                                                                .get(i)
                                                                .getUsername());
                                   StringBuffer sb = new StringBuffer();
                                   sb.append(
                                           response.getData().get(i).getJob());
                                   sb.append("-");
                                   sb.append(response.getData()
                                                     .get(i)
                                                     .getCompany());
                                   event.setTv_per_pos(sb.toString());
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
                                   event.setTv_event_time(sb1.toString());
                                   event.setTv_event_number(response.getData()
                                                                    .get(i)
                                                                    .getEventnum() +
                                           "");
                                   event.setType(response.getData()
                                                         .get(i)
                                                         .getEventprice());
                                   event.setEvent_price(response.getData()
                                                                .get(i)
                                                                .getPrice());
                                   StringBuffer buffer = new StringBuffer();
                                   buffer.append(
                                           response.getData().get(i).getProv());
                                   buffer.append(
                                           response.getData().get(i).getCity());
                                   buffer.append(response.getData()
                                                         .get(i)
                                                         .getAddress());
                                   event.setTv_event_location(
                                           buffer.toString());
                                   event.setTv_event_watch_num(
                                           response.getData()
                                                   .get(i)
                                                   .getViewnum() + "");
                                   event.setTv_event_com_num(response.getData()
                                                                     .get(i)
                                                                     .getComnum() +
                                           "");
                                   event.setTv_event_per_num(response.getData()
                                                                     .get(i)
                                                                     .getFollownum() +
                                           "");
                                   mListDicts.add(event);
                               }
                               mEventListAdapter.notifyDataSetChanged();
                           }
                           else {
                               MyToast.showToast(mContext,
                                       response.getMessage());
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
        View v = inflater.inflate(R.layout.fragment_event, null);
        mContext = getActivity();
        pageindex = 1;
        temp = new TextView(mContext);
        ll_cursor = (LinearLayout) v.findViewById(R.id.ll_cursor);
        WindowManager wm = (WindowManager) getContext().getSystemService(
                Context.WINDOW_SERVICE);

        width = wm.getDefaultDisplay().getWidth();
        initView(v);
        initFLv(v);
        mHandler.sendEmptyMessage(FIND_EVENT_LIST);
        return v;
    }


    private void initFLv(View v) {
        mEventListAdapter = new EventListAdapter(mListDicts, mContext);
        plv_event_list = (PullToRefreshListView) v.findViewById(
                R.id.plv_event_list);
        plv_event_list.setMode(PullToRefreshBase.Mode.BOTH);
        plv_event_list.setAdapter(mEventListAdapter);
        plv_event_list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        startActivity(new Intent(mContext,
                                EventDetailActivity.class));
                        mainActivity.overridePendingTransition(
                                R.anim.push_left_in, R.anim.push_left_out);
                    }
                });
        plv_event_list.setOnRefreshListener(this);
    }

    private void initView(View v) {
        rl_event_recom = (RelativeLayout) v.findViewById(R.id.rl_event_recom);
        rl_event_recom.setOnClickListener(this);
        rl_event_time = (RelativeLayout) v.findViewById(R.id.rl_event_time);
        rl_event_time.setOnClickListener(this);
        rl_event_distance = (RelativeLayout) v.findViewById(
                R.id.rl_event_distance);
        rl_event_distance.setOnClickListener(this);
        tv_event_recom = (TextView) v.findViewById(R.id.tv_event_recom);
        tv_event_time = (TextView) v.findViewById(R.id.tv_event_time);
        tv_event_distance = (TextView) v.findViewById(R.id.tv_event_distance);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.event, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.start_event:
                startActivity(new Intent(mContext, EventPubActivity.class));
                mainActivity.overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.search_event:
                startActivity(new Intent(mContext, EventSearchActivity.class));
                mainActivity.overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.my_event:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override public void onClick(View v) {

        switch (v.getId()) {
            case R.id.rl_event_recom:
                fromStateTo(temp, tv_event_recom, 0);
                break;
            case R.id.rl_event_time:
                fromStateTo(temp, tv_event_time, width / 3);
                break;
            case R.id.rl_event_distance:
                fromStateTo(temp, tv_event_distance, width * 2 / 3);
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


    @Override public void onRefresh(PullToRefreshBase refreshView) {
        if(refreshView.isHeaderShown()){
            pageindex=1;
            mListDicts.clear();
            mHandler.sendEmptyMessage(FIND_EVENT_LIST);

        }else if(refreshView.isFooterShown()){
            pageindex++;
            mHandler.sendEmptyMessage(FIND_EVENT_LIST);
        }
    }
}

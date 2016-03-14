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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.EventListAdapter;
import app.nahehuo.com.bean.EventListDict;
import app.nahehuo.com.ui.MainActivity;
import app.nahehuo.com.ui.event.EventDetailActivity;
import app.nahehuo.com.ui.event.EventPubActivity;
import app.nahehuo.com.ui.event.EventSearchActivity;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2015/12/24.
 */
public class EventFragment extends Fragment implements View.OnClickListener {

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
        temp = new TextView(mContext);
        ll_cursor = (LinearLayout) v.findViewById(R.id.ll_cursor);
        WindowManager wm = (WindowManager) getContext().getSystemService(
                Context.WINDOW_SERVICE);

        width = wm.getDefaultDisplay().getWidth();
        initView(v);
        initFLv(v);
        return v;
    }


    private void initFLv(View v) {
        initData();
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
    }


    private void initData() {
        mListDicts.add(new EventListDict("大工厂------专注于打造“服饰生态圈”的互联网公司", "樱桃小王子",
                "后台产品经理 - 今翌信息科技（上海）有限公司", "2015-12-31 05:00 至2016-02-01",
                "1000人", "上海市松江区九新公路341弄28号7层", "99", "本人多个创业项目，想找技术或创业人士聊聊",
                "88", "55", "55",
                "http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg", 1));
        mListDicts.add(new EventListDict("大工厂------专注于打造“服饰生态圈”的互联网公司", "樱桃小王子",
                "后台产品经理 - 今翌信息科技（上海）有限公司", "2015-12-31 05:00 至2016-02-01",
                "1000人", "上海市松江区九新公路341弄28号7层", "99", "本人多个创业项目，想找技术或创业人士聊聊",
                "88", "55", "55",
                "http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg", 0));
        mListDicts.add(new EventListDict("大工厂------专注于打造“服饰生态圈”的互联网公司", "樱桃小王子",
                "后台产品经理 - 今翌信息科技（上海）有限公司", "2015-12-31 05:00 至2016-02-01",
                "1000人", "上海市松江区九新公路341弄28号7层", "99", "本人多个创业项目，想找技术或创业人士聊聊",
                "88", "55", "55",
                "http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg", 1));
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
}

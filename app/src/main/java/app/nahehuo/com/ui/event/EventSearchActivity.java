package app.nahehuo.com.ui.event;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.EventSearchAdapter;
import app.nahehuo.com.bean.EventSearchList;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/1/27.
 */
public class EventSearchActivity extends AppCompatActivity
        implements View.OnClickListener {

    private RelativeLayout rl_event_city, rl_event_time, rl_event_theme;
    private TextView tv_event_city, tv_event_time, tv_event_theme;
    private TextView temp;
    private Context mContext;
    private LinearLayout ll_cursor;
    private int width;
    private PullToRefreshListView flv_show_content;
    private List<EventSearchList> mListDicts = new ArrayList<>();


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_search);
        mContext = this;
        temp = new TextView(this);
        WindowManager wm = (WindowManager) getSystemService(
                Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        initView();
        initFlv();
    }


    private void initFlv() {
        initData();
        flv_show_content.setMode(PullToRefreshBase.Mode.BOTH);
        flv_show_content.setAdapter(
                new EventSearchAdapter(mContext, mListDicts));
    }


    private void initData() {
        mListDicts.add(new EventSearchList(
                "http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg",
                "今年我在哪，初来乍到交流专场，寻找职场菜鸟，干一票大的", "2015-12-31 05:00", "上海市松江区",
                "365m", "1000人", 0));
        mListDicts.add(new EventSearchList(
                "http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg",
                "今年我在哪，初来乍到交流专场，寻找职场菜鸟，干一票大的", "2015-12-31 05:00", "上海市松江区",
                "365m", "1000人", 1));
        mListDicts.add(new EventSearchList(
                "http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg",
                "社会主义四有青年就是我们", "2015-12-31 05:00", "上海市松江区", "365m", "1000人",
                1));
        mListDicts.add(new EventSearchList(
                "http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg",
                "今年我在哪，初来乍到交流专场，寻找职场菜鸟，干一票大的", "2015-12-31 05:00", "上海市松江区",
                "365m", "1000人", 0));
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
}

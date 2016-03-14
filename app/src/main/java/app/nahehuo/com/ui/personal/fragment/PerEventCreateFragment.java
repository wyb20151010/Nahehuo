package app.nahehuo.com.ui.personal.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.PerEventAdapter;
import app.nahehuo.com.bean.PerEvent;
import app.nahehuo.com.ui.personal.PerEventDetailActivity;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyb on 2016/3/3.
 */
public class PerEventCreateFragment extends Fragment {

    private PullToRefreshListView plv_event;

    private List<PerEvent> mPerEvents = new ArrayList<>();

    private PerEventAdapter mPerEventAdapter;

    private Context mContext;


    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_per_event_create, null);
        mContext = getActivity();
        initData();
        initFlv(v);
        return v;
    }


    private void initData() {
        mPerEvents.add(
                new PerEvent("大工厂------专注于打造“服饰生态圈”的互联网公司", "进行中", "樱桃小王子",
                        "后台产品经理 - 今翌信息科技（上海）有限公司",
                        "2015-12-31 05:00 至 2016-02-01", "1000人", "免费",
                        "上海市松江区九新公路341弄28号7层", "362m"));
        mPerEvents.add(
                new PerEvent("大工厂------专注于打造“服饰生态圈”的互联网公司", "圆满结束", "樱桃小王子",
                        "后台产品经理 - 今翌信息科技（上海）有限公司",
                        "2015-12-31 05:00 至 2016-02-01", "1000人", "200元/人",
                        "上海市松江区九新公路341弄28号7层", "362m"));
        mPerEvents.add(
                new PerEvent("大工厂------专注于打造“服饰生态圈”的互联网公司", "报名截止", "樱桃小王子",
                        "后台产品经理 - 今翌信息科技（上海）有限公司",
                        "2015-12-31 05:00 至 2016-02-01", "1000人", "200元/人",
                        "上海市松江区九新公路341弄28号7层", "362m"));
    }


    private void initFlv(View v) {
        mPerEventAdapter = new PerEventAdapter(mPerEvents, mContext);
        plv_event = (PullToRefreshListView) v.findViewById(R.id.plv_event);
        plv_event.setMode(PullToRefreshBase.Mode.BOTH);
        plv_event.setAdapter(mPerEventAdapter);
        plv_event.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(
                        new Intent(mContext, PerEventDetailActivity.class));
            }
        });
    }
}

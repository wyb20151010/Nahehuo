package app.nahehuo.com.ui.personal.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.PerCollectJobAdapter;
import app.nahehuo.com.bean.PerCollectJob;
import app.nahehuo.com.bean.TagStatus;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wyb on 2016/3/3.
 */
public class PerCollectJobFragment extends Fragment {

    private PullToRefreshListView plv_job;
    private List<PerCollectJob> mPerCollectJobs = new ArrayList<>();
    private PerCollectJobAdapter mAdapter;
    private Context mContext;


    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_per_collect_job, null);
        mContext = getActivity();

        initData();
        mAdapter = new PerCollectJobAdapter(mPerCollectJobs, mContext);
        plv_job = (PullToRefreshListView) v.findViewById(R.id.plv_job);
        plv_job.setMode(PullToRefreshBase.Mode.BOTH);
        plv_job.setAdapter(mAdapter);
        return v;
    }


    private void initData() {
        mPerCollectJobs.add(new PerCollectJob("高级android开发", "腾讯科技",
                Arrays.asList(new TagStatus[] { new TagStatus("20k-30k", "1"),
                        new TagStatus("学历不限", "2"), new TagStatus("上海", "2"),
                        new TagStatus("本科", "2") })));
        mPerCollectJobs.add(new PerCollectJob("有钱摸摸哒", "腾讯科技", Arrays.asList(
                new TagStatus[] { new TagStatus("20k-30k", "1"),
                        new TagStatus("学历不限", "2"), new TagStatus("上海", "2"),
                        new TagStatus("本科", "2") })));
        mPerCollectJobs.add(new PerCollectJob("没钱玩你妹啊", "腾讯科技", Arrays.asList(
                new TagStatus[] { new TagStatus("20k-30k", "1"),
                        new TagStatus("学历不限", "2"), new TagStatus("上海", "2"),
                        new TagStatus("本科", "2") })));
    }
}

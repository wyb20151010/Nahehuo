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
import android.widget.ListView;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.PerProjectPubAdapter;
import app.nahehuo.com.bean.PerProjectPub;
import app.nahehuo.com.ui.personal.PerProjectDetailActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyb on 2016/3/4.
 */
public class PerProjectPubFragment extends Fragment {

    private ListView lv_project_pub;
    private List<PerProjectPub> mPubs = new ArrayList<>();
    private PerProjectPubAdapter mAdapter;
    private Context mContext;


    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_per_pro_pub, null);
        mContext = getActivity();
        initData();
        mAdapter = new PerProjectPubAdapter(mPubs, mContext);
        lv_project_pub = (ListView) v.findViewById(R.id.lv_project_pub);
        lv_project_pub.setAdapter(mAdapter);
        lv_project_pub.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        startActivity(new Intent(mContext,
                                PerProjectDetailActivity.class));
                    }
                });
        return v;
    }


    private void initData() {
        mPubs.add(new PerProjectPub("寻皮革网--千亿级项目招募省级城市合伙人",
                "创建时间:2015-09-30 13:30:23", "审核中"));
        mPubs.add(new PerProjectPub("寻皮革网--千亿级项目招募省级城市合伙人",
                "创建时间:2015-09-30 13:30:23", "进行中"));
        mPubs.add(new PerProjectPub("寻皮革网--千亿级项目招募省级城市合伙人",
                "创建时间:2015-09-30 13:30:23", "合伙成功"));
    }
}

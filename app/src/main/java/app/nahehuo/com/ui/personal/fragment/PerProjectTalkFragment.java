package app.nahehuo.com.ui.personal.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.PerProjectTalkAdapter;
import app.nahehuo.com.bean.PerProjectTalk;
import app.nahehuo.com.bean.TagStatus;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by wyb on 2016/3/4.
 */
public class PerProjectTalkFragment extends Fragment {

    private ListView lv_project_talk;
    private Context mContext;
    private ArrayList<PerProjectTalk> mPerProjectTalks = new ArrayList<>();
    private PerProjectTalkAdapter mAdapter;


    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_per_pro_talk, null);
        mContext = getActivity();
        initData();

        mAdapter = new PerProjectTalkAdapter(mPerProjectTalks, mContext);
        lv_project_talk = (ListView) v.findViewById(R.id.lv_project_talk);
        lv_project_talk.setAdapter(mAdapter);
        return v;
    }


    private void initData() {
        mPerProjectTalks.add(
                new PerProjectTalk("寻皮革网--千亿级项目招募省级城市合伙人", "千亿级项目招募省级城市合伙人",
                        Arrays.asList(
                                new TagStatus[] { new TagStatus("上海", "1"),
                                        new TagStatus("寻求合伙人", "2") }), "樱桃子",
                        "后台产品经理-今翌信息科技有限公司", "13915170369", "40314510@qq.com"));
        mPerProjectTalks.add(
                new PerProjectTalk("寻皮革网--千亿级项目招募省级城市合伙人", "千亿级项目招募省级城市合伙人",
                        Arrays.asList(
                                new TagStatus[] { new TagStatus("上海", "1"),
                                        new TagStatus("寻求合伙人", "2") }), "樱桃子",
                        "后台产品经理-今翌信息科技有限公司", "13915170369", "40314510@qq.com"));
        mPerProjectTalks.add(
                new PerProjectTalk("寻皮革网--千亿级项目招募省级城市合伙人", "千亿级项目招募省级城市合伙人",
                        Arrays.asList(
                                new TagStatus[] { new TagStatus("北京", "1"),
                                        new TagStatus("寻求合伙人", "2") }), "樱桃子",
                        "后台产品经理-今翌信息科技有限公司", "13915170369", "40314510@qq.com"));
    }
}

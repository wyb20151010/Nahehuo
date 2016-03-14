package app.nahehuo.com.ui.personal.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.PersonalWorkExpAdapter;
import app.nahehuo.com.bean.PerWorkExp;
import app.nahehuo.com.ui.personal.PersonalMy2VipActivity;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/2/26.
 */
public class PersonalWorkExpFragment extends Fragment {

    private PullToRefreshListView plv_content;
    private PersonalMy2VipActivity mActivity;
    private List<PerWorkExp> mPerWorkExps = new ArrayList<>();
    private PersonalWorkExpAdapter mAdapter;
    private Context mContext;


    @Override public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof PersonalMy2VipActivity) {
            mActivity = (PersonalMy2VipActivity) activity;
        }
        else {
            throw new IllegalArgumentException(
                    "the activity must be PersonalMy2VipActivity");
        }
    }


    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_work_exp, null);
        mContext = getActivity();
        initData();
        mAdapter = new PersonalWorkExpAdapter(mPerWorkExps, mContext);
        plv_content = (PullToRefreshListView) v.findViewById(R.id.plv_content);
        plv_content.setAdapter(mAdapter);
        return v;
    }


    private void initData() {
        mPerWorkExps.add(
                new PerWorkExp("2014.3-至今", "高级产品经理", "0", "今翌信息科技(上海)有限公司",false));
        mPerWorkExps.add(
                new PerWorkExp("2014.3-至今", "高级产品经理", "1", "今翌信息科技(上海)有限公司",false));
        mPerWorkExps.add(
                new PerWorkExp("2014.3-至今", "高级产品经理", "1", "今翌信息科技(上海)有限公司",false));
        mPerWorkExps.add(
                new PerWorkExp("2014.3-至今", "高级产品经理", "0", "今翌信息科技(上海)有限公司",false));
    }
}

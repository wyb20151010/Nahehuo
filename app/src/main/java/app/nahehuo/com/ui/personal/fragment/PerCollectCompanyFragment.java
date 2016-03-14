package app.nahehuo.com.ui.personal.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.PerCollectCompanyAdapter;
import app.nahehuo.com.bean.PerCollectCompany;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wyb on 2016/3/3.
 */
public class PerCollectCompanyFragment extends Fragment {

    private PullToRefreshListView plv_company;
    private Context mContext;
    private PerCollectCompanyAdapter mCompanyAdapter;
    private List<PerCollectCompany> mPerCollectCompanies = new ArrayList<>();


    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_per_collect_company, null);
        mContext = getActivity();

        initData();
        mCompanyAdapter = new PerCollectCompanyAdapter(mPerCollectCompanies,
                mContext);
        plv_company = (PullToRefreshListView) v.findViewById(R.id.plv_company);
        plv_company.setMode(PullToRefreshBase.Mode.BOTH);
        plv_company.setAdapter(mCompanyAdapter);
        return v;
    }


    private void initData() {
        mPerCollectCompanies.add(new PerCollectCompany("腾讯科技",
                Arrays.asList(new String[] { "妹子多", "福利好", "十三薪","妹子多",
                        "福利好", "十三薪" })));
        mPerCollectCompanies.add(new PerCollectCompany("腾讯科技",
                Arrays.asList(new String[] { "妹子多", "福利好", "十三薪" })));
        mPerCollectCompanies.add(new PerCollectCompany("腾讯科技",
                Arrays.asList(new String[] { "妹子多", "福利好", "十三薪" })));
        mPerCollectCompanies.add(new PerCollectCompany("腾讯科技",
                Arrays.asList(new String[] { "妹子多", "福利好", "十三薪" })));
    }
}

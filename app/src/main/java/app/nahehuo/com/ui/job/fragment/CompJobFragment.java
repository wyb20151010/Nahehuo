package app.nahehuo.com.ui.job.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.CompanyJobAdapter;
import app.nahehuo.com.bean.CompanyJobDict;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/1/11.
 */
public class CompJobFragment extends Fragment {

    private RecyclerView recycler_view;
    private CompanyJobAdapter mCompanyJobAdapter;
    private Context mContext;
    private List<CompanyJobDict> mCompanyJobDicts = new ArrayList<>();


    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_comp_job, null);
        mContext = getActivity();
        initData();
        initView(v);
        return v;
    }


    private void initData() {
        mCompanyJobDicts.add(
                new CompanyJobDict("高级PHP开发工程师", "15:55", "10K-20K",
                        "齐齐哈尔 | 1-2年 |硕士"));
        mCompanyJobDicts.add(
                new CompanyJobDict("高级IOS开发工程师", "15:55", "10K-20K",
                        "齐齐哈尔 | 1-2年 |硕士"));
        mCompanyJobDicts.add(
                new CompanyJobDict("高级Android开发工程师", "15:55", "10K-20K",
                        "齐齐哈尔 | 1-2年 |硕士"));
        mCompanyJobDicts.add(
                new CompanyJobDict("高级Android开发工程师", "15:55", "10K-20K",
                        "齐齐哈尔 | 1-2年 |硕士"));
        mCompanyJobDicts.add(
                new CompanyJobDict("高级Android开发工程师", "15:55", "10K-20K",
                        "齐齐哈尔 | 1-2年 |硕士"));
        mCompanyJobDicts.add(
                new CompanyJobDict("高级Android开发工程师", "15:55", "10K-20K",
                        "齐齐哈尔 | 1-2年 |硕士"));
    }


    private void initView(View v) {
        recycler_view = (RecyclerView) v.findViewById(R.id.recycler_view);
        mCompanyJobAdapter = new CompanyJobAdapter(mCompanyJobDicts, mContext);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_view.setLayoutManager(layoutManager);
        recycler_view.setAdapter(mCompanyJobAdapter);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}

package app.nahehuo.com.ui.job.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import app.nahehuo.com.R;
import app.nahehuo.com.ui.job.JobSearchActivity;

/**
 * Created by WYB on 2016/1/5.
 */
public class JobSearchComFragment extends Fragment {

    private Context mContext;
    private JobSearchActivity mJobDetailActivity;

    @Override public void onAttach(Activity activity) {
        if (activity instanceof JobSearchActivity) {
            mJobDetailActivity = (JobSearchActivity) activity;
        }
        else {
            throw new IllegalArgumentException(
                    "The activity must be a JobSearchActivity !");
        }
        super.onAttach(activity);
    }

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(
                R.layout.fragment_company_search_pos, null);
        mContext=getActivity();
        return contentView;
    }
}

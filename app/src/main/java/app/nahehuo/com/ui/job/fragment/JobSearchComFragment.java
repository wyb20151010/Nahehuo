package app.nahehuo.com.ui.job.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import app.nahehuo.com.R;

/**
 * Created by WYB on 2016/1/5.
 */
public class JobSearchComFragment extends Fragment {

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_job_search_pos,
                null);
        return contentView;
    }
}

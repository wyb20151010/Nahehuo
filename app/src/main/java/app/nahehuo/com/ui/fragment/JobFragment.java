package app.nahehuo.com.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import app.nahehuo.com.R;
import app.nahehuo.com.ui.MainActivity;
import app.nahehuo.com.ui.job.JobSearchActivity;

/**
 * Created by WYB on 2015/12/24.
 */
public class JobFragment extends Fragment {

    private Fragment mContent = new Fragment();
    private MainActivity mainActivity;
    private Context mContext;


    @Override public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof MainActivity) {
            mainActivity = (MainActivity) activity;
        }
        else {
            throw new IllegalArgumentException(
                    "The activity must be a MainActivity !");
        }
    }


    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_job, null);
        mContext = getContext();
        return v;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.job, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                startActivityForResult(
                        new Intent(mContext, JobSearchActivity.class), 1);
                mainActivity.overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

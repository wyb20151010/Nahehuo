package app.nahehuo.com.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.eventbus.ToolBarEvent;
import app.nahehuo.com.ui.MainActivity;
import de.greenrobot.event.EventBus;

/**
 * Created by WYB on 2015/12/28.
 */
public class EventToolBarFragment extends Fragment {

    private MainActivity mainActivity;
    private Toolbar toolbar;
    private TextView tv_title;

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


    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }


    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.toolbar_event, null);
        toolbar = (Toolbar) v.findViewById(R.id.toolbar);
        tv_title = (TextView) v.findViewById(R.id.tv_title);
        mainActivity.setSupportActionBar(toolbar);
        ActionBar actionBar = mainActivity.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        return v;
    }


    public void onEventMainThread(ToolBarEvent item) {
        Log.d("TAG",item.getMsg());
        tv_title.setText(item.getMsg());

    }


    @Override public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

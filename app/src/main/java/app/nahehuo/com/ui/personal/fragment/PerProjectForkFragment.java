package app.nahehuo.com.ui.personal.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import app.nahehuo.com.R;

/**
 * Created by wyb on 2016/3/4.
 */
public class PerProjectForkFragment extends Fragment{

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_per_pro_fork, null);
        return v;
    }
}

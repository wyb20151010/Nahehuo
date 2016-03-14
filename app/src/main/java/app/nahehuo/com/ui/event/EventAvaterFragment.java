package app.nahehuo.com.ui.event;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import app.nahehuo.com.R;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by WYB on 2016/1/30.
 */
public class EventAvaterFragment extends Fragment {

    private String url;
    private ImageView iv_avater;

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url=getArguments().getString("url");
    }


    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_event_avater, null);
        iv_avater= (ImageView) v.findViewById(R.id.iv_avater);
        ImageLoader.getInstance().displayImage(url,iv_avater);
        return v;
    }

    public static EventAvaterFragment newInstance(String url) {
        EventAvaterFragment myFragment = new EventAvaterFragment();
        Bundle args = new Bundle();
        args.putString("url", url);
        myFragment.setArguments(args);

        return myFragment;
    }
}

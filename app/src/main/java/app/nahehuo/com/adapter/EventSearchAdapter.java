package app.nahehuo.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.bean.EventSearchList;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/1/27.
 */
public class EventSearchAdapter extends BaseAdapter {

    private Context mContext;
    private List<EventSearchList> mListDicts = new ArrayList<>();


    public EventSearchAdapter(Context context, List<EventSearchList>  listDicts) {
        mContext = context;
        mListDicts = listDicts;
    }


    @Override public int getCount() {
        return mListDicts.size();
    }


    @Override public Object getItem(int position) {
        return mListDicts.get(position);
    }


    @Override public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EventSearchList item = mListDicts.get(position);
        ViewHolder viewHolder;
        View v;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            v = LayoutInflater.from(mContext)
                              .inflate(R.layout.item_search_list, null);
            viewHolder.iv_event_pic = (ImageView) v.findViewById(
                    R.id.iv_event_pic);
            viewHolder.tv_event_title = (TextView) v.findViewById(
                    R.id.tv_event_title);
            viewHolder.tv_event_time = (TextView) v.findViewById(
                    R.id.tv_event_time);
            viewHolder.tv_event_location = (TextView) v.findViewById(
                    R.id.tv_event_location);
            viewHolder.tv_event_distance = (TextView) v.findViewById(
                    R.id.tv_event_distance);
            viewHolder.tv_event_number = (TextView) v.findViewById(
                    R.id.tv_event_number);
            viewHolder.tv_tag = (TextView) v.findViewById(R.id.tv_tag);
            viewHolder.ll_tag = (LinearLayout) v.findViewById(R.id.ll_tag);
            v.setTag(viewHolder);
        }
        else {
            v = convertView;
            viewHolder = (ViewHolder) v.getTag();
        }
        ImageLoader.getInstance()
                   .displayImage(item.getIv_event_pic(),
                           viewHolder.iv_event_pic);
        viewHolder.tv_event_title.setText(item.getTv_event_title());
        viewHolder.tv_event_time.setText(item.getTv_event_time());
        viewHolder.tv_event_location.setText(item.getTv_event_location());
        viewHolder.tv_event_distance.setText(item.getTv_event_distance());
        viewHolder.tv_event_number.setText(item.getTv_event_number());
        if (item.getType() == 0) {
            viewHolder.ll_tag.setBackgroundDrawable(mContext.getResources()
                                                            .getDrawable(
                                                                    R.drawable.checked_bg_green));
            viewHolder.tv_tag.setText("免费");
        }
        else {
            viewHolder.ll_tag.setBackgroundDrawable(mContext.getResources()
                                                            .getDrawable(
                                                                    R.drawable.checked_bg_red));
            viewHolder.tv_tag.setText("收费");
        }
        return v;
    }


    class ViewHolder {
        ImageView iv_event_pic;
        TextView tv_event_title;
        TextView tv_event_time;
        TextView tv_event_location;
        TextView tv_event_distance;
        TextView tv_event_number;
        TextView tv_tag;
        LinearLayout ll_tag;
    }
}

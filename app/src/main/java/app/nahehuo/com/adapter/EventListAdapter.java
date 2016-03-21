package app.nahehuo.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.bean.EventListDict;
import com.makeramen.RoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/1/25.
 */
public class EventListAdapter extends BaseAdapter {

    private List<EventListDict> mListDicts = new ArrayList<>();
    private Context mContext;


    public EventListAdapter(List<EventListDict> listDicts, Context context) {
        mListDicts = listDicts;
        mContext = context;
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
        EventListDict item = mListDicts.get(position);
        ViewHolder viewHolder;
        View v;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            v = LayoutInflater.from(mContext)
                              .inflate(R.layout.item_event_list, null);
            viewHolder.tv_event_title = (TextView) v.findViewById(
                    R.id.tv_event_title);
            viewHolder.tv_per_name = (TextView) v.findViewById(
                    R.id.tv_per_name);
            viewHolder.tv_per_pos = (TextView) v.findViewById(R.id.tv_per_pos);
            viewHolder.tv_event_time = (TextView) v.findViewById(
                    R.id.tv_event_time);
            viewHolder.tv_event_number = (TextView) v.findViewById(
                    R.id.tv_event_number);
            viewHolder.tv_event_location = (TextView) v.findViewById(
                    R.id.tv_event_location);
            viewHolder.tv_event_distance = (TextView) v.findViewById(
                    R.id.tv_event_distance);
            viewHolder.tv_event_comment = (TextView) v.findViewById(
                    R.id.tv_event_comment);
            viewHolder.tv_event_watch_num = (TextView) v.findViewById(
                    R.id.tv_event_watch_num);
            viewHolder.tv_event_com_num = (TextView) v.findViewById(
                    R.id.tv_event_com_num);
            viewHolder.tv_event_per_num = (TextView) v.findViewById(
                    R.id.tv_event_per_num);
            viewHolder.iv_per_avater = (RoundedImageView) v.findViewById(
                    R.id.iv_per_avater);
            viewHolder.ll_tag = (LinearLayout) v.findViewById(R.id.ll_tag);
            viewHolder.tv_tag = (TextView) v.findViewById(R.id.tv_tag);
            v.setTag(viewHolder);
        }
        else {
            v = convertView;
            viewHolder = (ViewHolder) v.getTag();
        }
        viewHolder.tv_event_com_num.setText(item.getTv_event_com_num());
        viewHolder.tv_event_per_num.setText(item.getTv_event_per_num());
        viewHolder.tv_event_watch_num.setText(item.getTv_event_watch_num());
        viewHolder.tv_event_distance.setText(item.getTv_event_distance());
        viewHolder.tv_event_location.setText(item.getTv_event_location());
        viewHolder.tv_event_number.setText(item.getTv_event_number()+"人");
        viewHolder.tv_event_time.setText(item.getTv_event_time());
        viewHolder.tv_per_pos.setText(item.getTv_per_pos());
        viewHolder.tv_per_name.setText(item.getTv_per_name());
        viewHolder.tv_event_title.setText(item.getTv_event_title());
        ImageLoader.getInstance()
                   .displayImage(item.getIv_per_avater(),
                           viewHolder.iv_per_avater);
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
            viewHolder.tv_tag.setText(item.getEvent_price()+"元/人");
        }
        return v;
    }


    static class ViewHolder {
        TextView tv_event_title;
        TextView tv_per_name;
        TextView tv_per_pos;
        TextView tv_event_time;
        TextView tv_event_number;
        TextView tv_event_location;
        TextView tv_event_distance;
        TextView tv_event_comment;
        TextView tv_event_watch_num;
        TextView tv_event_com_num;
        TextView tv_event_per_num;
        RoundedImageView iv_per_avater;
        LinearLayout ll_tag;
        TextView tv_tag;
    }
}

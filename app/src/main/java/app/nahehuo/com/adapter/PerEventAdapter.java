package app.nahehuo.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.bean.PerEvent;
import java.util.List;

/**
 * Created by wyb on 2016/3/3.
 */
public class PerEventAdapter extends BaseAdapter {

    private List<PerEvent> mPerEvents;

    private Context mContext;


    public PerEventAdapter(List<PerEvent> perEvents, Context context) {
        mPerEvents = perEvents;
        mContext = context;
    }


    @Override public int getCount() {
        return mPerEvents.size();
    }


    @Override public Object getItem(int position) {
        return mPerEvents.get(position);
    }


    @Override public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PerEvent perEvent = mPerEvents.get(position);
        ViewHolder viewHolder;
        View view;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext)
                                 .inflate(R.layout.item_per_event_list, null);
            viewHolder.ll_person = (LinearLayout) view.findViewById(
                    R.id.ll_person);
            viewHolder.ll_tag = (LinearLayout) view.findViewById(R.id.ll_tag);
            viewHolder.tv_event_distance = (TextView) view.findViewById(
                    R.id.tv_event_distance);
            viewHolder.tv_event_location = (TextView) view.findViewById(
                    R.id.tv_event_location);
            viewHolder.tv_tag = (TextView) view.findViewById(R.id.tv_tag);
            viewHolder.tv_event_number = (TextView) view.findViewById(
                    R.id.tv_event_number);
            viewHolder.tv_event_time = (TextView) view.findViewById(
                    R.id.tv_event_time);
            viewHolder.tv_per_pos = (TextView) view.findViewById(
                    R.id.tv_per_pos);
            viewHolder.tv_per_name = (TextView) view.findViewById(
                    R.id.tv_per_name);
            viewHolder.tv_type = (TextView) view.findViewById(R.id.tv_type);
            viewHolder.tv_event_title = (TextView) view.findViewById(
                    R.id.tv_event_title);
            view.setTag(viewHolder);
        }
        else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tv_event_title.setText(perEvent.getTitle());
        viewHolder.tv_type.setText(perEvent.getType());
        viewHolder.tv_per_name.setText(perEvent.getPer_name());
        viewHolder.tv_per_pos.setText(perEvent.getPer_pos());
        viewHolder.tv_event_time.setText(perEvent.getTime());
        viewHolder.tv_event_number.setText(perEvent.getNumber());
        viewHolder.tv_tag.setText(perEvent.getTag());
        viewHolder.tv_event_location.setText(perEvent.getLocation());
        viewHolder.tv_event_distance.setText(perEvent.getDistance());
        if ("进行中".equals(perEvent.getType())) {
            viewHolder.tv_type.setBackground(mContext.getResources()
                                                     .getDrawable(
                                                             R.drawable.bg_cf_border_green));
            viewHolder.tv_type.setTextColor(
                    mContext.getResources().getColor(R.color.green));
        }
        else if ("报名截止".equals(perEvent.getType())) {
            viewHolder.tv_type.setBackground(mContext.getResources()
                                                     .getDrawable(
                                                             R.drawable.bg_cf_border_gray));
            viewHolder.tv_type.setTextColor(mContext.getResources()
                                                    .getColor(
                                                            R.color.textcolorgrayhint));
        }
        else if ("圆满结束".equals(perEvent.getType())) {
            viewHolder.tv_type.setBackground(mContext.getResources()
                                                     .getDrawable(
                                                             R.drawable.bg_cf_border_red));
            viewHolder.tv_type.setTextColor(
                    mContext.getResources().getColor(R.color.text_red));
        }

        if ("免费".equals(perEvent.getTag())){
            viewHolder.ll_tag.setBackground(mContext.getResources()
                                                    .getDrawable(
                                                            R.drawable
                                                                    .checked_bg_green));
        }else {
            viewHolder.ll_tag.setBackground(mContext.getResources()
                                                    .getDrawable(
                                                            R.drawable.checked_bg_red));
        }
        return view;
    }


    static class ViewHolder {
        TextView tv_event_title;
        TextView tv_type;
        TextView tv_per_name;
        TextView tv_per_pos;
        TextView tv_event_time;
        TextView tv_event_number, tv_tag, tv_event_location, tv_event_distance;
        LinearLayout ll_tag, ll_person;
    }
}

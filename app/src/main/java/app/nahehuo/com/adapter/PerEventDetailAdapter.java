package app.nahehuo.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.bean.PersonEvent;
import java.util.List;

/**
 * Created by Administrator on 2016/3/4.
 */
public class PerEventDetailAdapter extends BaseAdapter {

    private List<PersonEvent> mPersonEvents;

    private Context mContext;


    public PerEventDetailAdapter(List<PersonEvent> personEvents, Context context) {
        mPersonEvents = personEvents;
        mContext = context;
    }


    @Override public int getCount() {
        return mPersonEvents.size();
    }


    @Override public Object getItem(int position) {
        return mPersonEvents.get(position);
    }


    @Override public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PersonEvent personEvent = mPersonEvents.get(position);
        ViewHolder viewHolder;
        View v;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            v = LayoutInflater.from(mContext)
                              .inflate(R.layout.item_per_event_detail, null);
            viewHolder.tv_name = (TextView) v.findViewById(R.id.tv_name);
            viewHolder.tv_position = (TextView) v.findViewById(
                    R.id.tv_position);
            v.setTag(viewHolder);
        }
        else {
            v = convertView;
            viewHolder = (ViewHolder) v.getTag();
        }
        viewHolder.tv_name.setText(personEvent.getName());
        viewHolder.tv_position.setText(personEvent.getPosition());
        return v;
    }


    static class ViewHolder {
        TextView tv_name;
        TextView tv_position;
    }
}

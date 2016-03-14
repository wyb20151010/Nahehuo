package app.nahehuo.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.bean.JobPartDict;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyb on 2016/1/13.
 */
public class JobParttimeAdapter extends BaseAdapter {

    private List<JobPartDict> mJobPartDicts = new ArrayList<>();
    private Context mContext;


    public JobParttimeAdapter(List<JobPartDict> jobPartDicts, Context context) {
        mJobPartDicts = jobPartDicts;
        mContext = context;
    }


    @Override public int getCount() {
        return mJobPartDicts.size();
    }


    @Override public Object getItem(int position) {
        return mJobPartDicts.get(position);
    }


    @Override public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        JobPartDict item = mJobPartDicts.get(position);
        View v;
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            v = LayoutInflater.from(mContext)
                              .inflate(R.layout.item_part_time, null);
            viewHolder.part_job_name = (TextView) v.findViewById(
                    R.id.part_job_name);
            viewHolder.part_job_location = (TextView) v.findViewById(
                    R.id.part_job_location);
            viewHolder.part_job_title = (TextView) v.findViewById(
                    R.id.part_job_title);
            viewHolder.part_job_content = (TextView) v.findViewById(
                    R.id.part_job_content);
            viewHolder.part_job_time = (TextView) v.findViewById(
                    R.id.part_job_time);
            viewHolder.part_job_see = (TextView) v.findViewById(
                    R.id.part_job_see);
            v.setTag(viewHolder);
        }
        else {
            v = convertView;
            viewHolder = (ViewHolder) v.getTag();
        }
        viewHolder.part_job_name.setText(item.getName());
        viewHolder.part_job_location.setText(item.getLocation());
        viewHolder.part_job_title.setText(item.getTitle());
        viewHolder.part_job_content.setText(item.getContent());
        viewHolder.part_job_time.setText(item.getTime());
        viewHolder.part_job_see.setText(item.getSee());
        return v;
    }


    static class ViewHolder {

        TextView part_job_name;
        TextView part_job_location;
        TextView part_job_title;
        TextView part_job_content;
        TextView part_job_time;
        TextView part_job_see;
    }
}

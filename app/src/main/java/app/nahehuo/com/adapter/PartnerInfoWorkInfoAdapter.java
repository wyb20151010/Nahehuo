package app.nahehuo.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.bean.WorkInfo;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/2/24.
 */
public class PartnerInfoWorkInfoAdapter extends BaseAdapter {

    private List<WorkInfo> mWorkInfos = new ArrayList<>();

    private Context mContext;


    public PartnerInfoWorkInfoAdapter(List<WorkInfo> workInfos, Context context) {
        mWorkInfos = workInfos;
        mContext = context;
    }


    @Override public int getCount() {
        return mWorkInfos.size();
    }


    @Override public Object getItem(int position) {
        return mWorkInfos.get(position);
    }


    @Override public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        WorkInfo workInfo = mWorkInfos.get(position);
        ViewHolder viewHolder;
        View v;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            v = LayoutInflater.from(mContext)
                              .inflate(R.layout.item_work_info, null);
            viewHolder.tv_company = (TextView) v.findViewById(R.id.tv_company);
            viewHolder.tv_position = (TextView) v.findViewById(
                    R.id.tv_position);
            viewHolder.tv_time = (TextView) v.findViewById(R.id.tv_time);
            v.setTag(viewHolder);
        }
        else {
            v = convertView;
            viewHolder = (ViewHolder) v.getTag();
        }
        viewHolder.tv_company.setText(workInfo.getCompany());
        viewHolder.tv_position.setText(workInfo.getPosition());
        viewHolder.tv_time.setText(workInfo.getTime());
        return v;
    }


    static class ViewHolder {
        TextView tv_position;
        TextView tv_company;
        TextView tv_time;
    }
}

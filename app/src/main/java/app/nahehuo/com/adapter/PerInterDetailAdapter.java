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
import app.nahehuo.com.bean.PerInterDetail;
import java.util.List;

/**
 * Created by wyb on 2016/3/2.
 */
public class PerInterDetailAdapter extends BaseAdapter {

    private List<PerInterDetail> mPerInterDetails;

    private Context mContext;
    boolean isopen = true;


    public PerInterDetailAdapter(List<PerInterDetail> perInterDetails, Context context) {
        mPerInterDetails = perInterDetails;
        mContext = context;
    }


    @Override public int getCount() {
        return mPerInterDetails.size();
    }


    @Override public Object getItem(int position) {
        return mPerInterDetails.get(position);
    }


    @Override public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PerInterDetail item = mPerInterDetails.get(position);
        final ViewHolder viewHolder;
        View v_more;
        if (convertView == null) {
            viewHolder = new ViewHolder();

            v_more = LayoutInflater.from(mContext)
                                   .inflate(R.layout.item_pro_inter_detail_more,
                                           null);
            viewHolder.tv_title = (TextView) v_more.findViewById(R.id.tv_title);
            viewHolder.tv_msg_time = (TextView) v_more.findViewById(
                    R.id.tv_msg_time);
            viewHolder.tv_time = (TextView) v_more.findViewById(R.id.tv_time);
            viewHolder.tv_location = (TextView) v_more.findViewById(
                    R.id.tv_location);
            viewHolder.tv_hr = (TextView) v_more.findViewById(R.id.tv_hr);
            viewHolder.ll_more = (LinearLayout) v_more.findViewById(
                    R.id.ll_more);
            viewHolder.iv_icon = (ImageView) v_more.findViewById(R.id.iv_icon);
            v_more.setTag(viewHolder);
        }
        else {
            v_more = convertView;
            viewHolder = (ViewHolder) v_more.getTag();
        }
        viewHolder.tv_title.setText(item.getStatus_name());
        viewHolder.tv_msg_time.setText(item.getStatus_name());
        viewHolder.tv_time.setText(item.getTime());
        viewHolder.tv_location.setText(item.getLocation());
        viewHolder.tv_hr.setText(item.getHrphone());
        if ("1".equals(item.getStatus())) {
            viewHolder.iv_icon.setVisibility(View.VISIBLE);
            viewHolder.ll_more.setVisibility(View.VISIBLE);
            viewHolder.tv_title.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    if (isopen) {
                        viewHolder.ll_more.setVisibility(View.GONE);
                        isopen = false;
                    }
                    else {
                        viewHolder.ll_more.setVisibility(View.VISIBLE);
                        isopen = true;
                    }
                }
            });
        }
        else {
            viewHolder.iv_icon.setVisibility(View.GONE);
            viewHolder.ll_more.setVisibility(View.GONE);
        }
        return v_more;
    }


    static class ViewHolder {
        TextView tv_title;
        TextView tv_msg_time, tv_time, tv_location, tv_hr;
        LinearLayout ll_more;
        ImageView iv_icon;
    }
}

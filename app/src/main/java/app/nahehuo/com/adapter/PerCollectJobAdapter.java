package app.nahehuo.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.bean.PerCollectJob;
import app.nahehuo.com.bean.TagStatus;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import java.util.List;

/**
 * Created by wyb on 2016/3/3.
 */
public class PerCollectJobAdapter extends BaseAdapter {

    private List<PerCollectJob> mPerCollectJobs;
    private Context mContext;


    public PerCollectJobAdapter(List<PerCollectJob> perCollectJobs, Context context) {
        mPerCollectJobs = perCollectJobs;
        mContext = context;
    }


    @Override public int getCount() {
        return mPerCollectJobs.size();
    }


    @Override public Object getItem(int position) {
        return mPerCollectJobs.get(position);
    }


    @Override public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PerCollectJob item = mPerCollectJobs.get(position);
        final List<TagStatus> tags = item.getTags();
        View v;
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            v = LayoutInflater.from(mContext)
                              .inflate(R.layout.item_per_collect_job, null);
            viewHolder.tv_company = (TextView) v.findViewById(R.id.tv_company);
            viewHolder.tv_position = (TextView) v.findViewById(
                    R.id.tv_position);
            viewHolder.tfl_info = (TagFlowLayout) v.findViewById(R.id.tfl_info);
            v.setTag(viewHolder);
        }
        else {
            v = convertView;
            viewHolder = (ViewHolder) v.getTag();
        }
        viewHolder.tv_position.setText(item.getPosition());
        viewHolder.tv_company.setText(item.getCompany());
        viewHolder.tfl_info.setAdapter(new TagAdapter(tags) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {

                LayoutInflater inflater
                        = (LayoutInflater) mContext.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                TextView v = (TextView) inflater.inflate(
                        R.layout.item_tag_bule1, parent, false);
                v.setText(tags.get(position).getName());
                if ("1".equals(tags.get(position).getType())) {
                    v.setBackgroundDrawable(mContext.getResources()
                                                    .getDrawable(
                                                            R.drawable.bg_shape_round_red));
                }
                else {
                    v.setBackgroundDrawable(mContext.getResources()
                                                    .getDrawable(
                                                            R.drawable.bg_shape_round2));
                }
                return v;
            }
        });
        return v;
    }


    static class ViewHolder {
        TextView tv_position;
        TextView tv_company;
        TagFlowLayout tfl_info;
    }
}

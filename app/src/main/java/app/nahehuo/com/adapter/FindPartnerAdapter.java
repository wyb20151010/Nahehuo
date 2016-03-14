package app.nahehuo.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.bean.ProjectListDict;
import com.zhy.view.flowlayout.TagFlowLayout;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/21.
 */
public class FindPartnerAdapter extends BaseAdapter {
    private List<ProjectListDict> mProjectListDicts = new ArrayList<>();
    private Context mContext;


    public FindPartnerAdapter(List<ProjectListDict> projectListDicts, Context mContext) {
        mProjectListDicts = projectListDicts;
        this.mContext = mContext;
    }


    @Override public int getCount() {
        return mProjectListDicts.size();
    }


    @Override public Object getItem(int position) {
        return mProjectListDicts.get(position);
    }


    @Override public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ProjectListDict item = mProjectListDicts.get(position);
        View v;
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            v = LayoutInflater.from(mContext)
                              .inflate(R.layout.item_project_partner, parent,
                                      false);
            viewHolder.iv_title_avater = (ImageView) v.findViewById(
                    R.id.iv_per_avater);
            viewHolder.iv_per_avater = (ImageView) v.findViewById(
                    R.id.iv_per_avater);
            viewHolder.tv_pro_title = (TextView) v.findViewById(
                    R.id.tv_pro_title);
            viewHolder.tv_pro_title_detail = (TextView) v.findViewById(
                    R.id.tv_pro_title_detail);
            viewHolder.tv_per_name = (TextView) v.findViewById(
                    R.id.tv_per_name);
            viewHolder.tv_per_pos = (TextView) v.findViewById(R.id.tv_per_pos);
            viewHolder.tv_location= (TextView) v.findViewById(R.id.tv_location);
            viewHolder.tv_find= (TextView) v.findViewById(R.id.tv_find);
            v.setTag(viewHolder);
        }
        else {
            v = convertView;
            viewHolder = (ViewHolder) v.getTag();
        }
        viewHolder.tv_per_pos.setText(item.getProjectComPosition());
        viewHolder.tv_pro_title.setText(item.getProjectTitle());
        viewHolder.tv_pro_title_detail.setText(item.getProjectTitleDetail());
        viewHolder.tv_per_name.setText(item.getProjectComName());
        viewHolder.tv_location.setText(item.getTagPosition());
        viewHolder.tv_find.setText(item.getTagFind());
        return v;
    }


    class ViewHolder {
        ImageView iv_title_avater;
        ImageView iv_per_avater;
        TextView tv_pro_title;
        TextView tv_pro_title_detail;
        TextView tv_per_name;
        TextView tv_per_pos;
        TagFlowLayout tfl_pro_tag;
        TextView tv_location;
        TextView tv_find;
    }
}

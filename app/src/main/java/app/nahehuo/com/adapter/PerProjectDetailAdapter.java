package app.nahehuo.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.bean.PerProjectDetail;
import java.util.List;

/**
 * Created by wyb on 2016/3/4.
 */
public class PerProjectDetailAdapter extends BaseAdapter {

    private List<PerProjectDetail> mPerProjectDetails;
    private Context mContext;


    public PerProjectDetailAdapter(List<PerProjectDetail> perProjectDetails, Context context) {
        mPerProjectDetails = perProjectDetails;
        mContext = context;
    }


    @Override public int getCount() {
        return mPerProjectDetails.size();
    }


    @Override public Object getItem(int position) {
        return mPerProjectDetails.get(position);
    }


    @Override public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PerProjectDetail detail=mPerProjectDetails.get(position);
        ViewHolder viewHolder;
        View view;
        if(convertView==null){
            viewHolder=new ViewHolder();
            view= LayoutInflater.from(mContext).inflate(R.layout
                    .item_per_project_detail,null);
            viewHolder.tv_email= (TextView) view.findViewById(R.id.tv_email);
            viewHolder.tv_phone= (TextView) view.findViewById(R.id.tv_phone);
            viewHolder.tv_position= (TextView) view.findViewById(R.id.tv_position);
            viewHolder.tv_name= (TextView) view.findViewById(R.id.tv_name);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.tv_email.setText(detail.getEmail());
        viewHolder.tv_name.setText(detail.getName());
        viewHolder.tv_phone.setText(detail.getPhone());
        viewHolder.tv_position.setText(detail.getPosition());
        return view;
    }


    static class ViewHolder {
        TextView tv_name, tv_position, tv_phone, tv_email;
    }
}

package app.nahehuo.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.bean.PerProjectPub;
import java.util.List;

/**
 * Created by Administrator on 2016/3/4.
 */
public class PerProjectPubAdapter extends BaseAdapter {

    private List<PerProjectPub> mPubs;
    private Context mContext;


    public PerProjectPubAdapter(List<PerProjectPub> pubs, Context context) {
        mPubs = pubs;
        mContext = context;
    }


    @Override public int getCount() {
        return mPubs.size();
    }


    @Override public Object getItem(int position) {
        return mPubs.get(position);
    }


    @Override public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PerProjectPub pub = mPubs.get(position);
        ViewHolder viewHolder;
        View v;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            v = LayoutInflater.from(mContext)
                              .inflate(R.layout.item_per_pro_pub, null);
            viewHolder.tv_title = (TextView) v.findViewById(R.id.tv_title);
            viewHolder.tv_time = (TextView) v.findViewById(R.id.tv_time);
            viewHolder.tv_type = (TextView) v.findViewById(R.id.tv_type);
            viewHolder.tv_one = (TextView) v.findViewById(R.id.tv_one);
            viewHolder.tv_two = (TextView) v.findViewById(R.id.tv_two);
            viewHolder.iv_one = (ImageView) v.findViewById(R.id.iv_one);
            viewHolder.iv_two = (ImageView) v.findViewById(R.id.iv_two);
            v.setTag(viewHolder);
        }
        else {
            v = convertView;
            viewHolder = (ViewHolder) v.getTag();
        }
        viewHolder.tv_title.setText(pub.getTitle());
        viewHolder.tv_time.setText(pub.getTime());
        viewHolder.tv_type.setText(pub.getType());
        if ("审核中".equals(pub.getType())) {
            viewHolder.tv_type.setBackground(mContext.getResources()
                                                     .getDrawable(
                                                             R.drawable.bg_cf_border_green));
            viewHolder.tv_one.setText("查看审核进度");
            viewHolder.tv_two.setText("删除");
            viewHolder.iv_one.setImageResource(R.drawable.my_event_progress);
            viewHolder.iv_two.setImageResource(R.drawable.my_event_delete);
            viewHolder.tv_type.setTextColor(
                    mContext.getResources().getColor(R.color.green));
        }
        else if ("进行中".equals(pub.getType())) {
            viewHolder.tv_type.setBackground(mContext.getResources()
                                                     .getDrawable(
                                                             R.drawable.bg_cf_border_bule));
            viewHolder.tv_one.setText("标为完成合伙");
            viewHolder.tv_two.setText("约谈目录");
            viewHolder.iv_one.setImageResource(R.drawable.my_project_finish);
            viewHolder.iv_two.setImageResource(R.drawable.my_project_yuetan);
            viewHolder.tv_type.setTextColor(
                    mContext.getResources().getColor(R.color.colorPrimary));
        }
        else if ("合伙成功".equals(pub.getType())) {
            viewHolder.tv_type.setBackground(mContext.getResources()
                                                     .getDrawable(
                                                             R.drawable.bg_cf_border_red));
            viewHolder.tv_one.setText("已标为完成合伙");
            viewHolder.tv_two.setText("约谈目录");
            viewHolder.iv_one.setImageResource(R.drawable.my_project_finish);
            viewHolder.iv_two.setImageResource(R.drawable.my_project_yuetan);
            viewHolder.tv_type.setTextColor(
                    mContext.getResources().getColor(R.color.text_red));
        }
        return v;
    }


    static class ViewHolder {
        TextView tv_title;
        TextView tv_time;
        TextView tv_type, tv_one, tv_two;
        ImageView iv_one, iv_two;
    }
}

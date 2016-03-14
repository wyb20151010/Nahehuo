package app.nahehuo.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.bean.PartnerAll;
import java.util.List;

/**
 * Created by WYB on 2016/2/19.
 */
public class PartnerAllAdapter extends BaseAdapter {

    private List<PartnerAll> mPartnerAlls;

    private Context mContext;


    public PartnerAllAdapter(List<PartnerAll> partnerAlls, Context context) {
        mPartnerAlls = partnerAlls;
        mContext = context;
    }


    @Override public int getCount() {
        return mPartnerAlls.size();
    }


    @Override public Object getItem(int position) {
        return mPartnerAlls.get(position);
    }


    @Override public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PartnerAll item = mPartnerAlls.get(position);
        ViewHolder viewHolder;
        View v;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            v = LayoutInflater.from(mContext)
                              .inflate(R.layout.item_all_partner, null);
            viewHolder.tv_name = (TextView) v.findViewById(R.id.tv_name);
            viewHolder.tv_position = (TextView) v.findViewById(
                    R.id.tv_position);
            viewHolder.btn_attention = (Button) v.findViewById(
                    R.id.btn_attention);
            v.setTag(viewHolder);
        }
        else {
            v = convertView;
            viewHolder = (ViewHolder) v.getTag();
        }
        viewHolder.tv_name.setText(item.getName());
        viewHolder.tv_position.setText(item.getPosition());
        if ("1".equals(item.getAttenType())) {
            viewHolder.btn_attention.setText("+关注");
            viewHolder.btn_attention.setBackgroundDrawable(
                    mContext.getResources()
                            .getDrawable(R.drawable.bg_shape_round));
            viewHolder.btn_attention.setOnClickListener(
                    new View.OnClickListener() {
                        @Override public void onClick(View v) {
                            Button temp = (Button) v;
                            temp.setText("等待接受");
                            temp.setBackgroundDrawable(mContext.getResources()
                                                               .getDrawable(
                                                                       R.drawable.checked_bg_gray));
                            temp.setTextSize(10);
                        }
                    });
        }
        else if ("2".equals(item.getAttenType())) {
            viewHolder.btn_attention.setText("加好友");
            viewHolder.btn_attention.setBackgroundDrawable(
                    mContext.getResources()
                            .getDrawable(R.drawable.checked_bg_green));
            viewHolder.btn_attention.setOnClickListener(
                    new View.OnClickListener() {
                        @Override public void onClick(View v) {
                            Button temp = (Button) v;
                            temp.setText("等待接受");
                            temp.setBackgroundDrawable(mContext.getResources()
                                                               .getDrawable(
                                                                       R.drawable.checked_bg_gray));
                            temp.setTextSize(10);
                        }
                    });
        }
        else if ("3".equals(item.getAttenType())) {
            viewHolder.btn_attention.setText("等待接受");
            viewHolder.btn_attention.setBackgroundDrawable(
                    mContext.getResources()
                            .getDrawable(R.drawable.checked_bg_gray));
            viewHolder.btn_attention.setTextSize(10);
        }
        return v;
    }


    static class ViewHolder {
        TextView tv_name;
        TextView tv_position;
        Button btn_attention;
    }
}

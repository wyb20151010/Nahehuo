package app.nahehuo.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.application.MyApplication;
import app.nahehuo.com.bean.MyInter;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.List;

/**
 * Created by wyb on 2016/3/2.
 */
public class PerMyInterAdapter extends BaseAdapter {

    private List<MyInter> mMyInters;
    private Context mContext;


    public PerMyInterAdapter(List<MyInter> myInters, Context context) {
        mMyInters = myInters;
        mContext = context;
    }


    @Override public int getCount() {
        return mMyInters.size();
    }


    @Override public Object getItem(int position) {
        return mMyInters.get(position);
    }


    @Override public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MyInter item = mMyInters.get(position);
        ViewHolder viewHolder;
        View v;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            v = LayoutInflater.from(mContext)
                              .inflate(R.layout.item_per_my_inter, null);
            viewHolder.tv_company = (TextView) v.findViewById(R.id.tv_company);
            viewHolder.tv_position = (TextView) v.findViewById(
                    R.id.tv_position);
            viewHolder.tv_salary = (TextView) v.findViewById(R.id.tv_salary);
            viewHolder.tv_time = (TextView) v.findViewById(R.id.tv_time);
            viewHolder.tv_status = (TextView) v.findViewById(R.id.tv_status);
            viewHolder.iv_logo = (ImageView) v.findViewById(R.id.iv_logo);
            v.setTag(viewHolder);
        }
        else {
            v = convertView;
            viewHolder = (ViewHolder) v.getTag();
        }
        viewHolder.tv_position.setText(item.getPosition());
        viewHolder.tv_company.setText(item.getCompany());
        viewHolder.tv_salary.setText(item.getSalary());
        viewHolder.tv_time.setText(item.getTime());
        viewHolder.tv_status.setText(item.getStatus_name());
        ImageLoader.getInstance()
                   .displayImage(item.getLogo(), viewHolder.iv_logo,
                           MyApplication.getDisplayDefaultOption());

        //
        if ("1".equals(item.getStatus())) {
            viewHolder.tv_status.setVisibility(View.VISIBLE);
            viewHolder.tv_status.setBackgroundDrawable(mContext.getResources()
                                                               .getDrawable(
                                                                       R.drawable.bg_zf_my_inter));
            viewHolder.tv_status.setTextColor(
                    mContext.getResources().getColor(R.color.jobtitlecolor));
        }
        else if ("2".equals(item.getStatus())) {
            viewHolder.tv_status.setVisibility(View.VISIBLE);
            viewHolder.tv_status.setBackgroundDrawable(mContext.getResources()
                                                               .getDrawable(
                                                                       R.drawable.bg_zf_my_inter2));
            viewHolder.tv_status.setTextColor(
                    mContext.getResources().getColor(R.color.white));
        }
        else if ("3".equals(item.getStatus())) {
            viewHolder.tv_status.setVisibility(View.VISIBLE);
            viewHolder.tv_status.setBackgroundDrawable(mContext.getResources()
                                                               .getDrawable(
                                                                       R.drawable.bg_zf_my_inter3));
            viewHolder.tv_status.setTextColor(
                    mContext.getResources().getColor(R.color.white));
        }
        else if ("4".equals(item.getStatus())) {
            viewHolder.tv_status.setVisibility(View.VISIBLE);
            viewHolder.tv_status.setBackgroundDrawable(mContext.getResources()
                                                               .getDrawable(
                                                                       R.drawable.bg_zf_my_inter4));
            viewHolder.tv_status.setTextColor(
                    mContext.getResources().getColor(R.color.white));
        }

        return v;
    }


    static class ViewHolder {
        TextView tv_position;
        TextView tv_company;
        TextView tv_salary;
        TextView tv_time;
        TextView tv_status;
        ImageView iv_logo;
    }
}

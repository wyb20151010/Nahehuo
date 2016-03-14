package app.nahehuo.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.bean.PersonalPay;
import java.util.List;

/**
 * Created by WYB on 2016/3/1.
 */
public class PersonalPayAdapter extends BaseAdapter {

    private List<PersonalPay> mPersonalPays;
    private Context mContext;


    public PersonalPayAdapter(List<PersonalPay> personalPays, Context context) {
        mPersonalPays = personalPays;
        mContext = context;
    }


    @Override public int getCount() {
        return mPersonalPays.size();
    }


    @Override public Object getItem(int position) {
        return mPersonalPays.get(position);
    }


    @Override public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PersonalPay personalPay = mPersonalPays.get(position);
        ViewHolder viewHolder;
        View v;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            v = LayoutInflater.from(mContext)
                              .inflate(R.layout.item_per_pay, null);
            viewHolder.tv_type = (TextView) v.findViewById(R.id.tv_type);
            viewHolder.tv_money = (TextView) v.findViewById(R.id.tv_money);
            viewHolder.tv_tradestatus = (TextView) v.findViewById(
                    R.id.tv_tradestatus);
            viewHolder.tv_time = (TextView) v.findViewById(R.id.tv_time);
            v.setTag(viewHolder);
        }
        else {
            v = convertView;
            viewHolder = (ViewHolder) v.getTag();
        }
        viewHolder.tv_money.setText(personalPay.getMoney());
        viewHolder.tv_type.setText(personalPay.getType());
        viewHolder.tv_time.setText(personalPay.getTime());
        if ("1".equals(personalPay.getTradestatus())) {
            viewHolder.tv_tradestatus.setText("处理中");
            viewHolder.tv_tradestatus.setTextColor(
                    mContext.getResources().getColor(R.color.text_red));
            viewHolder.tv_money.setTextColor(
                    mContext.getResources().getColor(R.color.text_red));
        }
        else if ("2".equals(personalPay.getTradestatus())) {
            viewHolder.tv_tradestatus.setText("交易成功");
            viewHolder.tv_tradestatus.setTextColor(
                    mContext.getResources().getColor(R.color.colorPrimary));
            viewHolder.tv_tradestatus.setTextColor(
                    mContext.getResources().getColor(R.color.colorPrimary));
        }

        return v;
    }


    static class ViewHolder {
        TextView tv_type;
        TextView tv_money;
        TextView tv_tradestatus;
        TextView tv_time;
    }
}

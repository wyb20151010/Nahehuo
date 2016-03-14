package app.nahehuo.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.bean.PersonWorkExp;
import java.util.List;

/**
 * Created by Administrator on 2016/3/3.
 */
public class PerMyResAdapter extends BaseAdapter {

    private List<PersonWorkExp> mPersonWorkExps;
    private Context mContext;


    public PerMyResAdapter(List<PersonWorkExp> personWorkExps, Context context) {
        mPersonWorkExps = personWorkExps;
        mContext = context;
    }


    @Override public int getCount() {
        return mPersonWorkExps.size();
    }


    @Override public Object getItem(int position) {
        return mPersonWorkExps.get(position);
    }


    @Override public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PersonWorkExp workExp = mPersonWorkExps.get(position);
        ViewHolder viewHolder;
        View view;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext)
                                 .inflate(R.layout.item_pro_work_exp, null);
            viewHolder.tv_time = (TextView) view.findViewById(R.id.tv_time);
            viewHolder.tv_position = (TextView) view.findViewById(
                    R.id.tv_position);
            viewHolder.tv_company = (TextView) view.findViewById(
                    R.id.tv_company);
            view.setTag(viewHolder);
        }
        else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tv_company.setText(workExp.getCompany());
        viewHolder.tv_position.setText(workExp.getPosition());
        viewHolder.tv_time.setText(workExp.getTime());
        return view;
    }


    static class ViewHolder {
        TextView tv_time;
        TextView tv_position;
        TextView tv_company;
    }
}

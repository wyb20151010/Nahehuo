package app.nahehuo.com.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.bean.WorkExp;
import app.nahehuo.com.ui.personal.PerInfoEduExpDetailActivity;
import app.nahehuo.com.ui.personal.PerInfoWkExpDetailActivity;
import java.util.List;

/**
 * Created by WYB on 2016/2/29.
 */
public class PersonalWkExpAdapter extends BaseAdapter {

    private List<WorkExp> mWorkExps;

    private Context mContext;


    public PersonalWkExpAdapter(List<WorkExp> workExps, Context context) {
        mWorkExps = workExps;
        mContext = context;
    }


    @Override public int getCount() {
        return mWorkExps.size();
    }


    @Override public Object getItem(int position) {
        return mWorkExps.get(position);
    }


    @Override public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final WorkExp workExp = mWorkExps.get(position);
        ViewHolder viewHolder;
        View v;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            v = LayoutInflater.from(mContext)
                              .inflate(R.layout.item_per_wk_exp, null);
            viewHolder.tv_company = (TextView) v.findViewById(R.id.tv_company);
            viewHolder.tv_time = (TextView) v.findViewById(R.id.tv_time);
            viewHolder.tv_edit = (TextView) v.findViewById(R.id.tv_edit);
            viewHolder.tv_position = (TextView) v.findViewById(
                    R.id.tv_position);
            v.setTag(viewHolder);
        }
        else {
            v = convertView;
            viewHolder = (ViewHolder) v.getTag();
        }
        viewHolder.tv_company.setText(workExp.getCompany());
        viewHolder.tv_position.setText(workExp.getPosition());
        viewHolder.tv_time.setText(workExp.getTime());
        viewHolder.tv_edit.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if ("1".equals(workExp.getType())) {
                    Intent intent = new Intent(mContext,
                            PerInfoWkExpDetailActivity.class);
                    intent.putExtra("data", workExp);
                    mContext.startActivity(intent);
                }else if("2".equals(workExp.getType())){
                    Intent intent = new Intent(mContext,
                            PerInfoEduExpDetailActivity.class);
                    intent.putExtra("data", workExp);
                    mContext.startActivity(intent);
                }
            }
        });
        return v;
    }


    static class ViewHolder {
        TextView tv_time;
        TextView tv_edit;
        TextView tv_position;
        TextView tv_company;
    }
}

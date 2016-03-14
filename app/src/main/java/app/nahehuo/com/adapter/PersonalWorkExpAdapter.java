package app.nahehuo.com.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.bean.PerWorkExp;
import app.nahehuo.com.ui.personal.Per2VipUploadExpActivity;
import java.util.List;

/**
 * Created by WYB on 2016/2/29.
 */
public class PersonalWorkExpAdapter extends BaseAdapter {

    private List<PerWorkExp> mPerWorkExps;

    private Context mContext;


    public PersonalWorkExpAdapter(List<PerWorkExp> perWorkExps, Context context) {
        mPerWorkExps = perWorkExps;
        mContext = context;
    }


    @Override public int getCount() {
        return mPerWorkExps.size();
    }


    @Override public Object getItem(int position) {
        return mPerWorkExps.get(position);
    }


    @Override public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final PerWorkExp workExp = mPerWorkExps.get(position);
        ViewHolder viewHolder;
        View v;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            v = LayoutInflater.from(mContext)
                              .inflate(R.layout.item_work_exp, null);
            viewHolder.tv_time = (TextView) v.findViewById(R.id.tv_time);
            viewHolder.tv_company = (TextView) v.findViewById(R.id.tv_company);
            viewHolder.tv_position = (TextView) v.findViewById(
                    R.id.tv_position);
            viewHolder.btn_certificate = (Button) v.findViewById(
                    R.id.btn_certificate);
            viewHolder.ll_prove = (LinearLayout) v.findViewById(R.id.ll_prove);
            viewHolder.ll_content= (LinearLayout) v.findViewById(R.id.ll_content);
            v.setTag(viewHolder);
        }
        else {
            v = convertView;
            viewHolder = (ViewHolder) v.getTag();
        }
        viewHolder.tv_company.setText(workExp.getCompany());
        viewHolder.tv_time.setText(workExp.getTime());
        viewHolder.tv_position.setText(workExp.getPosition());
        if ("0".equals(workExp.getType())) {
            viewHolder.ll_prove.setVisibility(View.GONE);
            viewHolder.btn_certificate.setText("去认证");
        }else if("1".equals(workExp.getType())){
            viewHolder.ll_prove.setVisibility(View.VISIBLE);
            viewHolder.btn_certificate.setText("更新认证");
        }

        Intent intent = new Intent(mContext,
                Per2VipUploadExpActivity.class);
        intent.putExtra("type", mPerWorkExps.get(position).getType());
        viewHolder.ll_content.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(mContext,
                        Per2VipUploadExpActivity.class);
                intent.putExtra("type",workExp);
                mContext.startActivity(intent);
            }
        });
        return v;
    }


    static class ViewHolder {
        TextView tv_time;
        TextView tv_position;
        Button btn_certificate;
        TextView tv_company;
        LinearLayout ll_prove;
        LinearLayout ll_content;
    }
}

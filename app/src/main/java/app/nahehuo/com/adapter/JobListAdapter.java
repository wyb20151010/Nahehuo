package app.nahehuo.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.bean.ListJob;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/1/7.
 */
public class JobListAdapter extends BaseAdapter {

    private Context mContext;
    private List<ListJob> mJobListDict = new ArrayList<>();


    public JobListAdapter(Context context, List<ListJob> jobListDict) {
        mContext = context;
        mJobListDict = jobListDict;
    }


    @Override public int getCount() {
        return mJobListDict.size();
    }


    @Override public Object getItem(int position) {
        return mJobListDict.get(position);
    }


    @Override public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String financle, workexp, edu;
        ListJob item = mJobListDict.get(position);
        ViewHolder viewHolder;
        View v;
        if (convertView == null) {
            v = LayoutInflater.from(mContext)
                              .inflate(R.layout.item_job_list, null);
            viewHolder = new ViewHolder();
            viewHolder.tv_job_position = (TextView) v.findViewById(
                    R.id.tv_job_position);
            viewHolder.tv_job_salary = (TextView) v.findViewById(
                    R.id.tv_job_salary);
            viewHolder.tv_job_city = (TextView) v.findViewById(
                    R.id.tv_job_city);
            viewHolder.tv_job_work_time = (TextView) v.findViewById(
                    R.id.tv_job_work_time);
            viewHolder.tv_job_edu = (TextView) v.findViewById(R.id.tv_job_edu);
            viewHolder.tv_job_company = (TextView) v.findViewById(
                    R.id.tv_job_company);
            viewHolder.iv_job_pic = (ImageView) v.findViewById(R.id.iv_job_pic);
            viewHolder.tv_job_tag = (TextView) v.findViewById(R.id.tv_job_tag);
            v.setTag(viewHolder);
        }
        else {
            v = convertView;
            viewHolder = (ViewHolder) v.getTag();
        }
        viewHolder.tv_job_position.setText(item.getPosition());
        viewHolder.tv_job_salary.setText(
                item.getWagemin() + "k-" + item.getWagemax() + "k");
        viewHolder.tv_job_city.setText(item.getCity());
        viewHolder.tv_job_company.setText(item.getCompany());
        ImageLoader.getInstance()
                   .displayImage(item.getLogo(), viewHolder.iv_job_pic);
        if (item.getFinancle().equals("0")) {
            financle = "未融资";
        }
        else {
            financle = item.getFinancle();
        }
        if (item.getWorkexp().equals("0")) {
            workexp = "工作经验不限";
        }
        else {
            workexp = item.getWorkexp();
        }
        if (item.getEdu().equals("0")) {
            edu = "学历不限";
        }
        else {
            edu = item.getEdu();
        }
        viewHolder.tv_job_work_time.setText(workexp);
        viewHolder.tv_job_edu.setText(edu);
        viewHolder.tv_job_tag.setText(
                item.getIndustry() + " | " + financle + " | " +
                        "" + item.getIndustry());
        return v;
    }


    static class ViewHolder {
        TextView tv_job_position;
        TextView tv_job_salary;
        TextView tv_job_city;
        TextView tv_job_work_time;
        TextView tv_job_edu;
        ImageView iv_job_pic;
        TextView tv_job_company, tv_job_tag;
    }
}

package app.nahehuo.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.bean.JobContentDict;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/1/6.
 */
public class CompanyContentAdapter extends BaseAdapter {
    private Context mContext;
    private List<JobContentDict> mJobListDicts2 = new ArrayList<>();


    public CompanyContentAdapter(Context context, List<JobContentDict> jobListDicts2) {
        mContext = context;
        mJobListDicts2 = jobListDicts2;
    }


    @Override public int getCount() {
        return mJobListDicts2.size();
    }


    @Override public Object getItem(int position) {
        return mJobListDicts2.get(position);
    }


    @Override public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        View v;
        if (convertView == null) {
            v = LayoutInflater.from(mContext)
                              .inflate(R.layout.item_company_content, null);
            viewHolder = new ViewHolder();
            viewHolder.iv_com_content = (ImageView) v.findViewById(
                    R.id.iv_com_job);
            viewHolder.tv_com_job = (TextView) v.findViewById(
                    R.id.tv_com_content);
            viewHolder.mTagFlowLayout = (TagFlowLayout) v.findViewById(
                    R.id.tfl_job_content);
            v.setTag(viewHolder);
        }
        else {
            v = convertView;
            viewHolder = (ViewHolder) v.getTag();
        }
        JobContentDict jobListDict = mJobListDicts2.get(position);
        viewHolder.tv_com_job.setText(jobListDict.getPosition());
        ImageLoader.getInstance()
                   .displayImage(jobListDict.getAvatar(),
                           viewHolder.iv_com_content);
        TagAdapter<String> adapter = new TagAdapter<String>(
                jobListDict.getTags()) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                LayoutInflater inflater
                        = (LayoutInflater) mContext.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                View v = inflater.inflate(R.layout.item_tag, parent, false);
                TextView tv_tag = (TextView) v.findViewById(R.id.tv_tag);
                tv_tag.setText(s);
                return v;
            }
        };
        viewHolder.mTagFlowLayout.setAdapter(adapter);
        return v;
    }


    class ViewHolder {
        TextView tv_com_job;
        ImageView iv_com_content;
        TagFlowLayout mTagFlowLayout;
    }
}

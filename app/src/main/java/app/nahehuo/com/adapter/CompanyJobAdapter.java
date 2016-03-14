package app.nahehuo.com.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.bean.CompanyJobDict;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyb on 2016/1/11.
 */
public class CompanyJobAdapter
        extends RecyclerView.Adapter<CompanyJobAdapter.ViewHolder> {
    private List<CompanyJobDict> mCompanyJobDicts = new ArrayList<>();
    private Context mContext;


    public CompanyJobAdapter(List<CompanyJobDict> companyJobDicts, Context context) {
        mCompanyJobDicts = companyJobDicts;
        mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext)
                               .inflate(R.layout.item_comp_job_list, parent,
                                       false);
        return new ViewHolder(v);
    }


    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        CompanyJobDict item = mCompanyJobDicts.get(position);
        holder.tv_job_name.setText(item.getJob_name());
        holder.tv_job_time.setText(item.getJob_time());
        holder.tv_job_wage.setText(item.getJob_wage());
        holder.tv_job_need.setText(item.getJob_need());
    }


    @Override public int getItemCount() {
        return mCompanyJobDicts.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_job_name;
        TextView tv_job_time;
        TextView tv_job_wage;
        TextView tv_job_need;


        public ViewHolder(View itemView) {
            super(itemView);
            tv_job_need = (TextView) itemView.findViewById(R.id.tv_job_need);
            tv_job_wage = (TextView) itemView.findViewById(R.id.tv_job_wage);
            tv_job_time = (TextView) itemView.findViewById(R.id.tv_job_time);
            tv_job_name = (TextView) itemView.findViewById(R.id.tv_job_name);
        }
    }
}

package app.nahehuo.com.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.bean.RecomJob;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2015/12/29.
 */
public class CompanyTitleAdapter
        extends RecyclerView.Adapter<CompanyTitleAdapter.ViewHolder> {

    private Context mContext;
    private List<RecomJob> mJobListDicts = new ArrayList<>();


    public CompanyTitleAdapter(Context context, List<RecomJob> jobListDicts) {
        mContext = context;
        mJobListDicts = jobListDicts;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                                  .inflate(R.layout.item_company_title, null);
        return new ViewHolder(view);
    }


    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        RecomJob jobListDict = mJobListDicts.get(position);
        holder.tv_company_title.setText(jobListDict.getCompany());
        ImageLoader.getInstance()
                   .displayImage(jobListDict.getLogo(),
                           holder.iv_company_title);
    }


    @Override public int getItemCount() {
        return mJobListDicts.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_company_title;
        private TextView tv_company_title;


        public ViewHolder(View itemView) {
            super(itemView);
            tv_company_title = (TextView) itemView.findViewById(
                    R.id.tv_company_title);
            iv_company_title = (ImageView) itemView.findViewById(
                    R.id.iv_company_title);
        }
    }
}

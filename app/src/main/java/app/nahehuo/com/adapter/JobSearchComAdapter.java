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
import app.nahehuo.com.bean.CompanySearch;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.List;

/**
 * Created by wyb on 2016/3/16.
 */
public class JobSearchComAdapter extends BaseAdapter {

    private List<CompanySearch> mCompanySearches;
    private Context mContext;


    public JobSearchComAdapter(List<CompanySearch> companySearches, Context context) {
        mCompanySearches = companySearches;
        mContext = context;
    }


    @Override public int getCount() {
        return mCompanySearches.size();
    }


    @Override public Object getItem(int position) {
        return mCompanySearches.get(position);
    }


    @Override public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CompanySearch item = mCompanySearches.get(position);
        ViewHolder viewHolder;
        View v;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            v = LayoutInflater.from(mContext)
                              .inflate(R.layout.item_search_company, null);
            viewHolder.iv_logo = (ImageView) v.findViewById(R.id.iv_logo);
            viewHolder.tv_city = (TextView) v.findViewById(R.id.tv_city);
            viewHolder.tv_name = (TextView) v.findViewById(R.id.tv_name);
            viewHolder.tv_finance = (TextView) v.findViewById(R.id.tv_finance);
            viewHolder.tv_industry = (TextView) v.findViewById(
                    R.id.tv_industry);
            viewHolder.tv_size = (TextView) v.findViewById(R.id.tv_size);
            v.setTag(viewHolder);
        }
        else {
            v = convertView;
            viewHolder = (ViewHolder) v.getTag();
        }
        viewHolder.tv_name.setText(item.getName());
        viewHolder.tv_city.setText(item.getCity());
        viewHolder.tv_finance.setText(item.getFinance());
        viewHolder.tv_industry.setText(item.getIndustry());
        viewHolder.tv_size.setText(item.getSize());
        ImageLoader.getInstance()
                   .displayImage(item.getLogo(), viewHolder.iv_logo,
                           MyApplication.getDisplayDefaultOption());
        return v;
    }


    static class ViewHolder {
        ImageView iv_logo;
        TextView tv_name, tv_city, tv_finance, tv_industry, tv_size;
    }
}

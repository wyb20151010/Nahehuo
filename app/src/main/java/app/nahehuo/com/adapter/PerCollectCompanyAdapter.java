package app.nahehuo.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.bean.PerCollectCompany;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import java.util.List;

/**
 * Created by wyb on 2016/3/3.
 */
public class PerCollectCompanyAdapter extends BaseAdapter{

    private List<PerCollectCompany> mPerCollectCompanies;

    private Context mContext;


    public PerCollectCompanyAdapter(List<PerCollectCompany> perCollectCompanies, Context context) {
        mPerCollectCompanies = perCollectCompanies;
        mContext = context;
    }


    @Override public int getCount() {
        return mPerCollectCompanies.size();
    }


    @Override public Object getItem(int position) {
        return mPerCollectCompanies.get(position);
    }


    @Override public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final PerCollectCompany each=mPerCollectCompanies.get(position);

        ViewHolder viewHolder;
        View v;
        if(convertView==null){
            viewHolder=new ViewHolder();

            v= LayoutInflater.from(mContext).inflate(R.layout
                    .item_per_collect_company,null);
            viewHolder.tv_company= (TextView) v.findViewById(R.id.tv_company);
            viewHolder.tfl_info= (TagFlowLayout) v.findViewById(R.id.tfl_info);
            v.setTag(viewHolder);
        }else {
            v=convertView;
            viewHolder= (ViewHolder) v.getTag();
        }

        viewHolder.tv_company.setText(each.getCompany());
        viewHolder.tfl_info.setAdapter(new TagAdapter(each.getTags()) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {

                LayoutInflater inflater
                        = (LayoutInflater) mContext.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                TextView v = (TextView) inflater.inflate(
                        R.layout.item_tag_bule1, parent, false);
                v.setText(each.getTags().get(position));
                return v;
            }
        });
        return v;
    }


    static class ViewHolder{
        TextView tv_company;
        TagFlowLayout tfl_info;
    }
}

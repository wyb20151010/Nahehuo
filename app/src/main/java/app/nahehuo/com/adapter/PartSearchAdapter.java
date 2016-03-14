package app.nahehuo.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.bean.PartSearch;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/2/17.
 */
public class PartSearchAdapter extends BaseAdapter {

    private List<PartSearch> mPartSearches = new ArrayList<>();
    private Context mContext;


    public PartSearchAdapter(List<PartSearch> partSearches, Context context) {
        mPartSearches = partSearches;
        mContext = context;
    }


    @Override public int getCount() {
        return mPartSearches.size();
    }


    @Override public Object getItem(int position) {
        return mPartSearches.get(position);
    }


    @Override public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PartSearch partSearch=mPartSearches.get(position);
        View v;
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            v= LayoutInflater.from(mContext).inflate(R.layout
                    .item_part_search,null);
            viewHolder.tv_name= (TextView) v.findViewById(R.id.tv_name);
            viewHolder.tv_position= (TextView) v.findViewById(R.id.tv_position);
            v.setTag(viewHolder);
        }else {
            v=convertView;
            viewHolder= (ViewHolder) v.getTag();
        }
        viewHolder.tv_name.setText(partSearch.getName());
        viewHolder.tv_position.setText(partSearch.getPosition());
        return v;
    }


    static class ViewHolder {
        TextView tv_position;
        TextView tv_name;
    }
}

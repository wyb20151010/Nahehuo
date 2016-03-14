package app.nahehuo.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.bean.ProjectPersonDict;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/1/19.
 */
public class AttentationAdapter extends BaseAdapter {

    private List<ProjectPersonDict> mItems = new ArrayList<>();
    private Context mContext;


    public AttentationAdapter(List<ProjectPersonDict> items, Context context) {
        mItems = items;
        mContext = context;
    }


    @Override public int getCount() {
        return mItems.size();
    }


    @Override public Object getItem(int position) {
        return mItems.get(position);
    }


    @Override public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ProjectPersonDict personDict = mItems.get(position);
        View v;
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            v = LayoutInflater.from(mContext)
                              .inflate(R.layout.item_person_atten, null);
            viewHolder.iv_avater = (ImageView) v.findViewById(R.id.iv_avater);
            viewHolder.tv_per_name = (TextView) v.findViewById(
                    R.id.tv_per_name);
            viewHolder.tv_per_pos = (TextView) v.findViewById(R.id.tv_per_pos);
            viewHolder.ll_line = (LinearLayout) v.findViewById(R.id.ll_line);
            v.setTag(viewHolder);
        }
        else {
            v = convertView;
            viewHolder = (ViewHolder) v.getTag();
        }
        ImageLoader.getInstance()
                   .displayImage(personDict.getAvater(), viewHolder.iv_avater);
        viewHolder.tv_per_pos.setText(personDict.getPosition());
        viewHolder.tv_per_name.setText(personDict.getPerson());
        if (position < mItems.size() - 1) {
            viewHolder.ll_line.setVisibility(View.VISIBLE);
        }
        return v;
    }


    class ViewHolder {
        ImageView iv_avater;
        TextView tv_per_name;
        TextView tv_per_pos;
        LinearLayout ll_line;
    }
}

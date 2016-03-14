package app.nahehuo.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.bean.PersonPartner;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/2/16.
 */
public class PartnerPersonAdapter extends BaseAdapter {

    private List<PersonPartner> mPartners = new ArrayList<>();

    private Context mContext;


    public PartnerPersonAdapter(List<PersonPartner> partners, Context context) {
        mPartners = partners;
        this.mContext = context;
    }


    @Override public int getCount() {
        return mPartners.size();
    }


    @Override public PersonPartner getItem(int position) {
        return mPartners.get(position);
    }


    @Override public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        View v;
        PersonPartner item = mPartners.get(position);
        if (convertView == null) {
            viewHolder = new ViewHolder();
            v = LayoutInflater.from(mContext)
                              .inflate(R.layout.item_person_partner, null);
            viewHolder.tv_name = (TextView) v.findViewById(R.id.tv_name);
            viewHolder.tv_position = (TextView) v.findViewById(
                    R.id.tv_position);
            viewHolder.tv_header = (TextView) v.findViewById(R.id.tv_header);
            viewHolder.ll_title = (LinearLayout) v.findViewById(R.id.ll_title);
            v.setTag(viewHolder);
        }
        else {
            v = convertView;
            viewHolder = (ViewHolder) v.getTag();
        }
/*
        if (position == 0) {
            viewHolder.tv_position.setTextColor(
                    mContext.getResources().getColor(R.color.colorPrimary));
        }*/
        int section = getSectionForPosition(position);
        if (position == getPositionForSection(section)) {
            viewHolder.ll_title.setVisibility(View.VISIBLE);
            viewHolder.tv_header.setText(item.getPingyintou());
        }
        else {
            viewHolder.ll_title.setVisibility(View.GONE);
        }
        viewHolder.tv_name.setText(item.getName());
        viewHolder.tv_position.setText(item.getPosition());
        return v;
    }


    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            char sortStr = mPartners.get(i).getPingyintou().charAt(0);
            if (sortStr == section) {
                return i;
            }
        }

        return -1;
    }


    public int getSectionForPosition(int position) {
        return mPartners.get(position).getPingyintou().charAt(0);
    }


    static class ViewHolder {
        TextView tv_name;
        TextView tv_position;
        TextView tv_header;
        LinearLayout ll_title;
    }
}

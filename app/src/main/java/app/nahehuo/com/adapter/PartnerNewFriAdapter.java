package app.nahehuo.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.bean.PartnerNewFriend;
import app.nahehuo.com.view.SwipeDeleteLayout;
import java.util.List;

/**
 * Created by WYB on 2016/2/19.
 */
public class PartnerNewFriAdapter extends BaseAdapter {

    private List<PartnerNewFriend> mNewFriends;

    private Context mContext;

    DeleteItemCallBack mDeleteItemCallBack;


    public PartnerNewFriAdapter(List<PartnerNewFriend> newFriends, Context context) {
        mNewFriends = newFriends;
        mContext = context;
    }


    public void setDeleteItemCallBack(DeleteItemCallBack deleteItemCallBack) {
        mDeleteItemCallBack = deleteItemCallBack;
    }


    @Override public int getCount() {
        return mNewFriends.size();
    }


    @Override public Object getItem(int position) {
        return mNewFriends.get(position);
    }


    @Override public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        PartnerNewFriend item = mNewFriends.get(position);
        View v;
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            v = LayoutInflater.from(mContext)
                              .inflate(R.layout.item_partner_new_fri, null);
            viewHolder.tv_name = (TextView) v.findViewById(R.id.tv_name);
            viewHolder.tv_position = (TextView) v.findViewById(
                    R.id.tv_position);
            viewHolder.tv_onesay = (TextView) v.findViewById(R.id.tv_onesay);
            viewHolder.sdl_show = (SwipeDeleteLayout) v.findViewById(
                    R.id.sdl_show);
            viewHolder.rl_delete = (RelativeLayout) v.findViewById(
                    R.id.rl_delete);
            v.setTag(viewHolder);
        }
        else {
            v = convertView;
            viewHolder = (ViewHolder) v.getTag();
        }
        viewHolder.tv_name.setText(item.getName());
        viewHolder.tv_position.setText(item.getPosition());
        viewHolder.tv_onesay.setText(item.getOnesay());
        viewHolder.rl_delete.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mDeleteItemCallBack.onDeletePositon(v, position);
            }
        });
        return v;
    }


    static class ViewHolder {
        TextView tv_name;
        TextView tv_position;
        TextView tv_onesay;
        SwipeDeleteLayout sdl_show;
        RelativeLayout rl_delete;
    }

    public interface DeleteItemCallBack {
        void onDeletePositon(View v, int position);
    }
}

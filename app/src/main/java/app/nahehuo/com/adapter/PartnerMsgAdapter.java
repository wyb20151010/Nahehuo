package app.nahehuo.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.bean.PartnerMsg;
import java.util.List;

/**
 * Created by WYB on 2016/2/23.
 */
public class PartnerMsgAdapter extends BaseAdapter{

    private List<PartnerMsg> mMsgs;
    private Context mContext;


    public PartnerMsgAdapter(List<PartnerMsg> msgs, Context context) {
        mMsgs = msgs;
        mContext = context;
    }


    @Override public int getCount() {
        return mMsgs.size();
    }


    @Override public Object getItem(int position) {
        return mMsgs.get(position);
    }


    @Override public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PartnerMsg msg= mMsgs.get(position);

        ViewHolder viewHolder;
        View view;
        if(convertView==null){
            viewHolder=new ViewHolder();
            view= LayoutInflater.from(mContext).inflate(R.layout
                            .item_partner_msg,
                    null);
            viewHolder.ll_left= (LinearLayout) view.findViewById(R.id.ll_left);
            viewHolder.rl_right= (RelativeLayout) view.findViewById(R.id.rl_right);
            viewHolder.tv_right= (TextView) view.findViewById(R.id.tv_right);
            viewHolder.tv_left= (TextView) view.findViewById(R.id.tv_left);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();

        }
        if(msg.isLeft()){
            viewHolder.rl_right.setVisibility(View.GONE);
            viewHolder.tv_left.setText(msg.getMsg());
        }else {
            viewHolder.ll_left.setVisibility(View.GONE);
            viewHolder.tv_right.setText(msg.getMsg());
        }

        return view;
    }

    static class ViewHolder{
        LinearLayout ll_left;
        RelativeLayout rl_right;
        TextView tv_right,tv_left;

    }
}

package app.nahehuo.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.bean.PerProjectTalk;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import java.util.ArrayList;

/**
 * Created by wyb on 2016/3/4.
 */
public class PerProjectTalkAdapter extends BaseAdapter {

    private ArrayList<PerProjectTalk> mPerProjectTalks;

    private Context mContext;


    public PerProjectTalkAdapter(ArrayList<PerProjectTalk> perProjectTalks,
                                 Context context) {
        mPerProjectTalks = perProjectTalks;
        mContext = context;
    }


    @Override public int getCount() {
        return mPerProjectTalks.size();
    }


    @Override public Object getItem(int position) {
        return mPerProjectTalks.get(position);
    }


    @Override public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final PerProjectTalk talk = mPerProjectTalks.get(position);
        ViewHolder viewHolder;
        View v;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            v = LayoutInflater.from(mContext)
                              .inflate(R.layout.item_per_pro_talk, null);
            viewHolder.tv_email = (TextView) v.findViewById(R.id.tv_email);
            viewHolder.tv_phone = (TextView) v.findViewById(R.id.tv_phone);
            viewHolder.tv_position = (TextView) v.findViewById(
                    R.id.tv_position);
            viewHolder.tv_name = (TextView) v.findViewById(R.id.tv_name);
            viewHolder.tv_sub_title = (TextView) v.findViewById(
                    R.id.tv_sub_title);
            viewHolder.tv_title = (TextView) v.findViewById(R.id.tv_title);
            viewHolder.tfl_show = (TagFlowLayout) v.findViewById(R.id.tfl_show);
            v.setTag(viewHolder);
        }
        else {
            v = convertView;
            viewHolder = (ViewHolder) v.getTag();
        }
        viewHolder.tv_title.setText(talk.getTitle());
        viewHolder.tv_sub_title.setText(talk.getSubTitle());
        viewHolder.tv_name.setText(talk.getName());
        viewHolder.tv_position.setText(talk.getPosition());
        viewHolder.tv_phone.setText(talk.getPhone());
        viewHolder.tv_email.setText(talk.getEmail());
        viewHolder.tfl_show.setAdapter(new TagAdapter(talk.getTags()) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                TextView v = (TextView) LayoutInflater.from(mContext)
                                                      .inflate(
                                                              R.layout.item_tag_bule,
                                                              parent, false);
                if ("1".equals(talk.getTags().get(position).getType())) {
                    v.setBackground(mContext.getResources()
                                            .getDrawable(
                                                    R.drawable.bg_shape_round_red));
                }
                else if ("2".equals(talk.getTags().get(position).getType())) {
                    v.setBackground(mContext.getResources()
                                            .getDrawable(
                                                    R.drawable.bg_shape_round_green));
                }
                v.setText(talk.getTags().get(position).getName());
                return v;
            }
        });
        return v;
    }


    static class ViewHolder {
        TextView tv_title, tv_sub_title, tv_name, tv_position, tv_phone,
                tv_email;
        TagFlowLayout tfl_show;
    }
}

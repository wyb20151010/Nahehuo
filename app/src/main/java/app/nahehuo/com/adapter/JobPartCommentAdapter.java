package app.nahehuo.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.bean.JobCommentDict;
import app.nahehuo.com.util.TextUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/1/14.
 */
public class JobPartCommentAdapter extends BaseAdapter {

    private List<JobCommentDict> mCommentDicts = new ArrayList<>();
    private Context mContext;


    public JobPartCommentAdapter(List<JobCommentDict> commentDicts, Context context) {
        mCommentDicts = commentDicts;
        mContext = context;
    }


    @Override public int getCount() {
        return mCommentDicts.size();
    }


    @Override public Object getItem(int position) {
        return mCommentDicts.get(position);
    }


    @Override public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        JobCommentDict item = mCommentDicts.get(position);
        ViewHolder viewHolder;
        View v;

        if (convertView == null) {

            v = LayoutInflater.from(mContext)
                              .inflate(R.layout.item_part_job_comment, parent,
                                      false);
            viewHolder = new ViewHolder();
            viewHolder.tv_answer = (TextView) v.findViewById(R.id.tv_answer);
            viewHolder.tv_comment_content = (TextView) v.findViewById(
                    R.id.tv_comment_content);
            viewHolder.tv_say_from = (TextView) v.findViewById(
                    R.id.tv_say_from);
            viewHolder.tv_say_to = (TextView) v.findViewById(R.id.tv_say_to);
            v.setTag(viewHolder);
        }
        else {
            v = convertView;
            viewHolder = (ViewHolder) v.getTag();
        }
        if (TextUtil.isEmpty(item.getTo_say())) {
            viewHolder.tv_say_to.setVisibility(View.GONE);
            viewHolder.tv_answer.setVisibility(View.GONE);
        }
        else {
            viewHolder.tv_say_to.setText(item.getTo_say());
        }
        viewHolder.tv_say_from.setText(item.getFrom_say());
        viewHolder.tv_comment_content.setText(item.getComment());
        return v;
    }


    static class ViewHolder {
        TextView tv_say_from;
        TextView tv_say_to;
        TextView tv_answer;
        TextView tv_comment_content;
    }
}

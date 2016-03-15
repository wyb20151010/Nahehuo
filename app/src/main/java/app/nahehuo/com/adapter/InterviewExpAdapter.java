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
import app.nahehuo.com.application.MyApplication;
import app.nahehuo.com.bean.CompanyCommentDict;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/1/8.
 */
public class InterviewExpAdapter extends BaseAdapter {

    private List<CompanyCommentDict> mCommentDicts = new ArrayList<>();
    private Context mContext;


    public InterviewExpAdapter(List<CompanyCommentDict> commentDicts, Context context) {
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
        CompanyCommentDict item = mCommentDicts.get(position);
        ViewHolder viewHolder;
        View v;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater
                    = (LayoutInflater) mContext.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_interview_exp_green, null);
            viewHolder.ll_tag = (LinearLayout) v.findViewById(R.id.ll_tag);
            viewHolder.tv_tag = (TextView) v.findViewById(R.id.tv_tag);
            viewHolder.tv_username = (TextView) v.findViewById(
                    R.id.tv_username);
            viewHolder.tv_score = (TextView) v.findViewById(R.id.tv_score);
            viewHolder.tv_comment_title = (TextView) v.findViewById(
                    R.id.tv_comment_title);
            viewHolder.tv_comment_content = (TextView) v.findViewById(
                    R.id.tv_comment_content);
            viewHolder.tv_comment_time = (TextView) v.findViewById(
                    R.id.tv_comment_time);
            viewHolder.iv_avater = (ImageView) v.findViewById(R.id.iv_avater);
            v.setTag(viewHolder);
        }
        else {
            v = convertView;
            viewHolder = (ViewHolder) v.getTag();
        }
        if (item.getState() == 0) {
            viewHolder.ll_tag.setBackgroundDrawable(mContext.getResources()
                                                            .getDrawable(
                                                                    R.drawable.checked_bg_green));
            viewHolder.tv_tag.setTextColor(
                    mContext.getResources().getColor(R.color.white));
            viewHolder.tv_tag.setText("接到offer并入职( ^_^ )");
        }
        else if (item.getState() == 1) {
            viewHolder.ll_tag.setBackgroundDrawable(mContext.getResources()
                                                            .getDrawable(
                                                                    R.drawable.checked_bg_red));
            viewHolder.tv_tag.setTextColor(
                    mContext.getResources().getColor(R.color.white));
            viewHolder.tv_tag.setText("未接到offer ⊙︿⊙");
        }
        else if (item.getState() == 2) {
            viewHolder.ll_tag.setBackgroundDrawable(mContext.getResources()
                                                            .getDrawable(
                                                                    R.drawable.checked_bg_gray));
            viewHolder.tv_tag.setTextColor(
                    mContext.getResources().getColor(R.color.white));
            viewHolder.tv_tag.setText("接到offer但拒绝 (＞﹏＜)");
        }

        viewHolder.tv_score.setText(item.getScore());
        viewHolder.tv_comment_title.setText("["+item.getComment_title()+"]");
        viewHolder.tv_comment_content.setText(item.getComment_content());
        viewHolder.tv_comment_time.setText(item.getComment_time());
        ImageLoader.getInstance()
                   .displayImage(item.getAvater(), viewHolder.iv_avater,
                           MyApplication.getDisplayDefaultOption());
        if(item.getAnony()==1){
            viewHolder.tv_username.setText(item.getUsername());

        }else if(item.getAnony()==2){
            viewHolder.tv_username.setText("匿名");
            //TODO 图标换成匿名图片
        }
        return v;
    }


    static class ViewHolder {
        TextView tv_username;
        TextView tv_score;
        TextView tv_comment_title;
        TextView tv_comment_content;
        TextView tv_comment_time;
        TextView tv_tag;
        LinearLayout ll_tag;
        ImageView iv_avater;
    }
}

package app.nahehuo.com.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.bean.CompanyCommentDict;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/1/8.
 */
public class CompCommentAdapter
        extends RecyclerView.Adapter<CompCommentAdapter.ViewHolder> {

    private List<CompanyCommentDict> mCommentDicts = new ArrayList<>();
    private Context mContext;


    public CompCommentAdapter(List<CompanyCommentDict> commentDicts, Context context) {
        mCommentDicts = commentDicts;
        mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext)
                               .inflate(R.layout.item_comp_comment_list, parent,
                                       false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        CompanyCommentDict item = mCommentDicts.get(position);
        viewHolder.tv_comment_username.setText(item.getUsername());
        viewHolder.tv_comment_score.setText(item.getScore());
        viewHolder.tv_comment_content.setText(item.getComment_content());
        viewHolder.tv_comment_time.setText(item.getComment_time());
        ImageLoader.getInstance()
                   .displayImage(item.getAvater(),
                           viewHolder.iv_comment_avater);
        if(item.getAnony()==2){
            viewHolder.tv_comment_username.setText("匿名");
        }else if(item.getAnony()==1){
            viewHolder.tv_comment_username.setText(item.getUsername());
        }
    }


    @Override public int getItemCount() {
        return mCommentDicts.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_comment_username;
        TextView tv_comment_score;
        TextView tv_comment_content;
        TextView tv_comment_time;
        ImageView iv_comment_avater;


        public ViewHolder(View v) {
            super(v);
            tv_comment_username = (TextView) v.findViewById(
                    R.id.tv_comment_username);
            tv_comment_score = (TextView) v.findViewById(R.id.tv_comment_score);
            tv_comment_content = (TextView) v.findViewById(
                    R.id.tv_comment_content);
            tv_comment_time = (TextView) v.findViewById(R.id.tv_comment_time);
            iv_comment_avater = (ImageView) v.findViewById(
                    R.id.iv_comment_avater);
        }
    }
}

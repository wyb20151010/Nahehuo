package app.nahehuo.com.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import app.nahehuo.com.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/1/28.
 */
public class EventDetailPicAdapter
        extends RecyclerView.Adapter<EventDetailPicAdapter.ViewHolder> {

    private Context mContext;
    private List<String> pics = new ArrayList<>();
    private OnclickCallBack mCallBack;


    public EventDetailPicAdapter(Context context, List<String> pics) {
        mContext = context;
        this.pics = pics;
    }


    public void setCallBack(OnclickCallBack callBack) {
        mCallBack = callBack;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext)
                               .inflate(R.layout.item_event_pic, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String url = pics.get(position);
        ImageLoader.getInstance().displayImage(url, holder.iv_pic);
        holder.iv_pic.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mCallBack.setItemOnclick(v, position);
            }
        });
    }


    @Override public int getItemCount() {
        return pics.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_pic;


        public ViewHolder(View v) {
            super(v);
            iv_pic = (ImageView) v.findViewById(R.id.iv_pic);
        }
    }

    public interface OnclickCallBack {
        void setItemOnclick(View v, int position);
    }
}

package app.nahehuo.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.bean.CompHistory;
import app.nahehuo.com.util.DpPxUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/1/21.
 */
public class ProjectProgressAdapter extends BaseAdapter {

    private List<CompHistory> mDatas = new ArrayList<>();
    private Context mContext;
    private float mviewHeight;
    private LinearLayout.LayoutParams layoutParams;


    public ProjectProgressAdapter(List<CompHistory> datas, Context context) {
        mDatas = datas;
        mContext = context;
    }


    @Override public int getCount() {
        return mDatas.size();
    }


    @Override public Object getItem(int position) {
        return mDatas.get(position);
    }


    @Override public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        CompHistory item = mDatas.get(position);
        final ViewHolder viewHolder;
        View v;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            v = LayoutInflater.from(mContext)
                              .inflate(R.layout.item_pro_progress, null);
            viewHolder.tv_title = (TextView) v.findViewById(R.id.tv_title);
            viewHolder.view = v.findViewById(R.id.view);
            viewHolder.tv_content = (TextView) v.findViewById(R.id.tv_content);
            viewHolder.tv_title.getViewTreeObserver()
                               .addOnGlobalLayoutListener(
                                       new ViewTreeObserver.OnGlobalLayoutListener() {
                                           @Override
                                           public void onGlobalLayout() {
                                               mviewHeight = viewHolder.tv_title
                                                       .getHeight();

                                               layoutParams
                                                       = new LinearLayout.LayoutParams(
                                                       LinearLayout.LayoutParams.MATCH_PARENT,
                                                       LinearLayout.LayoutParams.WRAP_CONTENT);

                                               layoutParams.height
                                                       = DpPxUtil.dip2px(
                                                       mContext, 40 +
                                                               DpPxUtil.px2dip(
                                                                       mContext,
                                                                       mviewHeight) -
                                                               13);
                                               layoutParams.width
                                                       = DpPxUtil.dip2px(
                                                       mContext, 1);
                                               viewHolder.view.setLayoutParams(layoutParams);
                                               viewHolder.tv_title.getViewTreeObserver()
                                                                  .removeGlobalOnLayoutListener(
                                                                          this);
                                           }
                                       });
            v.setTag(viewHolder);
        }
        else {
            v = convertView;
            viewHolder = (ViewHolder) v.getTag();
        }
        viewHolder.tv_content.setText(item.getContent());
        viewHolder.tv_title.setText(item.getTitle());

        return v;
    }


    class ViewHolder {
        TextView tv_title;
        TextView tv_content;
        View view;
    }
}

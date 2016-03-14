package app.nahehuo.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import app.nahehuo.com.R;
import app.nahehuo.com.application.MyApplication;
import app.nahehuo.com.bean.EventGalleryDict;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyb on 2016/1/30.
 */
public class EventGalleryAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private List<EventGalleryDict> photo_list
            = new ArrayList<EventGalleryDict>();
    DisplayImageOptions options = MyApplication.getDisplayDefaultOption();


    public EventGalleryAdapter(Context mContext, List<EventGalleryDict> photo_list) {
        super();
        this.mContext = mContext;
        this.photo_list = photo_list;
        inflater = LayoutInflater.from(mContext);
    }


    @Override public int getCount() {
        return photo_list.size();
    }


    @Override public Object getItem(int position) {
        return photo_list.get(position);
    }


    @Override public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final EventGalleryDict eventPhoto = photo_list.get(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_event_gallery, parent,
                    false);
            viewHolder.img_event_photo = (ImageView) convertView.findViewById(
                    R.id.img_event_photo);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ImageLoader.getInstance()
                   .displayImage(eventPhoto.thumb, viewHolder.img_event_photo,
                           options);

        return convertView;
    }


    public class ViewHolder {
        ImageView img_event_photo;
    }
}


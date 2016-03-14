package app.nahehuo.com.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by WYB on 2016/1/14.
 */
public class MyListView extends ListView {
    public MyListView(Context context) {
        super(context);
    }


    public MyListView(Context context, AttributeSet as) {
        super(context, as);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}

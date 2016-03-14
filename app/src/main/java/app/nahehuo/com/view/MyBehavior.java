package app.nahehuo.com.view;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;

/**
 * Created by WYB on 2016/1/21.
 */
public class MyBehavior extends CoordinatorLayout.Behavior{

    static final Class<?>[] CONSTRUCTOR_PARMAS=new Class<?>[]{
            Context.class,AttributeSet.class
    };

    public MyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}

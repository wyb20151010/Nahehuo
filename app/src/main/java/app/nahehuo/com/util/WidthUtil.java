package app.nahehuo.com.util;

import android.content.Context;
import android.view.WindowManager;
import app.nahehuo.com.application.MyApplication;

/**
 * Created by Administrator on 2016/1/31.
 */
public class WidthUtil {

    public static int getPhoneWidth() {

        WindowManager wm = (WindowManager) MyApplication.getContext()
                                                        .getSystemService(
                                                                Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getWidth();
    }


    public static int getPhoneHeight() {

        WindowManager wm = (WindowManager) MyApplication.getContext()
                                                        .getSystemService(
                                                                Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getHeight();
    }
}

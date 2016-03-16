package app.nahehuo.com.util;

import android.app.Activity;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by Administrator on 2016/3/16.
 */
public class HideSoftInputUtil {

    /**
     * 隐藏软键盘
     *
     * @param activity
     */
    public static void hideSoftKeyboard(Activity activity) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            Log.d("Keyboard", "Not visible");
        }
    }

    /**
     * 显示软键盘
     *
     * @param activity
     */
    public static void showSoftKeyboard(Activity activity, EditText editText) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.showSoftInput(editText, 0);
        } catch (Exception e) {
            Log.d("Keyboard", "visible");
        }
    }
}

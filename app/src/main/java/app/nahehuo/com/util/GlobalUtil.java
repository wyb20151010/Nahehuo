package app.nahehuo.com.util;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

public class GlobalUtil {

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

	/*
	 * 判断网络连接是否已开 2012-08-20true 已打开 false 未打开
	 */
	public static boolean isConn(Context context) {
		try {
			ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivity != null) {
				// 获取网络连接管理的对象
				NetworkInfo info = connectivity.getActiveNetworkInfo();
				if (info != null && info.isConnected()) {
					// 判断当前网络是否已经连接
					if (info.getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			Log.v("error", e.toString());
		}
		return false;
	}

	public static void setNetworkMethod(final Context context) {
		// 提示对话框
		Builder builder = new Builder(context);
		builder.setTitle("网络设置提示").setMessage("网络连接不可用,是否进行设置?").setPositiveButton("设置", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Intent intent = null;
				// 判断手机系统的版本 即API大于10 就是3.0或以上版本
				if (android.os.Build.VERSION.SDK_INT > 10) {
					intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
				} else {
					intent = new Intent();
					ComponentName component = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
					intent.setComponent(component);
					intent.setAction("android.intent.action.VIEW");
				}
				context.startActivity(intent);
			}
		}).setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		}).show();
	}
}

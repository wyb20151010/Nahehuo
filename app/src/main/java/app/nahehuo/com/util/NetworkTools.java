package app.nahehuo.com.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkTools {

	/**
	 * 判断网络状态 context 上下文
	 * 
	 * @return
	 */
	public static boolean isNetworkAvailable(Context context) {
		// 获得网络状态管理器
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivityManager == null) {
			return false;
		} else {
			NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
			if (info != null) {
				for (NetworkInfo network : info) {
					if (network.getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean isWifiConnected(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo wifiNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (wifiNetworkInfo.isConnected()) {
			return true;
		}

		return false;
	}
}

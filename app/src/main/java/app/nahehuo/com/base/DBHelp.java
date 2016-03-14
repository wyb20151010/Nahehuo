package app.nahehuo.com.base;

import android.content.Context;
import com.lidroid.xutils.DbUtils;

/**
 * 数据库操作的帮助类
 * 
 * @author LT
 * 
 */
public class DBHelp {

	/**
	 * DB对象，操作数据库
	 */
	private static DbUtils db;

	/**
	 * 初始化db
	 * 
	 * @param context
	 */
	public static void initDB(Context context) {
		db = DbUtils.create(context.getApplicationContext(), "nahehuo");
		db.configAllowTransaction(true);// 开启事务（最好开启，若不开启会出现程序卡死现象）
		db.configDebug(true);// debug模式
	}

	public static DbUtils getInstance(Context context) {
		if (db == null) {
			initDB(context);
		}
		return db;
	}

}

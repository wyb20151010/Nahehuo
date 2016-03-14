package app.nahehuo.com.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 常用的文本格式验证
 * 
 * @author LT
 * 
 */
public class CheckTextFormatUtil {

	/**
	 * 验证字符串是否为null, "", "null"
	 * 
	 * @param string
	 * @return boolean
	 */
	public static boolean isNull(String s) {
		if (null == s || s.equals("") || s.equalsIgnoreCase("null")) {
			return true;
		}
		return false;
	}

	/**
	 * email格式验证
	 * 
	 * @param string
	 * @return 验证通过返回true
	 */
	public static boolean checkEmail(String email) {
		Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();

	}

	/**
	 * 手机号验证
	 * 
	 * @param string
	 * @return 验证通过返回true
	 */
	public static boolean isMobile(String str) {

		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号
		m = p.matcher(str);
		b = m.matches();
		return b;
	}

	/**
	 * 电话号码验证
	 * 
	 * @param string
	 * @return 验证通过返回true
	 */
	public static boolean isPhone(String str) {
		Pattern p1 = null, p2 = null;
		Matcher m = null;
		boolean b = false;
		p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$"); // 验证带区号的
		p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$"); // 验证没有区号的
		if (str.length() > 9) {
			m = p1.matcher(str);
			b = m.matches();
		} else {
			m = p2.matcher(str);
			b = m.matches();
		}
		return b;
	}

	/**
	 * 检查字符串是否含有HTML标签
	 * 
	 * @param value
	 * @return
	 */

	public static boolean checkHtmlTag(String value) {
		// boolean isHtml = value.matches("<(\\S*?)[^>]*>.*?</\\1>|<.*? />");

		value = value.replaceAll("\\n", "");
		// if(value.matches("<(\\S*?)[^>]*>.*?</\\1>|<.*? />")){
		//
		// }

		return value.matches("<(\\S*?)[^>]*>.*?</\\1>|<.*? />");
	}

}

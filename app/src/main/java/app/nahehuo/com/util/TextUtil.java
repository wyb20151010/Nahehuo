package app.nahehuo.com.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * Created by WYB on 2015/12/16.
 */
public class TextUtil {
    private static final String TAG = "TextUtil";


    public static String charater(int paramInt) {
        return new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
                "X", "Y", "Z", "#" }[paramInt];
    }


    public static String charaterAnother(int paramInt) {
        return new String[] { "#", "A", "B", "C", "D", "E", "F", "G", "H", "I",
                "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
                "W", "X", "Y", "Z" }[paramInt];
    }


    public static boolean containsAny(String paramString) {
        return (!paramString.contains("@yahoo.cn")) &&
                (!paramString.contains("@yahoo.com.cn"));
    }


    public static boolean emailFormat(String paramString) {
        int i = 1;
        if (!Pattern.compile(
                "^([a-zA-Z0-9_\\-\\.]+)@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$")
                    .matcher(paramString)
                    .find()) {
            i = 0;
        }
        return i == 1 ? true : false;
    }


    public static String filterHtml(String paramString) {
        if (TextUtils.isEmpty(paramString)) return paramString;
        return paramString.replaceAll("[&nbsp;|&hellip;|&mdash;|&alpha;| ]",
                "");
    }


    public static String filterString(String paramString) {
        if (TextUtils.isEmpty(paramString)) return paramString;
        return paramString.replaceAll("\\[img\\].*?\\[/img\\]", "")
                          .replaceAll("\\[.*?\\]", "");
    }


    public static float floatRoundHalfUp(float paramFloat, int paramInt) {
        return new BigDecimal(paramFloat).setScale(paramInt, 4).floatValue();
    }


    public static boolean hasEnglish(String paramString) {
        for (int i = 0; i < paramString.length(); ++i) {
            if (((paramString.charAt(i) >= 'A') &&
                    (paramString.charAt(i) <= 'Z')) ||
                    ((paramString.charAt(i) >= 'a') &&
                            (paramString.charAt(i) <= 'z'))) {
                return true;
            }
        }
        return false;
    }


    public static String integerFormatToK(long paramLong) {
        String str = "";
        if (paramLong % 1000L > 0L) {
            if (paramLong % 1000L > 500L) str = paramLong / 1000L + ".5K+";
        }
        do {
            do {
                if (paramLong % 1000L == 500L) return paramLong / 1000L + ".5K";
            } while (paramLong % 1000L >= 500L);
            return paramLong / 1000L + "K+";
        } while (paramLong % 1000L != 0L);
    }


    public static boolean isChinese(char paramChar) {
        Character.UnicodeBlock localUnicodeBlock = Character.UnicodeBlock.of(
                paramChar);
        return (localUnicodeBlock ==
                Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS) ||
                (localUnicodeBlock ==
                        Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS) ||
                (localUnicodeBlock ==
                        Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A) ||
                (localUnicodeBlock ==
                        Character.UnicodeBlock.GENERAL_PUNCTUATION) ||
                (localUnicodeBlock ==
                        Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION) ||
                (localUnicodeBlock ==
                        Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS);
    }


    public static final boolean isChinese(String paramString) {
        char[] arrayOfChar = paramString.toCharArray();
        for (int i = 0; i < arrayOfChar.length; ++i) {
            if (isChinese(arrayOfChar[i])) return true;
        }
        return false;
    }


    public static boolean isEmail(String paramString) {
        return (!isEmpty(paramString)) && (paramString.contains("@"));
    }


    public static boolean isEmpty(String paramString) {
        return (paramString == null) || (paramString.trim().equals("")) ||
                (paramString.equals("null"));
    }


    public static boolean isEnglish(String paramString) {
        for (int i = 0; i < paramString.length(); ++i) {
            if ((((paramString.charAt(i) < 'A') ||
                    (paramString.charAt(i) > 'Z'))) &&
                    (((paramString.charAt(i) < 'a') ||
                            (paramString.charAt(i) > 'z')))) {
                return false;
            }
        }
        return true;
    }


    public static boolean isSame(Context paramContext, String paramString1, String paramString2) {
        return paramString1.equals(paramString2);
    }


    public static boolean phoneFormat(String paramString) {
        Pattern localPattern = Pattern.compile("[0-9]*");
        return (paramString != null) && (paramString.length() == 11) &&
                (localPattern.matcher(paramString).matches());
    }


    public static boolean phoneNumt(String paramString) {
        return (paramString != null) && (paramString.trim().length() == 11);
    }


    public static String stringFilter(String paramString) {
        String str = paramString.replaceAll("【", "[")
                                .replaceAll("】", "]")
                                .replaceAll("！", "!")
                                .replaceAll("：", ":");
        return Pattern.compile("[『』]").matcher(str).replaceAll("").trim();
    }


    public static String trim(EditText paramEditText) {
        return paramEditText.getText().toString().trim();
    }


    public static String trim(TextView paramTextView) {
        return paramTextView.getText().toString().trim();
    }

}
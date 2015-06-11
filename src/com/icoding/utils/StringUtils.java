package com.icoding.utils;

/**
 * 字符串工具类
 * @author TOY
 * 创建于2014/06/23
 */
public class StringUtils {
    
    public static final String EMPTY = "";
    
    /**
     * 判断两个字符串是否相等
     * @return 相等：true, 非等：false
     * StringUtils.equals(null, null)   = true
     * StringUtils.equals(null, "abc")  = false
     * StringUtils.equals("abc", null)  = false
     * StringUtils.equals("abc", "abc") = true
     * StringUtils.equals("abc", "ABC") = false
     */
    public static boolean isEquals(final CharSequence cs1, final CharSequence cs2){
        if (cs1 == cs2) {
            return true;
        }
        if (cs1 == null || cs2 == null) {
            return false;
        }
        if (cs1 instanceof String && cs2 instanceof String) {
            return cs1.equals(cs2);
        }
        return false;//其他都返回false
    }
    
    /**
     * 判断是否为空
     * @param cs 字符串
     * @return 如果为null或字符串长度等于0返回true
     */
    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }
    
    /**
     * 判断是否不为空
     * @param cs 字符串
     * @return 如果为null或字符串长度等于0返回false
     */
    public static boolean isNotEmpty(final CharSequence cs) {
        return !StringUtils.isEmpty(cs);
    }
    
    /**
     * 判断字符串是否为空字符串
     * @param cs：字符串
     * @return 如果对象为null, "","  "等都返回true
     */
    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * 是否不为空字符串
     * @param cs 字符串
     * @return 如果为null, "", "   "等返回false
     */
    public static boolean isNotBlank(final CharSequence cs) {
        return !StringUtils.isBlank(cs);
    }
    
    /**
     * 普通的字符串trim方法
     * @param str 字符串
     * @return 如果str为null则返回null
     */
    public static String trim(final String str) {
        return str == null ? null : str.trim();
    }

    /**
     * 字符串去除空格
     * @param str 字符串
     * @return 字符串,如果对象为null,"","  "等，返回null
     */
    public static String trimToNull(final String str) {
        final String ts = trim(str);
        return isEmpty(ts) ? null : ts;
    }
    
    /**
     * 字符串去除空格
     * @param str 字符串
     * @return 如果str为null,"","  "返回空字符串""
     */
    public static String trimToEmpty(final String str) {
        return str == null ? EMPTY : str.trim();
    }
    
}

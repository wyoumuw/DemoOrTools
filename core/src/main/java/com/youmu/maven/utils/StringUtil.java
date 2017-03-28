package com.youmu.maven.utils;

/**
 * Created by wyoumuw on 2017/3/28.
 */
public class StringUtil {
    /**
     * 获得field的get方法名字
     * @param field
     * @return 如果field是空则返回field否则返回get方法名
     */
    public static String toGetterMethodName(String field){
        if(isEmpty(field)){
            return field;
        }
        return new StringBuilder(3+field.length()).append("get").append(capitalize(field)).toString();
    }

    /**
     * 获得field的set方法名字
     * @param field
     * @return 如果field是空则返回field否则返回set方法名
     */
    public static String toSetterMethodName(String field){
        if(isEmpty(field)){
            return field;
        }
        return new StringBuilder(3+field.length()).append("set").append(capitalize(field)).toString();
    }

    /**
     * 判空null或者""
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        return str==null||str.length()==0;
    }
    /**
     * 引用于spring-core的函数
     * @param str
     * @return
     */
    public static String capitalize(final String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }
        final char firstChar = str.charAt(0);
        if (Character.isTitleCase(firstChar)) {
            // already capitalized
            return str;
        }
        return new StringBuilder(strLen)
                .append(Character.toTitleCase(firstChar))
                .append(str.substring(1))
                .toString();
    }
}

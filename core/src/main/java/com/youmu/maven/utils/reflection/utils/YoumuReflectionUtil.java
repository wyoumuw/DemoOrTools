package com.youmu.maven.utils.reflection.utils;

import java.lang.reflect.Method;

/**
 * Created by wyoumuw on 2017/3/28.
 */
public class YoumuReflectionUtil {

    /**
     * 生成函数全名的格式
     */
    private static final String _methodFormat="%s(%s)";
    /**
     * 生成函数全名
     * 会慢因为调用拷贝参数
     */
    public static String generFullMethodName(Method method){
        Class[] parameterTypes=method.getParameterTypes();
        return generFullMethodName(method.getName(),parameterTypes);
    }
    /**
     * 生成函数全名
     */
    public static String generFullMethodName(String methodName,Class ...parameterTypes){
        StringBuilder builder=new StringBuilder();
        String argsStr="";
        if(parameterTypes!=null&&parameterTypes.length>0) {
            for (Class parameter:parameterTypes){
                builder.append(parameter.getName());
                builder.append(",");
            }
            argsStr=builder.toString();
        }
        return String.format(_methodFormat,methodName,argsStr);
    }
}

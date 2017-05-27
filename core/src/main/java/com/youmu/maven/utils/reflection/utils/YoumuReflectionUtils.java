package com.youmu.maven.utils.reflection.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wyoumuw on 2017/3/28.
 */
public abstract class YoumuReflectionUtils {



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
        //设置初始大小为函数名+"()".length()
        StringBuilder builder=new StringBuilder(methodName.length()+2);
        builder.append(methodName);
        builder.append("(");
        if(parameterTypes!=null&&parameterTypes.length>0) {
            for (Class parameter:parameterTypes){
                builder.append(parameter.getName()).append(",");
            }
        }
        return builder.append(")").toString();
    }


    public static Method[] getAllMethods(Class clazz){
        Map<String, Method> methodMap = new HashMap<String, Method>();
        Class sup=clazz;
        while (!sup.equals(Object.class)) {
            Method[] methods = sup.getDeclaredMethods();
            for (Method method : methods) {
                String s = generFullMethodName(method);
                if (!methodMap.containsKey(s)) {
                    methodMap.put(s, method);
                }
            }
            sup = sup.getSuperclass();
        }
        Method[] methods=new Method[methodMap.values().size()];
        return  methodMap.values().toArray(methods);
    }

    public static   Field[] getAllFields(Class clazz){
        Map<String, Field> fieldMap = new HashMap<String, Field>();
        Class sup=clazz;
        while (!sup.equals(Object.class)) {
            Field[] fields = sup.getDeclaredFields();
            for (Field field : fields) {
                if (!fieldMap.containsKey(field.getName())) {
                    fieldMap.put(field.getName(), field);
                }
            }
            sup = sup.getSuperclass();
        }
;
        Field[] fields=new Field[fieldMap.values().size()];
        return  fieldMap.values().toArray(fields);
    }

}

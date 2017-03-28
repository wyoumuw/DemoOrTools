package com.youmu.maven.utils.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wyoumuw on 2017/3/28.
 * 不允许调用非public属性
 */
public abstract class BeanUtil {

    private static Map<Class,BeanDefine> cache=new HashMap<Class,BeanDefine>();

    private static Lock lock=new ReentrantLock();

    public  static  void initialize(Class[] classes){
        if(null==classes||0==classes.length){
            return;
        }
        for(Class clazz:classes){
            loadBean(clazz);
        }
    }

    /********************************以下属于不方便对外调用的方法****************************/
    /**
     * 获取一个类定义
     * @param clazz 哪个类
     * @return
     */
    public static BeanDefine getBeanDefine(Class<?> clazz) {
        BeanDefine bd=cache.get(clazz);
        if(null==bd){
            bd=loadBean(clazz);
        }
        return bd;
    }
    /**
     * 读取一个类的定义并放入缓存
     * @param clazz 哪个类
     * @return 类定义
     */
    private static BeanDefine loadBean(Class<?> clazz) {
        return loadBean(clazz,false);
    }
    /**
     * 读取一个类的定义并放入缓存
     * @param clazz 哪个类
     * @param override 如果存在是否覆盖
     * @return 类定义
     */
    private static BeanDefine loadBean(Class<?> clazz,boolean override) {
        if(cache.containsKey(clazz)&&!override){
            return cache.get(clazz);
        }
        BeanDefine bd=null;
        //TODO: 没考虑好怎么处理这个锁
        synchronized (BeanUtil.class) {
            if(cache.containsKey(clazz)&&!override){
                return cache.get(clazz);
            }
            bd = new BeanDefine();
            bd.setClazz(clazz);
            bd.setFullClassName(clazz.getName());
            bd.setConstructors(clazz.getConstructors());
            bd.setMethods(clazz.getMethods());
            bd.setFields(clazz.getFields());
            cache.put(clazz, bd);
        }
        return bd;
    }
    /********************************以下属于获取方法****************************/
    /**
     * 获取一个方法
     * @param clazz 哪个类
     * @param methodName 方法名
     * @param args 参数
     * @return 方法
     *
     */
    public static Method getMethod(Class<?> clazz, String methodName, Class ...args){
        BeanDefine bd=getBeanDefine(clazz);
        MethodDecorator[] methods=bd.getMethods();
        for(MethodDecorator method:methods){
                if(method.isSameMethod(methodName,args)){
                    return method.getMethod();
                }
        }
        return null;
    }
    /**
     * 获取一个空参数方法
     * @param clazz 哪个类
     * @param methodName 方法名
     * @return 方法
     *
     */
    public static Method getMethod(Class<?> clazz, String methodName){
        return getMethod(clazz,methodName,null);
    }
    /********************************以下属于获取属性的方法****************************/
    public static Field getField(Class<?> clazz, String fieldName){
        BeanDefine bd=getBeanDefine(clazz);
        Field[] fields=bd.getFields();
        for(Field field:fields){
            if(field.getName().equals(fieldName)){
                return field;
            }
        }
        return null;
    }

    public static <T> T getFieldValue(Object target, String fieldName){
        T rtn=null;
        try {
            rtn= (T) getField(target.getClass(),fieldName).get(target);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return rtn;
    }







}

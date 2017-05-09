package com.youmu.maven.utils.reflection;

import com.youmu.maven.utils.reflection.utils.YoumuReflectionUtil;

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
            bd = new BeanDefine(clazz,clazz.getName(),clazz.getDeclaredConstructors(), YoumuReflectionUtil.getAllFields(clazz),YoumuReflectionUtil.getAllMethods(clazz));
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
        MethodDecorator methodDecorator=bd.getMethods().get(YoumuReflectionUtil.generFullMethodName(methodName,args));
        return null==methodDecorator?null:methodDecorator.getMethod();
    }
    /**
     * 获取一个方法的包装类
     * @param clazz 哪个类
     * @param methodName 方法名
     * @param args 参数
     * @return 方法的包装类
     *
     */
    public static MethodDecorator getMethodDecorator(Class<?> clazz, String methodName, Class ...args){
        BeanDefine bd=getBeanDefine(clazz);
        return bd.getMethods().get(YoumuReflectionUtil.generFullMethodName(methodName,args));
    }


    /********************************以下属于获取属性的方法****************************/
    public static Field getField(Class<?> clazz, String fieldName){
        BeanDefine bd=getBeanDefine(clazz);

        return bd.getFields().get(fieldName);
    }

    public static <T> T getFieldValue(Object target, String fieldName) throws IllegalAccessException {
        T rtn=null;
        rtn= (T) getField(target.getClass(),fieldName).get(target);
        return rtn;
    }







}

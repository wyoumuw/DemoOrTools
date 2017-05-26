package com.youmu.maven.utils.reflection;

import com.youmu.maven.utils.reflection.utils.YoumuReflectionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 实体定义类
 */
public class BeanDefine {
    private final Class clazz;
    private final String fullClassName;
    private final Constructor[] constructors;
    private final Map<String,Field> fields;
    private final Map<String,MethodDecorator> methods;

    public BeanDefine(Class clazz, String fullClassName, Constructor[] constructors, Field[] fields, MethodDecorator[] methods) {
        this.clazz = clazz;
        this.fullClassName = fullClassName;
        this.constructors = constructors;
        this.fields=new HashMap<String,Field>();
        for (Field field : fields) {
            this.fields.put(field.getName(),field);
        }
        this.methods=new HashMap<String,MethodDecorator>();
        for (MethodDecorator method : methods) {
            this.methods.put(YoumuReflectionUtils.generFullMethodName(method.getMethod()),method);
        }
    }

    public BeanDefine(Class clazz, String fullClassName, Constructor[] constructors, Field[] fields,Method[] methods) {
        this.clazz = clazz;
        this.fullClassName = fullClassName;
        this.constructors = constructors;
        this.fields=new HashMap<String,Field>();
        for (Field field : fields) {
            this.fields.put(field.getName(),field);
        }
        this.methods=new HashMap<String,MethodDecorator>();
        for (Method method : methods) {
            this.methods.put(YoumuReflectionUtils.generFullMethodName(method),new MethodDecorator(method));
        }
    }

    public String getFullClassName() {
        return fullClassName;
    }


    public Constructor[] getConstructors() {
        return constructors;
    }

    public Map<String, Field> getFields() {
        return fields;
    }

    public Map<String, MethodDecorator> getMethods() {
        return methods;
    }

    public Class getClazz() {
        return clazz;
    }

}
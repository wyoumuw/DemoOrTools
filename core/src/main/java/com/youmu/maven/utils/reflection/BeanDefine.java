package com.youmu.maven.utils.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 实体定义类
 */
public class BeanDefine {
    private Class clazz;
    private String fullClassName;
    private Constructor[] constructors;
    private Field[] fields;
    private MethodDecorator[] methods;

    public String getFullClassName() {
        return fullClassName;
    }

    public void setFullClassName(String fullClassName) {
        this.fullClassName = fullClassName;
    }

    public Constructor[] getConstructors() {
        return constructors;
    }

    public void setConstructors(Constructor[] constructors) {
        this.constructors = constructors;
    }

    public Field[] getFields() {
        return fields;
    }

    public void setFields(Field[] fields) {
        this.fields = fields;
    }

    public MethodDecorator[] getMethods() {
        return methods;
    }

    public void setMethods(MethodDecorator[] methods) {
        this.methods = methods;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public void setMethods(Method[] methods) {
        if (null == methods) {
            return;
        }
        this.methods = new MethodDecorator[methods.length];
        for (int i = 0; i < methods.length; i++) {
            this.methods[i] = new MethodDecorator(methods[i]);
        }
    }
}
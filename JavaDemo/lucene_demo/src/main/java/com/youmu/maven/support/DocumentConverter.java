package com.youmu.maven.support;


import com.youmu.maven.utils.ArrayUtil;
import com.youmu.maven.utils.StringUtil;
import com.youmu.maven.utils.reflection.BeanUtil;
import org.apache.lucene.document.*;
import org.springframework.core.convert.converter.Converter;

import java.lang.reflect.Method;

/**
 * Created by wyoumuw on 2017/3/28.
 * name:Text/Long/Store/String:store(true/false)
 */
public abstract class DocumentConverter<S> implements Converter<S, Document> {
    public static final String TYPE_TEXT = "text";//default
    public static final String TYPE_LONG = "long";
    public static final String TYPE_STORE = "store";
    public static final String TYPE_STRING = "string";
    public static final String TYPE_DOUBLE = "double";
    public static final String TYPE_INT = "int";
    public static final String TYPE_FLOAT = "float";


    private String defaultSplitor = ":";

    private boolean isInitialized = false;

    @Override
    public Document convert(Object source) {

        Document document = new Document();
        String[] fields = getFieldNames();
        if (!isInitialized) {
            initial(source.getClass());
        }
        if (!ArrayUtil.isEmpty(fields)) {
            initial(source.getClass());
            for (int i = 0; i < fields.length; i++) {
                try {
                    Field field = getField(source,fields[i], this.fields[i], methods[i]);
                    document.add(field);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return document;
    }

    private Method[] methods;
    private java.lang.reflect.Field[] fields;

    public abstract String[] getFieldNames();

    public Field getField(Object target,String fieldStr, java.lang.reflect.Field field, Method getMethod) throws Exception {
        String falseStr = String.valueOf(false);
        String[] strings = fieldStr.split(defaultSplitor);
        Field.Store store = Field.Store.YES;
        Field result = null;
        if (!ArrayUtil.isEmpty(strings)) {
            if (strings.length == 3 && falseStr.equals(strings[2])) {
                store = Field.Store.NO;
            }
            if (strings.length >= 2) {
                switch (strings[1].toLowerCase()) {
                    case TYPE_LONG:
                        result = new LongPoint(field.getName(), Long.valueOf(String.valueOf(getMethod.invoke(target))));
                        break;
                    case TYPE_STRING:
                        result = new StringField(field.getName(), String.valueOf(getMethod.invoke(target)), store);
                        break;
                    case TYPE_STORE:
                        result = new StoredField(field.getName(), String.valueOf(getMethod.invoke(target)));
                        break;
                    case TYPE_DOUBLE:
                        result = new DoublePoint(field.getName(), Double.valueOf(String.valueOf(getMethod.invoke(target))));
                        break;
                    case TYPE_INT:
                        result = new IntPoint(field.getName(), Integer.valueOf(String.valueOf(getMethod.invoke(target))));
                        break;
                    case TYPE_FLOAT:
                        result = new FloatPoint(field.getName(), Float.valueOf(String.valueOf(getMethod.invoke(target))));
                        break;
                    default:
                        result = new TextField(field.getName(), String.valueOf(getMethod.invoke(target)), store);
                        ;
                }
            }
        }
        return result;
    }

    public void initial(Class clazz) {
        String[] fields = getFieldNames();
        if (!ArrayUtil.isEmpty(fields)) {
            this.fields = new java.lang.reflect.Field[fields.length];
            this.methods = new Method[fields.length];
            for (int i = 0; i < fields.length; i++) {
                String[] strings = fields[i].split(defaultSplitor);
                this.fields[i] = BeanUtil.getField(clazz, strings[0]);
                this.methods[i] = BeanUtil.getMethod(clazz, StringUtil.toGetterMethodName(strings[0]));
            }
        }
        isInitialized = true;
    }


    public boolean isInitialized() {
        return isInitialized;
    }

    public void setInitialized(boolean initialized) {
        isInitialized = initialized;
    }

    public String getDefaultSplitor() {
        return defaultSplitor;
    }

    public void setDefaultSplitor(String defaultSplitor) {
        this.defaultSplitor = defaultSplitor;
    }
}

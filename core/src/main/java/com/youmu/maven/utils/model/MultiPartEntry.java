package com.youmu.maven.utils.model;

import java.io.File;

/**
 * Created by youmu on 2017/5/31.
 */
public class MultiPartEntry {
    private Object entry;
    private String key;



    public boolean isFile(){
       return entry instanceof File;
    }

    public Object getEntry() {
        return entry;
    }

    public void setEntry(Object entry) {
        if(!(entry instanceof File)&&!(entry instanceof String)){
            throw new IllegalArgumentException("just File or String type to set");
        }
        this.entry = entry;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

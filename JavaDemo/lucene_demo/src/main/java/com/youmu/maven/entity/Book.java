package com.youmu.maven.entity;

/**
 * Created by wyoumuw on 2017/3/28.
 */
public class Book extends BaseObject {
    private String name;
    private String description;

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

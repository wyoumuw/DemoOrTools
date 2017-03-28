package com.youmu.maven.test;

/**
 * Created by wyoumuw on 2017/3/28.
 */
public class TestEntity extends BaseObject{
    private Long id;
    private String name;

    public String publicStr="publicStr";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

package com.youmu.maven.test;

import java.util.Date;

/**
 * Created by wyoumuw on 2017/3/28.
 */
public class TestEntity extends BaseObject{
    private Long id;
    private String name;
    private boolean bool;
    private TestEntity self;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TestEntity getSelf() {
        return self;
    }

    public void setSelf(TestEntity self) {
        this.self = self;
    }

    public boolean isBool() {
        return bool;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }

    public String publicStr="publicStr";

    public Long getId() {
        System.out.println("getId");return id;
    }

    public void setId(Long id) {
        System.out.println("setId");this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", publicStr='" + publicStr + '\'' +
                '}';
    }
}

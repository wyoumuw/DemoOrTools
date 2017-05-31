package com.youmu.maven.utils.cache;

/**
 * Created by dehua.lai on 2017/5/31.
 */
public abstract class GroupCache<K,V> implements Cache<K,V>{
    protected String group;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}

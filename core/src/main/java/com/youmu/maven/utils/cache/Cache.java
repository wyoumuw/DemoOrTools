package com.youmu.maven.utils.cache;

/**
 * Created by youmu on 2017/5/26.
 */
public interface Cache<K,V> {
    public V get(K key);
    public V get(K key,Class<V> clazz);
    //TODO 可能要加put带class？
    public V put(K key, V value);
    public V remove(K key);
    public boolean containsKey(K key);
}

package com.youmu.maven.utils.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dehua.lai on 2017/5/26.
 */
public class MapCache<K,V> implements Cache<K,V>{

    private Map<K,V> map=new HashMap<K, V>();
    @Override
    public V get(K key) {
        return map.get(key);
    }
    @Override
    public V put(K key, V value) {
        return map.put(key,value);
    }
    @Override
    public V remove(K key) {
        return map.remove(key);
    }

    @Override
    public boolean containsKey(K key) {
        return map.containsKey(key);
    }
}

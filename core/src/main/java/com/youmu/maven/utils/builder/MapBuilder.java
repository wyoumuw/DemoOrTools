package com.youmu.maven.utils.builder;

import com.youmu.maven.utils.CollectionUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by youmu on 2017/5/31.
 */
public class MapBuilder<K,V> implements Builder<Map<K,V>>{
    private HashMap<K,V> hashMap;
    public MapBuilder() {
        this.hashMap = new HashMap<>();
    }
    public MapBuilder<K,V> put(K k,V v){
        hashMap.put(k,v);
        return this;
    }

    public Map<K, V> build(Class<? extends Map> mapClazz){
        Map<K, V> map=null;
        try {
          map= mapClazz.newInstance();
          CollectionUtils.copy(this.hashMap,map);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return map;
    }

    @Override
    public Map<K, V> build() {
        return build(HashMap.class);
    }

    public Map<K,V> getRaw(){
        return this.hashMap;
    }
}

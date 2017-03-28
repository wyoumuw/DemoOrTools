package com.youmu.maven.utils;

import java.util.Collection;

/**
 * Created by wyoumuw on 2017/3/28.
 */
public class CollectionUtil {
    public boolean isEmpty(Collection collection){
        return collection==null||collection.size()==0;
    }
}

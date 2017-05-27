package com.youmu.maven.utils;

import java.util.Collection;
import java.util.Map;

/**
 * Created by wyoumuw on 2017/3/28.
 */
public abstract class CollectionUtils {
    public static boolean isEmpty(Collection collection){
        return null==collection||collection.isEmpty();
    }

    public static boolean isEmpty(Map map) {
        return null==map||map.isEmpty();
    }
}

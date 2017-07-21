package com.youmu.maven.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by wyoumuw on 2017/3/28.
 */
public abstract class ArrayUtils {
    public static boolean isEmpty(Object[] array){
        return array==null||array.length==0;
    }

    public static boolean isEmpty(int[] array){
        return array==null||array.length==0;
    }

    public static boolean isEmpty(char[] array){
        return array==null||array.length==0;
    }

    public static boolean isEmpty(byte[] array){
        return array==null||array.length==0;
    }

    public static boolean isEmpty(double[] array){
        return array==null||array.length==0;
    }

    public static boolean isEmpty(boolean[] array){
        return array==null||array.length==0;
    }

    public static boolean isEmpty(float[] array){
        return array==null||array.length==0;
    }

    public static boolean isEmpty(long[] array){
        return array==null||array.length==0;
    }

    public static boolean isEmpty(short[] array){
        return array==null||array.length==0;
    }

    public static <V> void copy(List<V> raw,List<V> dist) throws NullPointerException{
        Collections.copy(dist,raw);
    }
}

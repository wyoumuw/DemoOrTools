package com.youmu.maven.utils.common;

/**
 * Created by ucmed on 2017/5/5.
 */
public interface Converter<S,T> {
    public T convert(S source);
}

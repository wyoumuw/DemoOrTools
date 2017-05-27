package com.youmu.maven.utils.poi;

import com.youmu.maven.utils.common.Converter;

/**
 * Created by youmu on 2017/5/5.
 */
public interface Mapper<S,T> extends Converter<S,T> {
    Class getTargetClass();
}

package com.youmu.maven.utils.poi;

import org.apache.poi.ss.usermodel.Row;

/**
 * Created by youmu on 2017/5/5.
 */
public abstract class ExcelMapper<T> implements Mapper<Row,T> {
    public abstract  Integer getContentMarginTop();
}

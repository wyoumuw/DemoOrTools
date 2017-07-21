package com.youmu.maven.test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by youmu on 2017/5/27.
 */
public class ExcelWrapper<T> {
    private List<List<Exception>> exceptionTable;
    private List<T> objects;

    public List<List<Exception>> getExceptionTable() {
        return exceptionTable;
    }

    public void setExceptionTable(List<List<Exception>> exceptionTable) {
        this.exceptionTable = exceptionTable;
    }

    public List<T> getObjects() {
        return objects;
    }

    public void setObjects(List<T> objects) {
        this.objects = objects;
    }
}

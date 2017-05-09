package com.youmu.maven.utils.poi;

import com.youmu.maven.utils.StringUtil;
import com.youmu.maven.utils.reflection.BeanUtil;
import com.youmu.maven.utils.LoggerUtil;
import com.youmu.maven.utils.reflection.MethodDecorator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by ucmed on 2017/5/5.
 */
public class CommonExcelMapper<T> extends ExcelMapper<T> {
    //private Map<String, MethodDecorator> map;

    protected List<MethodDecorator> methods;
    protected Class<T> clazz;
    protected List<String> mappingList;

    protected CommonExcelMapper() {

    }

    public CommonExcelMapper(List<String> mappingList,Class<T> clazz) {
        this.clazz = clazz;
        this.mappingList = mappingList;
        init();
    }

    protected void init() {
        methods = new ArrayList<MethodDecorator>();
        for (String s : mappingList) {
            try {
                //skip blank or no mapping column
                if(StringUtil.isEmpty(s)){
                    continue;
                }
                Field field = BeanUtil.getField(clazz, s);
                methods.add(BeanUtil.getMethodDecorator(clazz, StringUtil.toSetterMethodName(s), field.getType()));
            } catch (Exception e) {
                LoggerUtil.getLogger().info("ignored field " + s+ ",maybe dont have setter?");
            }
        }
    }

    @Override
    public T convert(Row source) {
        T t;
        try {
            t = clazz.newInstance();

            int i=0;
            for (MethodDecorator method : methods) {
                Cell cell = source.getCell(i++);
                Class[] classes = method.getParameterTypes();
                try {
                    Object o = null;
                    try {
                        o = convertExcelType(cell, classes[0]);
                    } catch (Exception e) {
                        LoggerUtil.getLogger().info("can not convert", e);
                    }
                    method.getMethod().invoke(t, o);
                } catch (Exception e) {
                    LoggerUtil.getLogger().info("err", e);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return t;
    }

    //TODO：可能要改成registry
    public T convertExcelType(Cell cell, Class<T> type) {
        int cellType = cell.getCellType();
        Object rtn = null;
        if (type == String.class) {
            rtn = cell.getStringCellValue();
        } else if (type == long.class || type == Long.class) {
            rtn = Long.valueOf(Double.valueOf(cell.getNumericCellValue()).longValue());
        } else if (type == short.class || type == Short.class) {
            rtn = Short.valueOf(Double.valueOf(cell.getNumericCellValue()).shortValue());
        } else if (type == int.class || type == Integer.class) {
            rtn = Integer.valueOf(Double.valueOf(cell.getNumericCellValue()).intValue());
        } else if (type == char[].class) {
            rtn = cell.getStringCellValue().toCharArray();
        }
        return (T) rtn;
    }

    @Override
    public Class getTargetClass() {
        return clazz;
    }
}
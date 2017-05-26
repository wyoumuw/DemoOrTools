package com.youmu.maven.utils.poi;

import com.youmu.maven.utils.LoggerUtils;

import java.util.*;

/**
 * Created by ucmed on 2017/5/9.
 */
public class PropertiesExcelMapper<T> extends MapExcelMapper<T>{

    private static final String _classSuffix="class";
    private static final String _propPrefix="prop.";

    public PropertiesExcelMapper(Properties properties) {
        parse(properties);
    }

    protected void parse(Properties properties){
        super._mappingMap=new HashMap<String, String>();
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            String key= (String) entry.getKey();
            //print log
            LoggerUtils.getLogger().debug("maping "+key+" -> "+entry.getValue());
            if(key.startsWith(_propPrefix)){
                super._mappingMap.put((String) entry.getValue(),key.replaceFirst(_propPrefix,""));
            }else if(key.equals(_classSuffix)){
                try {
                    super.clazz= (Class<T>) Class.forName((String) entry.getValue());
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

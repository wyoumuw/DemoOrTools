package com.youmu.maven.utils.poi;

import com.youmu.maven.utils.LoggerUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by youmu on 2017/5/9.
 */
public class PropertiesExcelMapper<T> extends MapExcelMapper<T>{

    private static final String _CLASS="class";
    private static final String _PROP_PREFIX="prop.";
    private static final String _HEADER_MARGIN_TOP="header_margin_top";
    private static final String _HEADER_MARGIN_CONTENT="header_magrin_content";
    public PropertiesExcelMapper(Properties properties) {
        parse(properties);
    }

    protected void parse(Properties properties){
        super._mappingMap=new HashMap<String, String>();
        Integer headerMarginTop=0,headerMagrinContent=0;
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            String key= (String) entry.getKey();
            //print log
            LoggerUtils.getLogger().debug("maping "+key+" -> "+entry.getValue());
            if(key.startsWith(_PROP_PREFIX)){
                super._mappingMap.put((String) entry.getValue(),key.replaceFirst(_PROP_PREFIX,""));
            }else if(key.equals(_CLASS)){
                try {
                    super.clazz= (Class<T>) Class.forName((String) entry.getValue());
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }else if(key.equals(_HEADER_MARGIN_TOP)){
                headerMarginTop=Integer.valueOf(entry.getValue().toString());
            }else if(key.equals(_HEADER_MARGIN_CONTENT)){
                headerMagrinContent=Integer.valueOf(entry.getValue().toString());
            }
        }
        setHeaderMarginTop(headerMarginTop,headerMagrinContent);
    }


}

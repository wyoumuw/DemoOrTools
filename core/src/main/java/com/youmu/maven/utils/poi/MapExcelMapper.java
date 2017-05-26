package com.youmu.maven.utils.poi;

import com.youmu.maven.utils.StringUtils;
import com.youmu.maven.utils.LoggerUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.util.*;

/**
 * Created by ucmed on 2017/5/9.
 */
public class MapExcelMapper<T> extends CommonExcelMapper<T>  implements HeaderMappingMapper {
    protected Map<String,String> _mappingMap;

    public MapExcelMapper( Map<String, String> mappingMap, Class<T> clazz) {
        this._mappingMap = mappingMap;
        super.clazz=clazz;
    }

    protected MapExcelMapper() {
    }

    @Override
    public void remapping(Row header) {
        List<String> list=new ArrayList<String>();
        for (Cell cell : header) {
            String fieldName=_mappingMap.get(cell.getStringCellValue());
            if(StringUtils.isEmpty(fieldName)){
                LoggerUtils.getLogger().debug("skip column "+cell.getStringCellValue());
            }
            list.add(fieldName);
        }
        super.mappingList=list;
        init();
    }
}

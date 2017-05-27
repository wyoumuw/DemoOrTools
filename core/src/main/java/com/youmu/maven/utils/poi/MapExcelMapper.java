package com.youmu.maven.utils.poi;

import com.youmu.maven.utils.LoggerUtils;
import com.youmu.maven.utils.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by youmu on 2017/5/9.
 */

/**
 * 必须有header
 *
 * @param <T>
 */
public class MapExcelMapper<T> extends CommonExcelMapper<T>  implements HeaderMappingMapper {
    protected Map<String,String> _mappingMap;

    protected Integer _headerRow=0;

    public MapExcelMapper( Map<String, String> mappingMap, Class<T> clazz) {
        this(mappingMap,clazz,0);

    }

    public MapExcelMapper( Map<String, String> mappingMap, Class<T> clazz,Integer headerMarginTop) {
        this(mappingMap,clazz,headerMarginTop,0);
    }

    public MapExcelMapper( Map<String, String> mappingMap, Class<T> clazz,Integer headerMarginTop,Integer headerMagrinContent) {
        this._mappingMap = mappingMap;
        super.clazz=clazz;
        setHeaderMarginTop(headerMarginTop,headerMagrinContent);
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

    @Override
    public Integer getHeaderRow() {
        return _headerRow;
    }

    protected void setHeaderMarginTop(Integer headerMarginTop,Integer headerMagrinContent){
        this._headerRow=headerMarginTop;
        this._contentMarginTop=headerMarginTop+headerMagrinContent+1;
    }
}

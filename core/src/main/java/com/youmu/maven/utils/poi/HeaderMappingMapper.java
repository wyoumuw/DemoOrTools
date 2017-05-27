package com.youmu.maven.utils.poi;

import org.apache.poi.ss.usermodel.Row;

/**
 * Created by youmu on 2017/5/9.
 */
public interface HeaderMappingMapper {
    void remapping(Row header);
    Integer getHeaderRow();
}

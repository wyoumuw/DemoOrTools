package com.youmu.maven.utils.poi;

import com.youmu.maven.utils.common.Initializable;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ucmed on 2017/5/5.
 */
public class ExcelParser implements Initializable{
    private final Map<Class,Mapper> _mappers=new HashMap<Class,Mapper>();
    @Override
    public void init() {
        _mappers.clear();
    }

    public void addMapper(Mapper mapper){
        _mappers.put(mapper.getTargetClass(),mapper);
    }

    public void addMapper(List<String> linearProps, Class targetClass){
        _mappers.put(targetClass,new CommonExcelMapper(linearProps,targetClass));
    }

    /**
     * @param mapPair     Map<excel列名，field名>
     * @param targetClass
     */
    public void addMapper(Map<String, String> mapPair, Class targetClass){
        _mappers.put(targetClass,new MapExcelMapper(mapPair,targetClass));
    }

    public List parse(InputStream inputStream,String sheetName, Class targetClass,int headerRowNum) throws IOException {
        Workbook wb=null;
        try {
            wb = getWorkBook(inputStream);
            Sheet sheet = wb.getSheet(sheetName);
            Mapper mapper = _mappers.get(targetClass);
            if (null == mapper) {
                throw new RuntimeException("未找到类" + targetClass);
            }
            return parse(sheet,mapper,headerRowNum);
        }finally {
            if(null!=wb) {
                wb.close();
            }
        }
    }

    private  List parse(Sheet sheet,Mapper mapper,int headerRowNum){
        List list=new ArrayList();
        if(headerRowNum>=0&&mapper instanceof HeaderMappingMapper) {
            Row header = sheet.getRow(headerRowNum);
            ((HeaderMappingMapper) mapper).remapping(header);
        }
        //begin parse from head+1 row
        for (int i=headerRowNum+1;i<=sheet.getLastRowNum();i++){
            Row row= sheet.getRow(i);
            list.add(mapper.convert(row));
        }
        return list;
    }

    private Workbook getWorkBook(InputStream inputStream) throws IOException {
        return new XSSFWorkbook(inputStream);
    }
}
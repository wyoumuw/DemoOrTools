package com.youmu.maven.utils.poi;

import com.youmu.maven.utils.LoggerUtil;
import com.youmu.maven.utils.common.Initializable;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
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

    public List parse(InputStream inputStream,String sheetName, Class targetClass) throws IOException {
        Workbook wb=null;
        try {
            wb = getWorkBook(inputStream);
            Sheet sheet = wb.getSheet(sheetName);
            Mapper mapper = _mappers.get(targetClass);
            if (null == mapper) {
                throw new RuntimeException("未找到类" + targetClass);
            }
            return parse(sheet,mapper);
        }finally {
            if(null!=wb) {
                wb.close();
            }
        }
    }

    private  List parse(Sheet sheet,Mapper mapper){
        List list=new ArrayList();
        if(mapper instanceof HeaderMappingMapper) {
            HeaderMappingMapper headerMappingMapper= (HeaderMappingMapper) mapper;
            Row header = sheet.getRow(headerMappingMapper.getHeaderRow());
            ((HeaderMappingMapper) mapper).remapping(header);
        }
        ExcelMapper excelMapper= (ExcelMapper) mapper;
        //begin parse from head+1 row
        for (int i=excelMapper.getContentMarginTop();i<=sheet.getLastRowNum();i++){
            Row row= sheet.getRow(i);
            list.add(mapper.convert(row));
        }
        return list;
    }

    public void outToFile(Mapper mapper,List contents,File file) throws  IOException{
        if(!file.exists()){
            if(!file.createNewFile()){
                LoggerUtil.getLogger().debug("create file "+file.getAbsolutePath()+" failed");
            }
        }
        if(!(mapper instanceof MapExcelMapper)){
            throw new RuntimeException("");
        }
        Workbook workbook=createWorkBook();
    }

    private Workbook getWorkBook(InputStream inputStream) throws IOException {
        return new XSSFWorkbook(inputStream);
    }

    private Workbook createWorkBook(){
        return new XSSFWorkbook();
    }
}
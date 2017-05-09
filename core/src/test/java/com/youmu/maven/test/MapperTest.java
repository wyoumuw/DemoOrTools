package com.youmu.maven.test;

import com.youmu.maven.utils.poi.CommonExcelMapper;
import com.youmu.maven.utils.poi.ExcelParser;
import com.youmu.maven.utils.poi.MapExcelMapper;
import com.youmu.maven.utils.poi.PropertiesExcelMapper;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import javax.swing.text.html.parser.Entity;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by ucmed on 2017/5/5.
 */
public class MapperTest {

    @Test
    public void test1()throws Exception{
        Map<String, String> mapPair=new HashMap<>();
        mapPair.put("编号","id");
        mapPair.put("名称","name");
        FileInputStream inputStream=new FileInputStream("E:\\a.xlsx");
        ExcelParser parser=new ExcelParser();
        parser.addMapper(new MapExcelMapper(mapPair,TestEntity.class));
        List list=parser.parse(inputStream,"sheet1",TestEntity.class,0);
        for (Object o : list) {
            System.out.println(o);
        }
        System.out.println();

    }
    @Test
    public void test2()throws Exception{

        FileInputStream inputStream=new FileInputStream("E:\\a.xlsx");
        ExcelParser parser=new ExcelParser();
        Properties props=new Properties();
        props.load(new InputStreamReader(TestEntity.class.getClassLoader().getResourceAsStream("TestEntity.properties")));
        parser.addMapper(new PropertiesExcelMapper(props));
        List list=parser.parse(inputStream,"sheet1",TestEntity.class,0);
        for (Object o : list) {
            System.out.println(o);
        }
        System.out.println();

    }
}

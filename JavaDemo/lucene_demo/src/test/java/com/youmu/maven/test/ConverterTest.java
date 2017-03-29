package com.youmu.maven.test;

import org.junit.Test;
import org.springframework.beans.SimpleTypeConverter;

/**
 * Created by wyoumuw on 2017/3/29.
 */
public class ConverterTest {
    @Test
    public void test(){
        SimpleTypeConverter converter=new SimpleTypeConverter();
        Object obj=converter.convertIfNecessary(1,int.class);
        System.out.println(obj);
        System.out.println(obj.getClass().getName());
    }
}

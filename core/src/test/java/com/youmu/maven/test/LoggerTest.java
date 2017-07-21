package com.youmu.maven.test;

import com.youmu.maven.utils.LoggerUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * Created by wyoumuw on 2017/5/5.
 */
public class LoggerTest {
   static Logger logger= LoggerFactory.getLogger(LoggerTest.class);
    @Test
    public void log(){
        LoggerUtils.getLogger().debug("11111");
        double a=0.03;
        double b=0.02;
        System.out.println(new BigDecimal(a).subtract(new BigDecimal(b)).floatValue());

    }


    @Test
    public void log1(){
        StackTraceElement[] elements= Thread.currentThread().getStackTrace();
        for (StackTraceElement element : elements) {
            System.out.println(element.getClassName());
        }
    }

    public static void main(String[] args) {
        long start=System.nanoTime();
        for(int i=0;i<100000;i++) {
            logger.debug("dasd");
            //LoggerUtils.getLogger().debug("dasd");
        }
        System.out.println(System.nanoTime()-start);
    }
    @Test
    public void t1(){
        long start=System.nanoTime();
        for(int i=0;i<100000;i++) {
//            logger.debug("dasd");
            LoggerUtils.getLogger().debug("dasd");
        }
        System.out.println(System.nanoTime()-start);
    }
}

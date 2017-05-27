package com.youmu.maven.test;

import com.youmu.maven.utils.LoggerUtils;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by wyoumuw on 2017/5/5.
 */
public class LoggerTest {

    @Test
    public void log(){
        LoggerUtils.getLogger().debug("11111");
        double a=0.03;
        double b=0.02;
        System.out.println(new BigDecimal(a).subtract(new BigDecimal(b)).floatValue());

    }
}

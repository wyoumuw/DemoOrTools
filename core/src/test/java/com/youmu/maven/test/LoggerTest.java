package com.youmu.maven.test;

import com.youmu.maven.utils.LoggerUtil;
import org.junit.Test;

/**
 * Created by wyoumuw on 2017/5/5.
 */
public class LoggerTest {

    @Test
    public void log(){
        LoggerUtil.getLogger().debug("aas");


    }
}

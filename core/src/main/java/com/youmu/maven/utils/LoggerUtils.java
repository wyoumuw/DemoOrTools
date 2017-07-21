package com.youmu.maven.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wyoumuw on 2017/5/4.
 * 太慢了
 */
@Deprecated
public abstract class LoggerUtils {
    private static final Logger _defaultLogger= LoggerFactory.getLogger(LoggerUtils.class);


    public static Logger getLogger(){
        Logger logger=null;
        try{
            logger=LoggerFactory.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
        }catch (Throwable e){
            e.printStackTrace();
        }
        if(null==logger){
            logger=_defaultLogger;
        }
        return logger;

    }

}

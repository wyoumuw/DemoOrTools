package com.youmu.maven.utils.reflection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wyoumuw on 2017/5/4.
 */
public abstract class LoggerUtil {
    private static final Logger _defaultLogger= LoggerFactory.getLogger(LoggerUtil.class);


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

package com.youmu.maven.utils.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by youmu
 */
public abstract class Loggable {
    private Logger log= LoggerFactory.getLogger(getClass());
    public Logger getLog() {
        return log;
    }
}

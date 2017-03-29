package com.youmu.maven.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by wyoumuw on 2017/3/28.
 */
public class Environment {
    static{
        Properties properties=new Properties();
        try {
            properties.load(Environment.class.getClassLoader().getResourceAsStream("env.properties"));
        } catch (IOException e) {
            System.out.println("fail to load environment");
            e.printStackTrace();
        }
        STORE_DIRECTORY_PATH=properties.getProperty("STORE_DIRECTORY_PATH");
    }

    public static final String STORE_DIRECTORY_PATH;
}

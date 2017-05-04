package com.youmu.maven.dubbo.service.application;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by wyoumuw on 2017/4/27.
 */
public class ServerRunner {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext(new String[]{"applicationContext-service.xml"});
        context.start();
        System.out.println("启动");
        while(true);
    }
}

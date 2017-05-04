package com.youmu.maven.dubbo.consumer;

import com.youmu.maven.dubbo.service.WeatherService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by wyoumuw on 2017/4/27.
 */
public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext(new String[]{"applicationContext-consumer.xml"});
        context.start();
        WeatherService weatherService= context.getBean(WeatherService.class);
        System.out.println(weatherService.getWTDescByCityName("杭州"));
    }
}

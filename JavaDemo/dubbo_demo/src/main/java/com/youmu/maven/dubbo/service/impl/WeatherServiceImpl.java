package com.youmu.maven.dubbo.service.impl;

import com.youmu.maven.dubbo.service.WeatherService;

/**
 * Created by wyoumuw on 2017/4/27.
 */
public class WeatherServiceImpl implements WeatherService {
    public String getWTDescByCityName(String cityName) {
        return cityName+" is good";
    }
}

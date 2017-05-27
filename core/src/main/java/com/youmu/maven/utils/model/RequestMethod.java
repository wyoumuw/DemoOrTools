package com.youmu.maven.utils.model;

/**
 * Created by youmu on 2017/5/26.
 */
public enum RequestMethod {
    GET("GET"),POST("POST");
    private String method;
    RequestMethod(String method) {
        this.method=method;
    }

    public String getMethod() {
        return method;
    }
}

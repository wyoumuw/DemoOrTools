package com.youmu.maven.utils.model;

/**
 * Created by youmu on 2017/5/26.
 */
public class NetResult {
    private int code;
    private String msg;
    private String content;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isOk(){
        return (code/100)==2;
    }
}

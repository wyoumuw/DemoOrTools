package com.youmu.maven.utils.model;

import java.util.Map;

/**
 * Created by youmu on 2017/5/26.
 */
public class NetResult {
    private int code;
    private String msg;
    private byte[] content;
    private Map<String,String> header;

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

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public String getHeaderProperty(String key){
        if(null==header){
            return null;
        }
        return header.get(key);
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }

    public boolean isOk(){
        return (code/100)==2;
    }
}

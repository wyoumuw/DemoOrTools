package com.youmu.maven.utils.model;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by youmu on 2017/6/1.
 *
 * StringNetResult.wrapper to wrap a NetResult for StringNetResult
 */
public class StringNetResult extends NetResult{
    private String contentStr;

    public String getContentStr() {
        return contentStr;
    }

    public void setContentStr(String contentStr) {
        this.contentStr = contentStr;
    }


    public static StringNetResult wrapper(NetResult result) {

        if(null==result){
            return null;
        }
        StringNetResult stringNetResult=new StringNetResult();
        stringNetResult.setMsg(result.getMsg());
        stringNetResult.setCode(result.getCode());
        stringNetResult.setContent(result.getContent());
        stringNetResult.setHeader(result.getHeader());
        StringBuilder sb = new StringBuilder();
        ByteArrayInputStream inputStream=new ByteArrayInputStream(result.getContent());
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try{
            if (null != inputStream) {
                inputStreamReader = new InputStreamReader(inputStream, "utf-8");
                bufferedReader = new BufferedReader(inputStreamReader);
                String str = null;
                while ((str = bufferedReader.readLine()) != null) {
                    sb.append(str);
                }
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            try {
                if (null != bufferedReader) {
                    bufferedReader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (null != inputStreamReader) {
                    inputStreamReader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        stringNetResult.setContentStr(sb.toString());
        return stringNetResult;
    }
}

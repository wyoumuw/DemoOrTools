package com.youmu.maven.utils;


import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Created by wyoumuw on 2017/3/29.
 */
public class HttpUtil{
    private static CloseableHttpClient httpclient = HttpClients.createDefault();
    public static HttpResult get(String url,Map<String,String> map) throws IOException {
        if(StringUtil.isEmpty(url)){
            return null;
        }
        StringBuilder getParam=new StringBuilder();
        if(map!=null){
            getParam.append("?");
            for (Map.Entry<String, String> entry : map.entrySet()) {
                getParam.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            getParam.deleteCharAt(getParam.length()-1);
        }
        HttpGet getMethod=new HttpGet(url+getParam.toString());
        HttpResponse response=httpclient.execute(getMethod);
        HttpResult result=new HttpResult();
        result.setState(response.getStatusLine().getStatusCode());
        result.setMessage(response.getStatusLine().getReasonPhrase());
        if(HttpStatus.SC_OK==response.getStatusLine().getStatusCode()){
            InputStream is=null;
            try {
                is = response.getEntity().getContent();
                byte[] buffer = new byte[512];
                int len = -1;
                StringBuilder content = new StringBuilder();
                while (-1 != (len = is.read(buffer))) {
                    content.append(new String(buffer, 0, len));
                }
                result.setContent(content.toString());
            }finally {
                if(null!=is){
                    is.close();
                }
            }
        }
        return result;
    }

    public static class HttpResult{
        private int state;
        private String message;
        private String content;

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public boolean isOk(){
            return (state/10)==20;
        }
    }
}

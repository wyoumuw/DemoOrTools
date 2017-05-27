package com.youmu.maven.thread;

import com.youmu.maven.utils.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.*;

/**
 * Created by wyoumuw on 2017/4/9.
 */
public class FutureMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService es= Executors.newFixedThreadPool(3);
        Future<String> future=es.submit(new ReturnCallable("http://www.baidu.com"));
       // FutureTask<String> task=new FutureTask<String>(new ReturnCallable("http://www.baidu.com"));

        System.out.println(future.get());

    }
}
class ReturnCallable implements Callable<String>{
    private String url;

    public ReturnCallable(String url){
        this.url=url;
    }
    @Override
    public String call() throws Exception {
        if(StringUtils.isEmpty(url)) {
            return null;
        }
        StringBuilder sb=new StringBuilder();
        HttpURLConnection connection= (HttpURLConnection) new URL(this.url).openConnection();
        connection.connect();
        BufferedReader reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String line;
        while(null!=(line=reader.readLine())){
            sb.append(line.replaceAll(">",">\n"));
        }
        return sb.toString();
    }
}
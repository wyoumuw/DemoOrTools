package com.youmu.maven.demo;


import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by ヒカリ on 2016/6/14.
 */

public class JDBCTest {
    public static void  main(String[]args) throws Exception
    {


        Class.forName("com.mysql.jdbc.Driver");

        Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/seckill?useSSL=true","root","youmu");
        if(conn!=null){
            System.out.println("success");
        }
        else{
            System.out.println("fault");
        }
    }

}

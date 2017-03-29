package com.youmu.maven.dao.utils;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;

import java.io.InputStream;

/**
 * Created by wyoumuw on 2017/3/28.
 */
public abstract  class MyBatisUtil {
    private static boolean isInitialized=false;
    private static SqlSessionFactory sqlSessionFactory;
    public static void initialize(){
        if(isInitialized){
            System.out.println("isInitialized");
            return;
        }
        InputStream is=MyBatisUtil.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        sqlSessionFactory=builder.build(is);
        isInitialized=true;
    }
    public static SqlSession getSession(){
        return sqlSessionFactory.openSession();
    }

    public static void closeSession(SqlSession session){
        if(session!=null){
          session.close();
        }
    }

}

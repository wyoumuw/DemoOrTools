package com.youmu.maven.test;

import com.youmu.maven.utils.reflection.BeanDefine;
import com.youmu.maven.utils.reflection.BeanUtils;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wyoumuw on 2017/3/28.
 */
public class ReflectionTest {

    TestEntity testEntity;

    @Before
    public void preTest(){
        testEntity=new TestEntity();
        testEntity.setName("白玉楼正传");
    }

    @Test
    public void testMethod()throws Throwable{
        long start=System.currentTimeMillis();

        Method method= BeanUtils.getMethod(TestEntity.class,"getName");
        System.out.println(System.currentTimeMillis()-start+"ms");
        System.out.println(method.invoke(testEntity).toString());

        start=System.currentTimeMillis();
        method= BeanUtils.getMethod(TestEntity.class,"getName");
        System.out.println(System.currentTimeMillis()-start+"ms");
        System.out.println(method.invoke(testEntity).toString());

        start=System.currentTimeMillis();
        method= BeanUtils.getMethod(TestEntity.class,"getName");
        System.out.println(System.currentTimeMillis()-start+"ms");
        System.out.println(method.invoke(testEntity).toString());

        start=System.currentTimeMillis();
        method= BeanUtils.getMethod(TestEntity.class,"getName");
        System.out.println(System.currentTimeMillis()-start+"ms");
        System.out.println(method.invoke(testEntity).toString());

    }
    @Test
    public void testField()throws Throwable{
        long start=System.currentTimeMillis();
        System.out.println(BeanUtils.<String>getFieldValue(testEntity,"publicStr"));
        System.out.println(System.currentTimeMillis()-start+"ms");

        start=System.currentTimeMillis();
        System.out.println(BeanUtils.<String>getFieldValue(testEntity,"publicStr"));
        System.out.println(System.currentTimeMillis()-start+"ms");

        start=System.currentTimeMillis();
        System.out.println(BeanUtils.<String>getFieldValue(testEntity,"publicStr"));
        System.out.println(System.currentTimeMillis()-start+"ms");

        start=System.currentTimeMillis();
        System.out.println(BeanUtils.<String>getFieldValue(testEntity,"publicStr"));
        System.out.println(System.currentTimeMillis()-start+"ms");

        start=System.currentTimeMillis();
        for(int i=0;i<Integer.MAX_VALUE;i++)
            BeanUtils.<String>getFieldValue(testEntity,"publicStr");
        System.out.println(System.currentTimeMillis()-start+"ms");
    }
    @Test
    public void testThread() throws Throwable{
        long start=System.currentTimeMillis();
        Class.forName("com.youmu.maven.utils.reflection.BeanUtils");
                Runnable runnable=new Runnable() {
            @Override
            public void run() {
                BeanDefine bd= BeanUtils.getBeanDefine(TestEntity.class);
                System.out.println(Thread.currentThread().getName()+ BeanUtils.getBeanDefine(TestEntity.class));
                count++;
            }
        };
        int tcount=100;
        ExecutorService service= Executors.newCachedThreadPool();
        for (int i=0;i<tcount;i++) {
            service.execute(runnable);
        }
        while (count<tcount);
        System.out.println(count);
        System.out.println(System.currentTimeMillis()-start+"ms");
    }
    static  volatile  int count=0;

}

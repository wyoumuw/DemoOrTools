package com.youmu.maven.test;

import com.youmu.maven.utils.reflection.BeanDefine;
import com.youmu.maven.utils.reflection.BeanUtils;
import com.youmu.maven.utils.reflection.utils.YoumuReflectionUtils;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
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


        start=System.currentTimeMillis();
        method = BeanUtils.getMethod(TestEntity.class, "getName");
        for(int i=0;i<Integer.MAX_VALUE;i++) {
            method.invoke(testEntity);
        }
        System.out.println(System.currentTimeMillis()-start+"ms");
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

    @Test
    public void testPrivateField() throws Throwable{
        long start=System.currentTimeMillis();
        Field field= BeanUtils.getField(TestEntity.class,"name");
        field.setAccessible(true);
        for(int i=0;i<Integer.MAX_VALUE;i++) {
            field.get(testEntity);
        }
        System.out.println(System.currentTimeMillis()-start+"ms");
    }
    @Test
    public void testAllProduces() throws Throwable{
        BeanUtils.getBeanDefine(TestEntity.class);
        long start=System.currentTimeMillis();
        for(int i=0;i<Integer.MAX_VALUE;i++) {
            BeanUtils.getBeanDefine(TestEntity.class);
        }
        System.out.println(System.currentTimeMillis()-start+"ms");

        start=System.currentTimeMillis();
        for(int i=0;i<Integer.MAX_VALUE;i++) {
            //贼慢，速度全部死在这里
            //YoumuReflectionUtil.generFullMethodName("name");
        }
        System.out.println(System.currentTimeMillis()-start+"ms");

        BeanDefine bd= BeanUtils.getBeanDefine(TestEntity.class);
        String name= YoumuReflectionUtils.generFullMethodName("name");
        start=System.currentTimeMillis();
        for(int i=0;i<Integer.MAX_VALUE;i++) {
            bd.getMethods().get(name);
        }
        System.out.println(System.currentTimeMillis()-start+"ms");
    }
    @Test
    public void testGen() throws Throwable{
        long start=System.currentTimeMillis();
        for(int i=0;i<Integer.MAX_VALUE;i++) {
            //贼慢，速度全部死在这里
            YoumuReflectionUtils.generFullMethodName("name");
        }
        System.out.println(System.currentTimeMillis()-start+"ms");
    }
    @Test
    public void testClassName() throws Throwable{
        long start=System.currentTimeMillis();
        for(int i=0;i<Integer.MAX_VALUE;i++) {
            TestEntity.class.getName();
        }
        System.out.println(System.currentTimeMillis()-start+"ms");
    }

    @Test
    public void testConstructor() throws Throwable{
        TestEntity[] e=new TestEntity[110000];
        long start=System.currentTimeMillis();

        for(int i=0;i<110000;i++) {
           // e[i]=new TestEntity();
            //
            e[i]=TestEntity.class.newInstance();
        }
        System.out.println(System.currentTimeMillis()-start+"ms");
    }

    @Test
    public void lambdaTest() throws Throwable{



    }
    interface I{
        void print(String s);
    }
}

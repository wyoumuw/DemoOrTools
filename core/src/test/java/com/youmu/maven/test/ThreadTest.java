package com.youmu.maven.test;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by wyoumuw on 2017/4/13.
 */
public class ThreadTest {

    public static volatile int count=0;
    @Test
    public void test1() throws  Exception{


        Thread t1=new Thread(){
            @Override
            public void run() {
                synchronized (ThreadTest.class){
                    System.out.println("t1");
                    try {
                        ThreadTest.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    count++;
                }
                System.out.println("t1 over");
            }
        };

        Thread t2=new Thread(){
            @Override
            public void run() {
                synchronized (ThreadTest.class){
                    System.out.println("t2");
                    try {

                        ThreadTest.class.notify();
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                count++;
                }
                System.out.println("t2 over");
            }

        };
        t1.start();
        Thread.sleep(1000);
        t2.start();
        while(count<2){


        }
    }

@Test
    public void test2() throws  Exception{


        Thread t1=new Thread(){
            @Override
            public void run() {

                System.out.println("enter t1");

                synchronized (ThreadTest.class){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("t1");
                    count++;
                }
            }
        };

        Thread t2=new Thread(){
            @Override
            public void run() {
                System.out.println("enter t2");
                synchronized (ThreadTest.class){
                    System.out.println("t2");
                    count++;
                }
            }

        };
        t1.start();
        //Thread.sleep(1000);
        t2.start();
        while(count<2){


        }
    }
    @Test
    public void test3() throws  Exception{
        int i=0;
        do{
            i++;
            System.out.println(i);
            continue;

        }while(i<10);

    }
    @Test
    public void test4() throws  Exception{
        Future<String> f;
        ExecutorService service=Executors.newCachedThreadPool();
        f=service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("start "+System.currentTimeMillis());
                Thread.sleep(3000);
                System.out.println("end "+System.currentTimeMillis());
                return "aa";
            }
        });
        Thread.sleep(10000);
        System.out.println("start main "+System.currentTimeMillis());
        f.get();
        System.out.println("end main"+System.currentTimeMillis());
    }

    @Test
    public void test5() throws  Exception{
        FutureTask<String> f;
        ExecutorService service=Executors.newCachedThreadPool();

        service.execute(f=new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("start "+System.currentTimeMillis());
                Thread.sleep(3000);
                System.out.println("end "+System.currentTimeMillis());
                return "aa";
            }
        }));
        Thread.sleep(10000);
        System.out.println("start main "+System.currentTimeMillis());
        f.get();
        System.out.println("end main"+System.currentTimeMillis());
    }

    @Test
    public void test6() throws  Exception{
    }

    @Test
    public void testgc() throws InterruptedException {
        String c="123";
        System.out.println("pre");
        int []a=new int[20];
        System.out.println("create");
        a=null;
        System.gc();
        //Thread.sleep(10000);
        System.out.println("wake up");
    }

    @Test
    public void closePackage(){
       Runnable runnable= (Runnable) new Function(){

            @Override
            public Object apply(Object o) {
                final String wwaaa="youmu";
                return new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(wwaaa);
                    }
                };
            }
        }.apply(1);
       runnable.run();
    }
}


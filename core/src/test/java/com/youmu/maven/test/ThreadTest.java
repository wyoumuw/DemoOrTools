package com.youmu.maven.test;

import org.junit.Test;

import java.util.concurrent.*;

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
    CopyOnWriteArrayList a=new CopyOnWriteArrayList();
    }
}


package com.youmu.maven.test;

import org.junit.Test;

import java.io.*;

/**
 * Created by wyoumuw on 2017/4/9.
 */
public class ClassTest {
     @Test
    public void testInterface() throws Throwable{
         A a=new A();
         FileOutputStream fileOutputStream=new FileOutputStream(new File("D:\\a.txt"));
         ObjectOutputStream oos=new ObjectOutputStream(fileOutputStream);
         oos.writeObject(a);
         oos.flush();
         oos.close();

         FileInputStream fileInputStream=new FileInputStream(new File("D:\\a.txt"));
         ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
         Object obj=objectInputStream.readObject();

         objectInputStream.close();
         fileInputStream.close();
         fileOutputStream.close();

    }
    public static void sst(){
    }
}
class A implements Serializable {
    int a=11;
    protected  Object c(){
        return null;
    }
}
class B extends A{

    public String c(){
        return null;
    }
    public Object c(int a){return null;}
}
interface in{
    public static void a(){}
}
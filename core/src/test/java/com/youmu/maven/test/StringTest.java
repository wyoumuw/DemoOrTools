package com.youmu.maven.test;

import org.junit.Test;

import javax.activation.MimetypesFileTypeMap;
import java.security.MessageDigest;

/**
 * Created by wyoumuw on 2017/4/3.
 */
public class StringTest {
    @Test
    public void testStringReplaceAndStringFormat(){
        String result;
        String sss=new String("youmu");
        long start=System.nanoTime();
       result= String.format("www.%s.com",sss);
        System.out.println(System.nanoTime()-start);
        start=System.nanoTime();
        result= "www.{host}.com".replace("\\{youmu\\}",sss);
        System.out.println(System.nanoTime()-start);
        start=System.nanoTime();
        result= "www."+sss+".com";
        System.out.println(System.nanoTime()-start);

        StringBuilder sb=new StringBuilder();
        start=System.nanoTime();
        result= sb.append("www.").append(sss).append(".com").toString();
        System.out.println(System.nanoTime()-start);
    }

    @Test
    public void testMD5()throws Exception{
        MessageDigest digest=MessageDigest.getInstance("MD5");
        byte[] dig=digest.digest("aaaa".getBytes());

        for (int i = 0; i < dig.length; i++) {
            byte b = dig[i];
            System.out.println(Integer.toHexString(b&0xff));
        }
    }
    @Test
    public void equals()throws Exception{
        String a="a";
        String b="b";
        String ab="ab";
        String c=a+b;
        System.out.println(a+b==ab);
        System.out.println("ab"=="a"+"b");
    }

    @Test
    public void equalsInt()throws Exception{
        Integer a=1;
        Integer b=2;
        Integer ab=3;
        System.out.println(a+b==ab);
        System.out.println(3==2+1);

        Integer a1=127;
        Integer b1=1;
        Integer ab1=128;
        System.out.println(a1+b1==ab1);
        System.out.println(3==2+1);
    }


    @Test
    public void equalsBigInt()throws Exception{
        Integer a=1;
        Integer b=2;
        Integer ab=3;
        Integer apb=a+b;
        Integer abnew=new Integer(3);
        System.out.println(apb==abnew);
        System.out.println(3==2+1);

        Integer a1=127;
        Integer b1=1;
        Integer ab1=128;
        Integer apb1=a+b;
        System.out.println(apb1==ab1);
        System.out.println(3==2+1);
    }
    @Test
    public void genS()throws Exception{
        char[] s="0123456789abcdef".toCharArray();
        System.out.print("{");
        for (int i = 1; i <= s.length; i++) {
            System.out.print("\'"+s[i-1]+"\'");
            if(i!=s.length) System.out.print(",");
            if (0==i%4) System.out.println();
        }
        System.out.print("}");
    }

    @Test
    public void contentType()throws Exception{
        System.out.println(new MimetypesFileTypeMap().getContentType("E:\\home\\xxx.jpg"));
    }
}

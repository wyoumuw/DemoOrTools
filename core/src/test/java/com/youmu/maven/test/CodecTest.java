package com.youmu.maven.test;

import com.youmu.maven.utils.DigestUtils;
import com.youmu.maven.utils.codec.transfer.Hex;
import org.junit.Test;

/**
 * Created by youmu on 2017/5/27.
 */
public class CodecTest {

    @Test
    public void test1() throws Exception{
        System.out.println("youmu daisuki".getBytes().length);
        System.out.println(Hex.binToHexString(DigestUtils.sha1("youmu daisuki".getBytes())).length());
        System.out.println(DigestUtils.sha1("youmu daisuki".getBytes()).length);
        System.out.println("88fb95e453ea06b44f904e3a1ef9e750ffdb7fe6".length());
    }
    @Test
    public void test2() throws Exception{
        System.out.println("d6946faf4b445924091e20f3fae773fa".equals("d6946faf4b445924091e20f3fae773fa"));
    }
}

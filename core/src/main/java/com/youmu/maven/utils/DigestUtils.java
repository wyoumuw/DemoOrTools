package com.youmu.maven.utils;

import com.youmu.maven.utils.codec.model.DigestMethod;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by youmu on 2017/5/27.
 */
public abstract class DigestUtils {

    public static  byte[] sha1(byte[] msg){
        MessageDigest digest=getMethod(DigestMethod.SHA1);
        return digest.digest(msg);
    }
    public static  byte[] md5(byte[] msg){
        MessageDigest digest=getMethod(DigestMethod.MD5);
        return digest.digest(msg);
    }

    public static MessageDigest getMethod(DigestMethod method){
        try {
           return  MessageDigest.getInstance(method.getDigest());
        } catch (NoSuchAlgorithmException e) {
           throw new IllegalArgumentException();
        }
    }
}

package com.youmu.maven.utils.codec.model;

/**
 * Created by dehua.lai on 2017/5/27.
 */
public enum DigestMethod {
    SHA1("SHA-1"),MD5("MD5");
    private String digest;

    DigestMethod(String s) {
        this.digest=s;
    }

    public String getDigest() {
        return digest;
    }
}

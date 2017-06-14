package com.youmu.maven.utils.codec.transfer;

/**
 * Created by youmu on 2017/5/27.
 */
public class Hex {

    private static char[] CHAR_HEX={ '0','1','2','3',
                                       '4','5','6','7',
                                       '8','9','a','b',
                                       'c','d','e','f'};
    public static char[] binToHex(byte[] bin){
        char[] result=new char[bin.length<<1];
        for (int i = 0,j=0; i < bin.length; i++) {
            result[j++]=CHAR_HEX[(0xF0&bin[i])>>>4];
            result[j++]=CHAR_HEX[(0x0F&bin[i])];
        }
        return result;
    }

    public static String binToHexString(byte[] bin){
        return String.valueOf(binToHex(bin));
    }
}

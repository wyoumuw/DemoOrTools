package com.youmu.maven.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;

/**
 * Created by wyoumuw on 2017/3/30.
 */
public class SimpleApp {
    public static void main(String[] args) throws IOException {
        RandomAccessFile raFile=new RandomAccessFile("D:\\Nio.txt","rw");

        FileChannel channel=raFile.getChannel();

        channel.truncate(10);
        ByteBuffer buf= ByteBuffer.allocate(1024);
//        byte[] buffer=new byte[1024];
//
//
//        while (-1!=channel.read(buf)){
//            buf.flip();
//
//            while(buf.hasRemaining()){
//
//
//
//                //System.out.print(new String(buffer,"UTF-8"));
//                //buf.mark();
//            }
//            buf.clear();
//        }
        HashMap
        channel.close();
        raFile.close();
    }
}

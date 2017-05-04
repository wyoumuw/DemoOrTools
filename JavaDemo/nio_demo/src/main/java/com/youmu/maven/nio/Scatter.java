package com.youmu.maven.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by wyoumuw on 2017/3/31.
 */
public class Scatter implements Runnable {
    @Override
    public void run() {
        FileChannel fileChannel=null;
        RandomAccessFile file=null;
        try {
            ByteBuffer buffer = ByteBuffer.allocate(16);
            ByteBuffer buffer2 = ByteBuffer.allocate(16);
            file = new RandomAccessFile("D;\\Nio.txt", "rw");
            fileChannel =file.getChannel();
            //读内容到buffer 填满一个到下一个
            fileChannel.read(new ByteBuffer[]{buffer,buffer2});

        }catch (Exception e){

        }finally {
            try {
                fileChannel.close();
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

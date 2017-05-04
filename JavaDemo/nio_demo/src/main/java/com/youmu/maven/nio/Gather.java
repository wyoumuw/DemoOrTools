package com.youmu.maven.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by wyoumuw on 2017/3/31.
 */
public class Gather implements Runnable{


    @Override
    public void run() {
        FileChannel fileChannel=null;
        RandomAccessFile file=null;
        try {
            ByteBuffer buffer = ByteBuffer.allocate(16);
            ByteBuffer buffer2 = ByteBuffer.allocate(16);
            file = new RandomAccessFile("D;\\Nio.txt", "rw");
            fileChannel = file.getChannel();
            //按顺序写buffer到channel里，只写buffer内容大小不是最大长度
            fileChannel.write(new ByteBuffer[]{buffer,buffer2});

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

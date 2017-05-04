package com.youmu.maven.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

/**
 * Created by wyoumuw on 2017/3/31.
 */
public class Transfer implements Runnable {
    @Override
    public void run() {
        FileChannel fileChannel=null;
        FileChannel toFileChannel=null;
        RandomAccessFile file=null;
        RandomAccessFile toFile=null;
        try {
            ByteBuffer buffer = ByteBuffer.allocate(16);
            ByteBuffer buffer2 = ByteBuffer.allocate(16);
            file = new RandomAccessFile("D;\\Nio.txt", "r");
            toFile=new RandomAccessFile("D;\\outNio.txt", "w");
            fileChannel =file.getChannel();
            toFileChannel=toFile.getChannel();

            toFileChannel.transferFrom(fileChannel,0,fileChannel.size());
            //与上面那句话一个意思

            //fileChannel.transferTo(0,fileChannel.size(),toFileChannel);

        }catch (Exception e){

        }finally {
            try {
                fileChannel.close();
                file.close();
                toFileChannel.close();
                toFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        int[] poke=new int[52];
        for (int i = 0; i < 52; i++) {
            String s=scanner.next();
            if("j".equals(s.toLowerCase())){
                poke[i]=11;
            }else if("q".equals(s.toLowerCase())){
                poke[i]=12;
            }else if("k".equals(s.toLowerCase())){
                poke[i]=13;
            }else if("a".equals(s.toLowerCase())){
                poke[i]=14;
            }else if("2".equals(s.toLowerCase())){
                poke[i]=15;
            }else {
                poke[i]=Integer.valueOf(s);
            }
        }
        int i=0,count=poke.length;
        int cur=0,lastNo=0;
        while(count!=0){
            if(poke[i%52]>cur||lastNo+53==i){
                cur=poke[i%52];
                poke[i%52]=0;
                lastNo=i;
                count--;
                System.out.println(i%52+1);
            }
            i++;

        }
    }
}

package com.youmu.maven.nio;

import java.io.IOException;

/**
 * Created by wyoumuw on 2017/3/31.
 */
public class Selector implements Runnable {
    @Override
    public void run() {
        try {
            java.nio.channels.Selector selector= java.nio.channels.Selector.open();





        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

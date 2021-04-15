package com.example.juc.threadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FixedTP {
    public static void main(String[] args) {
//        Executors.newFixedThreadPool(3);
//        Executors.new
        ThreadPoolExecutor fix = new ThreadPoolExecutor(1, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        fix.execute(()->{
            System.out.println("aaaa");
        });
    }
}

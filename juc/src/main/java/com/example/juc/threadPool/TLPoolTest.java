package com.example.juc.threadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TLPoolTest {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2, 100, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10));
        executor.execute(()->{
            System.out.println(Thread.currentThread().getName() + "start");
            try {
                Thread.sleep(10*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "end");
        });

        for (int i = 0; i < 12; i++) {
            executor.execute(()->{
                System.out.println(Thread.currentThread().getName() + "start");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "end");
            });
        }

    }
}

package com.example.juc;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    private static int num = 5;
    private static CountDownLatch cdl =new CountDownLatch(num);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("比赛开始,比赛人数="+num);
        for (int i = 0; i < num; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "到达终点");
                cdl.countDown();
            },"运动员"+i).start();
        }
        cdl.await();
        System.out.println("比赛结束");
    }
}

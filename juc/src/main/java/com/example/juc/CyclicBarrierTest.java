package com.example.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    private static int num = 5;
    private static CyclicBarrier cb =new CyclicBarrier(num,()->{
        System.out.println("信号枪发射");
    });

    public static void main(String[] args) throws InterruptedException {
        System.out.println("比赛开始,比赛人数="+num);
        for (int i = 0; i < num; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "就位");
                try {
                    cb.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "开始跑步");
            },"运动员"+i).start();
        }

        //CyclicBarrier可以重复用


//        System.out.println("比赛开始,比赛人数="+num);
//        for (int i = 0; i < num; i++) {
//            new Thread(()->{
//                System.out.println(Thread.currentThread().getName() + "就位");
//                try {
//                    cb.await();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                System.out.println(Thread.currentThread().getName() + "开始跑步");
//            },"运动员"+i).start();
//        }
    }
}

package com.example.juc;

import java.util.concurrent.locks.LockSupport;

public class InterrupteNotify {
    public static void main(String[] args) {

        Thread t = new Thread(()->{
            System.out.println("start");
            LockSupport.park();
//            try {
//                Thread.sleep(1000*1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println("end");
        });
        t.start();
        t.interrupt();//可以打断sleep()的阻塞，LockSupport.park()的阻塞,Thread.interrupted()用来清除线程的中断信号

    }
}

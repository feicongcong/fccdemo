package com.example.juc;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    private static Semaphore semaphore =new Semaphore(2);

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(2000);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }

            },i+"").start();
        }
    }
}

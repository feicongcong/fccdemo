package com.example.algorithm.leetcode;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class FooBar {
    private int n;
    private Lock lock = new ReentrantLock(true);
    private final Condition condition = lock.newCondition();
    volatile boolean fooTurn = true;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i<n;i++) {
            lock.lock();
            try {
                while(!fooTurn) {
                    condition.await();
                }
                printFoo.run();
                fooTurn = false;
                condition.signal();
            }finally {
                lock.unlock();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i< n;i++) {
            lock.lock();
            try {
                while(fooTurn) {
                    condition.await();
                }
                printBar.run();
                fooTurn = true;
                condition.signal();
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        FooBar fooBar = new FooBar(10);
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(()->{
            try {
                fooBar.foo(()->System.out.print("foo"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executor.submit(() ->{
            try {
                fooBar.bar(()->System.out.print("bar"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}

package com.example.juc;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FooBar {
    private int n;
    Lock lock = new ReentrantLock(true);
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

 class FooBarQueue {
    private int n;
    private BlockingQueue<Integer> bar = new LinkedBlockingQueue<>(1);
    private BlockingQueue<Integer> foo = new LinkedBlockingQueue<>(1);
    public FooBarQueue(int n) {
        this.n = n;
    }
    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            foo.put(i);
            printFoo.run();
            bar.put(i);
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            bar.take();
            printBar.run();
            foo.take();
        }
    }

     public static void main(String[] args) {
         FooBarQueue fooBar = new FooBarQueue(10);
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


class FooBar6 {
    private int n;

    public FooBar6(int n) {
        this.n = n;
    }

    CyclicBarrier cb = new CyclicBarrier(2);
    volatile boolean fin = true;

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while(!fin);
            printFoo.run();
            fin = false;
            try {
                cb.await();
            } catch (BrokenBarrierException e) {}
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            try {
                cb.await();
            } catch (BrokenBarrierException e) {}
            printBar.run();
            fin = true;
        }
    }

    public static void main(String[] args) {
        FooBar6 fooBar = new FooBar6(10);
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


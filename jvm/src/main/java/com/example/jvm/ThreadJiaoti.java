package com.example.jvm;


public class ThreadJiaoti {
    private static Object object = new Object();

    public static void main(String[] args) throws InterruptedException {
//        Thread t1 = new Thread(() -> {
////            while (true) {
//                synchronized (object) {
//                    System.out.println("交");
//                    try {
////                        Thread.sleep(1000);
////                        object.notify();
//                        object.wait();
//                        System.out.println("交11111");
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
////            }
//        });
//        Thread t2 = new Thread(() -> {
////            while (true) {
//                synchronized (object) {
//                    System.out.println("替");
//                    //                        Thread.sleep(1000);
//                    object.notify();
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println("替11111");
////                        object.wait();
//                }
////            }
//        });
//        t1.start();
//        Thread.sleep(1000);
//        t2.start();
        new Thread(()->{
            Object object1 = new Object();
            System.out.println(1);
//            synchronized (object1) {
                try {
                    object1.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//            }
            System.out.println(2);
        }).start();

    }
}

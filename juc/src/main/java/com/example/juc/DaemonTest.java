package com.example.juc;

public class DaemonTest {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("aaaaaa");
            }
        });
        thread.setDaemon(true);
        thread.start();
        System.out.println("1111");
        System.out.println("1111");
        System.out.println("1111");
        System.out.println("1111");
    }
}

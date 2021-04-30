package com.example.jvm;

public class JoinTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("thread" + i);
            }
        });
        thread.start();
        thread.join();
        
        for (int i = 0; i <10 ; i++) {
            System.out.println("main"+i);
        }
    }
}

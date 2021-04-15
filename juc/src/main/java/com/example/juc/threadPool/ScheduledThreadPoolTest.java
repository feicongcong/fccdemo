package com.example.juc.threadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class ScheduledThreadPoolTest {
    public static void main(String[] args) {
        Executors.newScheduledThreadPool(3);
        Executors.newFixedThreadPool(3);
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(3);
    }
}

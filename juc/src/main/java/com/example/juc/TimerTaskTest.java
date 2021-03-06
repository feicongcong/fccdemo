package com.example.juc;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTaskTest {
    public static void main(String[] args) {
        TimerTask timerTask1 = new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"111111");
            }
        };
        TimerTask timerTask2 = new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"222222");
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask1,1000*5,1000);
        timer.schedule(timerTask2,1000*3,1000);
    }
}

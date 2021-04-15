package com.example.juc;

import java.util.PriorityQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityQueueTest {
    public static void main(String[] args) {
        PriorityBlockingQueue queue=new PriorityBlockingQueue<Ele>(3);
        queue.put(new Ele(1));
        queue.put(new Ele(8));
        queue.put(new Ele(2));
        queue.put(new Ele(5));
        queue.put(new Ele(3));
        while (!queue.isEmpty()){
            System.out.println(queue.poll());
        }

    }


}
class Ele implements Comparable<Ele>{
    int i;

    public Ele(int i) {
        this.i = i;
    }

    @Override
    public String toString() {
        return "Ele{" +
                "i=" + i +
                '}';
    }

    @Override
    public int compareTo(Ele o) {
        return i>o.i? 1: (i<o.i?-1:0);
    }
}
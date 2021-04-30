package com.example.juc.casUnsafe;

import sun.misc.Unsafe;

public class MyCas<T> {
    public MyCas(T obj){
        this.obj = obj;
    }
    private T obj;
    private static final Unsafe unsafe= MyUnsafeIns.reflectGetUnsafe();
    private static final long objOffset;
    static {
        try {
        objOffset = unsafe.objectFieldOffset(MyCas.class.getDeclaredField("obj"));
    } catch (Exception e) {
            throw new Error();
    }
}

    public final boolean myCompareAndSwap(T old ,T news){
        return unsafe.compareAndSwapObject(this,objOffset,old,news);
    }

}
class MyCasTest{
    public static void main(String[] args) {
        Integer i = new Integer(0);
        MyCas<Integer> myCas = new MyCas<>(i);

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+(myCas.myCompareAndSwap(i, 1)?" 修改成功":" 修改失败"));

        },"t1").start();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+(myCas.myCompareAndSwap(i, 1)?" 修改成功":" 修改失败"));

        },"t2").start();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+(myCas.myCompareAndSwap(i, 1)?" 修改成功":" 修改失败"));

        },"t3").start();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+(myCas.myCompareAndSwap(i, 1)?" 修改成功":" 修改失败"));

        },"t4").start();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+(myCas.myCompareAndSwap(i, 1)?" 修改成功":" 修改失败"));

        },"t5").start();
    }
}

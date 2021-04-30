package com.example.juc.casUnsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 内存指针工具，
 */
public class MyUnsafeIns {
    public static Unsafe reflectGetUnsafe(){
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe)field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Unsafe unsafe = reflectGetUnsafe();
        System.out.println(unsafe.ARRAY_OBJECT_BASE_OFFSET);
    }
}

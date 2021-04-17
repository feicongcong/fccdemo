package com.example.jvm;

import java.io.FileInputStream;
import java.lang.reflect.Method;

public class MyClassLoader extends ClassLoader {
    private String classPath;
    public MyClassLoader(String classPath) {
        this.classPath = classPath;
    }

    //打破双亲委派
    @Override
    protected Class<?> loadClass(String name, boolean resolve)
            throws ClassNotFoundException
    {
        synchronized (getClassLoadingLock(name)) {
            // First, check if the class has already been loaded
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                long t0 = System.nanoTime();
                long t1 = System.nanoTime();
                if (!name.startsWith("com.example.jvm")){
                    c = this.getParent().loadClass(name);
                }else {
                    c = findClass(name);
                }
                sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                sun.misc.PerfCounter.getFindClasses().increment();
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }

    //自定义类加载器 重写findClass()方法
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] data = loadByte(name);
            //defineClass将一个字节数组转为Class对象，这个字节数组是class文件读取后最终的字节 数组。
            return defineClass(name, data, 0, data.length);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
    }

    private byte[] loadByte(String name) throws Exception {
        name = name.replaceAll("\\.", "/");
        FileInputStream fis = new FileInputStream(classPath + "/" + name
                + ".class");
        int len = fis.available();
        byte[] data = new byte[len];
        fis.read(data);
        fis.close();
        return data;
    }

    public static void main(String args[]) throws Exception {
        //初始化自定义类加载器，会先初始化父类ClassLoader，其中会把自定义类加载器的父加载 器设置为应用程序类加载器AppClassLoader
        MyClassLoader classLoader1 = new MyClassLoader("D:/test1");
        // D盘创建 test1/com/example/jvm 几级目录，将User类的复制类User1.class丢入该目录
        Class clazz1 = classLoader1.loadClass("com.example.jvm.User");
        Object obj1 = clazz1.newInstance();
        Method method1 = clazz1.getDeclaredMethod("sout", null);
        method1.invoke(obj1, null);
        System.out.println(clazz1.getName());
        System.out.println(clazz1.getClassLoader());


        MyClassLoader classLoader2 = new MyClassLoader("D:/test2");
        // D盘创建 test2/com/example/jvm 几级目录，将User类的复制类User.class丢入该目录
        Class clazz2 = classLoader2.loadClass("com.example.jvm.User");
        Object obj2 = clazz2.newInstance();
        Method method2 = clazz2.getDeclaredMethod("sout", null);
        method2.invoke(obj2, null);
        System.out.println(clazz2.getName());
    }


}

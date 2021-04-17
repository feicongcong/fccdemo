package com.example.jvm;

public class TestDynamicLoad {

    static {
        System.out.println("*************load TestDynamicLoad************");
    }

    public static void main(String[] args) throws InterruptedException {
        //idea的debug按钮会把程序进行编译，则该程序中的TestDynamicLoad、A、B类都会在程序执行前编译生成class文件
        new A();

        System.out.println("*************load test************");
        B b = null;  //B的字节码文件不会加载进jvm,不会执行B的静态代码块，除非这里执行 new B()
        Thread.sleep(Integer.MAX_VALUE);
    }
}

class A {
    static {
        System.out.println("*************load A************");
    }

    public A() {
        System.out.println("*************initial A************");
    }
}

class B {
    static {
        System.out.println("*************load B************");
    }

    public B() {
        System.out.println("*************initial B************");
    }
}
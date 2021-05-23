package com.example.jdk8.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Description:
 * @Author: CC.F
 * @Date: 11:54 2018/12/5
 */
public class ReflectDemo {
    private String name;

    public ReflectDemo(String name) {
        this.name = name;
    }

    public ReflectDemo() {
    }

    public void method1(String age){
        System.out.println("年龄= "+age);
    }

    public static void main(String[] args) throws Exception {
        Class class1= ReflectDemo.class;
        System.out.println(class1);//class com.fcc.jdk8api.core.reflect.Reflect
        Class class2=new ReflectDemo().getClass();
        System.out.println(class2);//class com.fcc.jdk8api.core.reflect.Reflect
        System.out.println(class2.getName());//com.fcc.jdk8api.core.reflect.Reflect
        System.out.println(class1==class2);//true


        System.out.println(class1.getPackage());//Package 	getPackage()获取此类的包
        System.out.println(class1.getSuperclass());//


        //构造方法
        //Constructor<?>[] 	getDeclaredConstructors()返回 Constructor 对象的一个数组，这些对象反映此 Class 对象表示的类声明的所有构造方法。
        Constructor[] constructorArr=class1.getConstructors();
        for(int i=0;i<constructorArr.length;i++){
            System.out.println(constructorArr[i].toString());
        }
        //Constructor<T> 	getDeclaredConstructor(Class<?>... parameterTypes)返回一个 Constructor 对象，该对象反映此 Class 对象所表示的类或接口的指定构造方法。
        Constructor constructor=class1.getDeclaredConstructor(String.class);
        ReflectDemo reflectDemo =(ReflectDemo)constructor.newInstance("fcc");
        //Annotation[][] 	getParameterAnnotations()
        //          按照声明顺序返回一组数组，这些数组表示通过此 Constructor 对象表示的方法的形参上的注释。
        constructor.getParameterAnnotations();




        //成员变量
        //Field[] 	getFields()返回一个包含某些 Field 对象的数组，这些对象反映此 Class 对象所表示的类或接口的所有可访问公共(public)字段
        //Field[] 	getDeclaredFields()返回 Field 对象的一个数组，这些对象反映此 Class 对象所表示的类或接口所声明的所有字段。
        System.out.println(class1.getDeclaredFields());
        Field[] declaredFields=class1.getDeclaredFields();
        for(int i=0;i< declaredFields.length;i++){
            System.out.println(declaredFields[i].toString());
        }

        //方法
        //Method 	getDeclaredMethod(String name, Class<?>... parameterTypes)
        // 返回一个 Method 对象，该对象反映此 Class 对象所表示的类或接口的指定已声明方法。
        Method method= class1.getDeclaredMethod("method1", String.class);
        System.out.println(method);
        System.out.println(method.getName());
        //AccessibleObject 类是 Field、Method 和 Constructor 对象的基类
        // 值为 true 则指示反射的对象在使用时应该取消 Java 语言访问检查
        method.setAccessible(true);
        method.invoke(new ReflectDemo(),"20");

    }
}
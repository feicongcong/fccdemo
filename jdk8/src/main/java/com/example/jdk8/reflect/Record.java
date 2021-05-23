package com.example.jdk8.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;

/**
 * @Description:
 * @Author: CC.F
 * @Date: 13:01 2018/12/5
 */
@MyAnnotation
public class Record {
    @MyAnnotation(value = "fcc")
    private String name="feicong";

    public Record(String name){
        this.name=name;
    }
    public Record(){
    }
    public static void main(String[] args) throws Exception{

        Record record=new Record();
        System.out.println(record.getClass().getModifiers());
        Annotation[] annotations=record.getClass().getDeclaredAnnotations();
        Annotation annotation=record.getClass().getDeclaredAnnotation(MyAnnotation.class);
        for(int i=0;i<annotations.length;i++){
            System.out.println(annotations[i].toString());
        }
        //  boolean 	isAnnotation()
        //          如果此 Class 对象表示一个注释类型则返回 true。
        System.out.println(record.getClass().isAnnotation());
        System.out.println(annotation.getClass().isAnnotation());
        System.out.println(MyAnnotation.class.isAnnotation());
        //  boolean 	isAnnotationPresent(Class<? extends Annotation> annotationClass)
        //          如果指定类型的注释存在于此元素上，则返回 true，否则返回 false。
        System.out.println(record.getClass().isAnnotationPresent(MyAnnotation.class));
        System.out.println(annotation.annotationType());
        System.out.println(annotation.getClass());
        System.out.println(((MyAnnotation) annotation).value());
        System.out.println(((MyAnnotation) annotation).value());

        //  int 	getModifiers()
        //          以整数形式返回由此 Field 对象表示的字段的 Java 语言修饰符。
        System.out.println(record.getClass().getDeclaredField("name").getModifiers());//2
        if(record.getClass().getDeclaredField("name").isAnnotationPresent(MyAnnotation.class)){
            //Object 	get(Object obj)
            //          返回指定对象上此 Field 表示的字段的值。
            System.out.println(record.getClass().getDeclaredField("name").get(record));//feicong
            System.out.println(record.getClass().getDeclaredField("name").getAnnotation(MyAnnotation.class).value());//fcc
        }



        Constructor constructor=record.getClass().getDeclaredConstructor(String.class);
        Record record1=(Record)constructor.newInstance("fcc11");
        //Annotation[][] 	getParameterAnnotations()
        //          按照声明顺序返回一组数组，这些数组表示通过此 Constructor 对象表示的方法的形参上的注释。
        Annotation[][] parameterAnnotations1=constructor.getParameterAnnotations();
        for(int i=0;i<parameterAnnotations1.length;i++){
            int len=parameterAnnotations1[i].length;
            System.out.println("i= "+i);
            for(int k=0;k<len;k++){
                MyAnnotation annotation1= (MyAnnotation)parameterAnnotations1[i][k];
                System.out.println(annotation1.value());
                System.out.println(annotation1.num());
            }

        }
    }
}
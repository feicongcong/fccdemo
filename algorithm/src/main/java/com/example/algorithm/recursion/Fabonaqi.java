package com.example.algorithm.recursion;

public class Fabonaqi {
    public static int f(int n){
        if(n==1||n==2){
            return 1;
        }
        return f(n-1)+f(n-2);
    }

    public static int f1(int n){
        int i=3;
        int a1=1;
        int a2=1;
        int result=1;
        if(n==1||n==2){
            return 1;
        }
        while (i<=n){

            result=a1+a2;
            i++;
            a1=a2;
            a2=result;

        }
        return result;
    }
    public static void main(String[] args){
        System.out.println(f1(8));
    }
}

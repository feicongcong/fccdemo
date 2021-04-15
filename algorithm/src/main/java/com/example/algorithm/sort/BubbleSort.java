package com.example.algorithm.sort;

import java.util.Arrays;

public class BubbleSort {

    public static void sort(int[] arr){
        for(int i=arr.length-1;i>0;i--){
            for(int j=0;j<i;j++){
                if(arr[j]>arr[j+1]){
                    Util.swap(arr,j,j+1);
                }
            }
        }
    }

    public static void main(String[] args){
        int[] arr ={4,7,9,1,6,3,0,1,2,8,4};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}

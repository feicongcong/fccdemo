package com.example.algorithm.sort;

import java.util.Arrays;

public class SelectSort {

    public static void sort(int[] arr){
        if(null==arr || arr.length<=1){
            return;
        }
        for(int i=0;i<arr.length;i++){
            int minIndex = i;
            for(int j=i+1;j<arr.length;j++){
               minIndex = arr[minIndex]<arr[j]?minIndex:j;
            }
            Util.swap(arr,i,minIndex);
        }
    }

    public static void main(String[] args){
        int[] arr ={4,7,9,1,6,3,0,1,2,8,4};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}

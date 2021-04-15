package com.example.algorithm.sort;

import java.util.Arrays;

public class InsertSort {
    public static void sort(int[] arr){
        if(null==arr || arr.length<=1){
            return;
        }
//        for(int i=1;i<arr.length;i++){
//            for(int j=i;j>0&&arr[j]<arr[j-1];j--){
//                Util.swap(arr,j,j-1);
//            }
//        }
        int i=1;
        while (i<arr.length){
            int j=i;
            while (j>0&& arr[j]<arr[j-1]){
                Util.swap(arr,j,j-1);
                j--;
            }
            i++;
        }
    }

    public static void main(String[] args){
        int[] arr ={4,7,9,1,6,3,0,1,2,8,4};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}

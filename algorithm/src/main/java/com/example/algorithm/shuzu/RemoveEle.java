package com.example.algorithm.shuzu;

/**
 * 双指针法（快慢指针法）：通过一个快指针和慢指针在一个for循环下完成两个for循环的工作
 * 双指针法将时间复杂度O(n^2)的解法优化为 O(n)的解法
 */
public class RemoveEle {
    public static void main(String[] args) {
        System.out.println(baoli(new int[]{0, 1, 2, 2, 3,2, 0, 4, 2}, 2));
    }
    /**
     * 编号：27. 移除元素
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
     *
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并「原地」修改输入数组。
     *
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     *
     * 示例 1:
     * 给定 nums = [3,2,2,3], val = 3,
     * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
     * 你不需要考虑数组中超出新长度后面的元素。
     *
     * 示例 2:
     * 给定 nums = [0,1,2,2,3,0,4,2], val = 2,
     * 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
     */
    public static int remEle(int[] arr,int tar){
        int size =arr.length;
        int slow =0;

        for (int fast = 0; fast< size; fast++) {
            if (tar != arr[fast]) {
                arr[slow] = arr[fast];
                slow++;
            }
        }
        return slow;
    }

    public static int baoli(int[] arr ,int tar){
        int size =arr.length;
        for (int i = 0; i < size; i++) {
            if(arr[i]==tar){
                for (int j = i; j <size-1 ; j++) {
                    arr[j]=arr[j+1];
                }
                i--;
                size--;
            }
        }
        return size;
    }











    public static int myRemEle(int[] arr ,int tar){
        int size =arr.length;
        int slow = 0 ;

        for(int fast = 0;fast < size; fast++){
            if(tar!=arr[fast]){
                arr[slow] = arr[fast];
                slow++;
            }
        }
        return slow;
    }
}

package com.example.algorithm.shuzu;

/**
 * 数组理论：
 * 下标从0开始
 * 不能越界
 * 数组是连续的，创建之初大小已经决定
 */
public class ErFen {
    public static void main(String[] args) {
        System.out.println(myErfen(new int[]{1, 2, 3, 4, 5}, 6));
    }

    /**
     * 编号35：搜索插入位置
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     *
     * 你可以假设数组中无重复元素。
     *
     * 示例 1:
     * 输入: [1,3,5,6], 5
     * 输出: 2
     * @param nums
     * @param target
     * @return
     */
    public  static  int erFen(int[] nums,int target){
        int n = nums.length;
        int left = 0;
        int right = n - 1; // 定义target在左闭右闭的区间里，[left, right]
        while (left <= right) { // 当left==right，区间[left, right]依然有效
            int middle = left + ((right - left) / 2);// 防止溢出 等同于(left + right)/2
            if (nums[middle] > target) {
                right = middle - 1; // target 在左区间，所以[left, middle - 1]
            } else if (nums[middle] < target) {
                left = middle + 1; // target 在右区间，所以[middle + 1, right]
            } else { // nums[middle] == target
                return middle;
            }
        }
        // 分别处理如下四种情况
        // 目标值在数组所有元素之前  [0, -1]
        // 目标值等于数组中某一个元素  return middle;
        // 目标值插入数组中的位置 [left, right]，return  right + 1
        // 目标值在数组所有元素之后的情况 [left, right]， return right + 1
        return right + 1;
    }

    public static int myErfen(int[] arr,int tar){
        if(null==arr|| arr.length ==0){
            return -1;
        }
        int left = 0;
        int right = arr.length-1;
        while (left<=right){
            int mid =(left+right)/2;
            if(arr[mid]< tar){
                left =mid+1;
            }else if(tar< arr[mid]){
                right = mid-1;
            }else{
                return mid;
            }
        }
        return right+1;
    }
}

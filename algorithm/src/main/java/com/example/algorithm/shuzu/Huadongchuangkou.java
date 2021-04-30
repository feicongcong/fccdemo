package com.example.algorithm.shuzu;

/**
 * 滑动窗口:根据当前子序列和大小的情况，不断调节子序列的起始位置。从而将O(n^2)的暴力解法降为O(n)
 */
public class Huadongchuangkou {
    public static void main(String[] args) {
        System.out.println(minSubArrayLen2(new int[]{2, 3, 1, 2, 4, 3}, 7));
    }

    /**
     * 题目209.长度最小的子数组
     * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。如果不存在符合条件的子数组，返回 0。
     *
     * 示例：
     * 输入：s = 7, nums = [2,3,1,2,4,3]
     * 输出：2
     * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
     */
    public static int minSubArrayLen(int[] nums,int tar){
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum=0;
            int len =0;
            for (int j = i; j < nums.length && sum<tar; j++) {
                sum=sum + nums[j];
                len++;
            }
            if(sum>=tar){
                res= Math.min(res,len);
            }
        }
        return res == Integer.MAX_VALUE? 0: res;
    }

    public static int minSubArrayLen2(int[] nums,int tar){
        int sum=0;
        int res = Integer.MAX_VALUE;
        int start=0;
        int len = 0;
        for (int end = 0; end < nums.length; end++) {
            sum=sum + nums[end];

            while(sum>=tar){
                len =end-start+1;
                sum =sum-nums[start];
                start++;
                res =Math.min(len,res);
            }
        }
        return res == Integer.MAX_VALUE? 0: res;
    }
}

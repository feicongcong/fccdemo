package com.example.algorithm.dp;

/**
 * 当最优化问题具有重复子问题和最优子结构的时候，就是动态规划出场的时候了。
 * 动态规划算法的核心就是提供了一个memory来缓存重复子问题的结果，避免了递归的过程中的大量的重复计算。
 * 动态规划算法的难点在于怎么将问题转化为能够利用动态规划算法来解决。
 * 当重复子问题的数目比较小时，动态规划的效果也会很差。如果问题存在大量的重复子问题的话，那么动态规划对于效率的提高是非常恐怖的
 */
class MaxArr {
    public static int maxSubArray(int[] nums) {
        int dp = 0;
        int maxAns = nums[0];
        for (int x : nums) {
            dp = Math.max(dp + x, x);
            maxAns = Math.max(maxAns, dp);
        }
        return maxAns;
    }

    public static void main(String[] args) {
        maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4});
    }
}


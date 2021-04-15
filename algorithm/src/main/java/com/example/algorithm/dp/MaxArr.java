package com.example.algorithm.dp;

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


package com.example.algorithm.dp;

public class BeiBao {

    public static void main(String[] args) {
        int[] weight ={20,10,40};
        int[] value ={100,60,120};
        int pkg = 50;

        int[][] dp = new int[weight.length+1][pkg+1];
        for (int i = 1; i <= weight.length; i++) {
            for (int bao =1 ;bao<=pkg;bao++){
                if(weight[i-1]<=bao){
                   dp[i][bao]=Math.max(dp[i-1][bao-weight[i-1]]+ value[i-1],
                           dp[i-1][bao]);
                }else{
                    dp[i][bao]=dp[i-1][bao];
                }
            }
        }

        System.out.println(dp[weight.length][pkg]);
    }
}

package leetCode.medium;

import java.util.Arrays;

/**
 * 279. 完全平方数
 * https://leetcode-cn.com/problems/perfect-squares/
 *
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *
 * 示例 1:
 *
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 * 示例 2:
 *
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 *
 */
public class LeetCode279 {

    /**
     * 1、假设我们有一个dp[] 表示dp在坐标i的时候的和为n的最优解个数
     * 2、同时我们有一个从1<i<Math.sqrt(n)+1的数组，
     * int squarMax = Math.sqrt(n)+1;
     * int[] squarNum = new int[squarMax];
     * for(int i = 0;i<squarMax;i++){
     * squarNum[i] = i*i;
     * }
     * 因为squarNum[i]可以占据一项
     * 那么对于任意一个i,循环这个数组dp[i] = Math.min(dp[i],dp[i-squarNum[i]]+1)
     * for(int i = 1;i<=n;i++){
     * for(int j=1;j<squarMax;j++){
     * if(i<squarNum[j]){
     * break;
     * }
     * dp[i] = Math.min(dp[i],dp[i-squarNum[i]]+1);
     * }
     * }
     * return dp[n];
     *
     * @param n
     * @return
     */
    public int numSquares(int n) {
        //表示在等于i的时候的平方数个数
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        int maxSquars = (int)Math.sqrt(n)+1;
        int[] squarNum = new int[maxSquars];
        for(int i=1;i<maxSquars;i++){
            squarNum[i]=i*i;
        }
        for(int i = 1;i<=n;i++){
            for(int j = 1;j<maxSquars;j++){
                if(i<squarNum[j]){
                    break;
                }
                dp[i] = Math.min(dp[i],dp[i-squarNum[j]]+1);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        LeetCode279 leetCode279 = new LeetCode279();
        int n = 13;
        int result = leetCode279.numSquares(13);
        System.out.println(result);
    }
}

package leetCode.medium;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/perfect-squares/
 * <p>
 * 279. 完全平方数
 * <p>
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 * <p>
 * 示例 1:
 * <p>
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 * 示例 2:
 * <p>
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 *
 * @author chenwu on 2020.9.3
 */
public class LeetCode279_20200903 {

    public int numSquares(int n) {
        if (n <= 0) {
            return 0;
        }
        int maxLength = ((int) Math.sqrt(n))+1;
        int[] squarArray = new int[maxLength];
        for (int i = 1; i < maxLength; i++) {
            squarArray[i] = i * i;
        }
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < maxLength ; j++) {
                if (i < squarArray[j]) {
                    break;

                }
                dp[i] = Math.min(1+dp[i - squarArray[j]], dp[i]);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        LeetCode279_20200903 leetCode279_20200903 = new LeetCode279_20200903();
        int n = 13;
        int result = leetCode279_20200903.numSquares(n);
        System.out.println("result=" + result);
    }
}

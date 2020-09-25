package leetCode.medium;

/**
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 *
 * 309. 最佳买卖股票时机含冷冻期
 *
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
 *
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 *
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 示例:
 *
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 *
 * @author chenwu on 2020.9.25
 */
public class LeetCode309_20200925 {

    public int maxProfit(int[] prices) {
        if(prices==null || prices.length<=1){
            return  0;
        }
        int length = prices.length;
        //假定持股为0,不持股为1，冷冻期为2
        //那么每一天每一种状态都对应一个最大值
        /**
         *                   持股
         *               |          \|
         *          不持股     -       冷冻期
         *
         */

        //根据题意:
        //dp[0][i] = Math.max(dp[0][i-1],dp[2][i-1]-prices[i])
        //dp[1][i] = Math.max(dp[1][i-1],dp[0][i-1]+prices[i])
        //dp[2][i] = dp[1][i-1]
        int[][] dp = new int[3][length];
        dp[0][0] = -prices[0];
        dp[1][0] = 0;
        dp[2][0] = 0;
        for(int i = 1;i<length;i++){
            dp[0][i] = Math.max(dp[0][i-1],dp[2][i-1]-prices[i]);
            dp[1][i] = Math.max(dp[1][i-1],dp[0][i-1]+prices[i]);
            dp[2][i] = dp[1][i-1];
        }
        return Math.max(dp[1][length-1],dp[2][length-1]);
    }

    public static void main(String[] args) {
        int[] prices = new int[]{1,2,3,0,2};
        LeetCode309_20200925 leetCode309_20200925 = new LeetCode309_20200925();
        int maxProfit = leetCode309_20200925.maxProfit(prices);
        System.out.println(maxProfit);
    }
}

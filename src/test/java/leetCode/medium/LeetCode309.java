package leetCode.medium;

/**
 * 309. 最佳买卖股票时机含冷冻期
 *
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
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
 */
public class LeetCode309 {


    /**
     * 记住两个价格min,max
     * 记住最大交易额
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if(prices==null||prices.length<=1){
            return 0;
        }
        if(prices.length==2){
            return prices[1]>prices[0]?prices[1]-prices[0]:0;
        }
        //j的状态有3种，0表示不持股,1表示持股  2表示冷冻
        //假定dp[i][j] 表示从0到i的最大收益,j表示当前状态
        //状态转移可见leetcode309.png
        int length = prices.length;
        int[][] dp = new int[length][3];
        //第0天的状态
        dp[0][0] = 0;
        dp[0][1] = -prices[0];//表示购买了一股
        dp[0][2] = 0;
        for(int i  = 1;i<length;i++){
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][2]-prices[i]);
            dp[i][2] = dp[i-1][0];
        }
        int maxProfit = Math.max(dp[length-1][0],dp[length-1][2]);
        return maxProfit;
    }

    public static void main(String[] args) {
        LeetCode309 leetCode309 = new LeetCode309();
        int[] prices = new int[]{1,2,3,0,2};
        int maxProfit = leetCode309.maxProfit(prices);
        System.out.println("maxProfit="+maxProfit);
    }
}

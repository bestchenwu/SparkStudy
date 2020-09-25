package leetCode.medium;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/coin-change/
 *
 * 322. 零钱兑换
 *
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 示例 1:
 *
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * 示例 2:
 *
 * 输入: coins = [2], amount = 3
 * 输出: -1
 *
 * @author chenwu on 2020.9.25
 */
public class LeetCode322_20200925 {

    public int coinChange0(int[] coins, int amount) {
        //假定int dp[],dp[i]表示对于金额i所需的最少货币个数
        //那么dp[i] = coins[j]+dp[i-conis[j])
        //dp[0] = -1
        int[] dp = new int[amount+1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        for(int i = 0 ;i<coins.length;i++){
            if(coins[i]<amount+1){
                dp[coins[i]] = 1;
            }
        }
        for(int i = 1;i<=amount;i++){
            for(int j = 0;j<coins.length;j++){
                if(i>coins[j]){
                    if(dp[i-coins[j]]>0 && dp[i-coins[j]]!=Integer.MAX_VALUE){
                        dp[i] = Math.min(dp[i],1+dp[i-coins[j]]);
                    }
                }
            }
        }
        return dp[amount]==Integer.MAX_VALUE?-1:dp[amount];
    }

    /**
     * 这种做法很慢,不可取。原因是在 for(int k=1;k<=count;k++){ 判断了很多次
     *
     * @param coins
     * @param amount
     * @return
     *
     * @author chenwu on 2020.9.25
     */
    @Deprecated
    public int coinChange(int[] coins, int amount) {
        if(amount<=0 || coins == null || coins.length<1){
            return 0;
        }
        int[] dp = new int[amount+1];
        Arrays.fill(dp,amount+1);
        for(int i = 0;i<coins.length;i++){
            if(coins[i]<=amount){
                dp[coins[i]] = 1;
            }
        }
        for(int i = 1;i<=amount;i++){
            if(dp[i]==1){
                continue;
            }
            for(int j = 0;j<coins.length;j++){
                if(i>coins[j]){
                    int count = i / coins[j];
                    for(int k=1;k<=count;k++){
                        dp[i] = Math.min(dp[i],k+dp[i-k*coins[j]]);
                    }
                }
            }
        }
        return dp[amount] == amount+1?-1:dp[amount];
    }

    public static void main(String[] args) {
//        int[] coins = new int[]{1, 2, 5};
//        int amount = 11;
        int[] coins = new int[]{1};
        int amount = 2;
        LeetCode322_20200925 leetCode322_20200925 = new LeetCode322_20200925();
        int coinChange = leetCode322_20200925.coinChange(coins, amount);
        System.out.println(coinChange);
    }
}

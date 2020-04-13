package leetCode.medium;

import java.util.Arrays;

/**
 * 322. 零钱兑换
 *
 * https://leetcode-cn.com/problems/coin-change/
 *
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
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 *
 */
public class LeetCode322 {

    /**
     * 先对coins做排序
     * int[] dp 表示凑到amount的最小的硬币个数
     * dp[i] = coins*max+dp[i-coins*max]
     * 更进一步:
     * private int helpCoinChange(int[] coins,int maxIndex,int amount){}
     *dp[amount] = amount/coins + helpCoinChange(coins,maxIndex-1,amount-coins[maxIndex]*max) ||
     *
     * @param coins
     * @param amount
     * @return
     */
    //todo:超出时间
    public int coinChange0(int[] coins, int amount) {
        if(coins==null || coins.length==0 || amount<0){
            return -1;
        }
        if(amount ==  0){
            return 0;
        }
        int coinChange = helpCoinChange(coins, 0, amount);
        return coinChange;
    }

    private int helpCoinChange(int[] coins,int coinId,int amount){
        if(amount==0){
            return 0;
        }
        if(coinId<coins.length && amount>0){
            int maxVal = amount/coins[coinId];
            int minChange = Integer.MAX_VALUE;
            for(int x = 0;x<=maxVal;x++){
                if(amount>=x*coins[coinId]){
                    int res = helpCoinChange(coins,coinId+1,amount-x*coins[coinId]);
                    if(res!=-1){
                        minChange = Math.min(minChange,res+x);
                    }
                }
            }
            return minChange!=Integer.MAX_VALUE?minChange:-1;
        }
        return -1;
    }

    /**
     * 假定F(i)表示凑成面额为i需要的最少硬币数
     * 假定coins数组的个数是n,那么F(i) = min F(i-coins[j]) (0<=j<=n) +1
     *
     * 这样我们可以从0开始计算起
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange2(int[] coins, int amount) {
        if(coins==null||coins.length==0||amount<0){
            return -1;
        }
        if(amount == 0){
            return 0;
        }
        //dp[i]表示凑成金额为i的硬币数量最少为多少
        int[] dp = new int[amount+1];
        Arrays.fill(dp,amount+1);
        dp[0] = 0;
        for(int i = 1;i<=amount;i++){
            for(int j = 0;j<coins.length;j++){
                if(i>=coins[j]){
                    dp[i] = Math.min(dp[i],dp[i-coins[j]]+1);
                }
            }
        }
        return dp[amount]>amount?-1:dp[amount];
    }


    public int coinChange(int[] coins, int amount) {
        //int[] dp = new int[amount+1]
        // for(int i = 0;i<coins.length;i++){
        //         dp[i] = Math.min(dp[i-1],1+dp[i-coins[i]]);
        // }
        //dp[0] = -1
        //
        if(amount<=0 || coins==null ||  coins.length<=0){
            return -1;
        }
        int[] dp = new int[amount+1];
        Arrays.fill(dp,amount+1);
        for(int i = 0;i<coins.length;i++){
            if(coins[i]<=amount){
                dp[coins[i]] = 1;
            }
        }
        for(int i = 1;i<=amount;i++){
            if(dp[i]== 1){
                continue;
            }
            for(int j = 0;j<coins.length;j++){
                if(i>=coins[j]){
                    int count = i/coins[j];
                    for(int k = 1;k<=count;k++){
                        dp[i] = Math.min(dp[i],dp[i-count*coins[j]]+count);
                    }
                }
            }
        }
        return dp[amount] == amount+1?-1:dp[amount];
    }

    public static void main(String[] args) {
        LeetCode322 leetCode322 = new LeetCode322();
        int[] coins = new int[]{1, 2, 5};
        int amount = 11;
//        int[] coins = new int[]{2};
//        int amount = 4;
//        int[] coins = new int[]{3,5,7};
//        int amount = 13;
//        int[] coins = new int[]{186,419,83,408};
//        int amount = 6249;
//        int[] coins = new int[]{1};
//        int amount = 1;
//        int[] coins = new int[]{474,83,404,3};
//        int amount = 264;
        int coinChange = leetCode322.coinChange(coins, amount);
        System.out.println("coinChange="+coinChange);
    }
}

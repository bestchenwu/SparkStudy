package leetCode;

/**
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
 *
 * 可以多次买卖股票，但必须把前一次的股票卖出
 */
public class LeetCode122 {

    public int maxProfit(int[] prices) {
        if(prices==null||prices.length<=1){
            return 0;
        }
        int min = prices[0];
        int max = min;
        int result = 0;
        for(int i = 1;i<prices.length;i++){
            if(prices[i]>max){
                max = prices[i];
            }else{
                //执行卖出
                result +=(max-min);
                min = prices[i];
                max = min;
            }
        }
        return result+(max-min);
    }

    public static void main(String[] args) {
        LeetCode122 leetCode122 = new LeetCode122();
        int[] prices = new int[]{7,6,4,3,1};
        //期望7
        int result = leetCode122.maxProfit(prices);
        System.out.println(result);
    }
}

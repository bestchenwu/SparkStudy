package leetCode.medium;


/**
 * 494. 目标和
 * <p>
 * https://leetcode-cn.com/problems/target-sum/
 * <p>
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
 * <p>
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums: [1, 1, 1, 1, 1], S: 3
 * 输出: 5
 * 解释:
 * <p>
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * <p>
 * 一共有5种方法让最终目标和为3。
 * <p>
 * 注意:
 * <p>
 * 数组非空，且长度不会超过20。
 * 初始的数组的和不会超过1000。
 * 保证返回的最终结果能被32位整数存下。
 */
public class LeetCode494 {

    private int count;

    private void helpFindTargetSumWays(int[] nums, int i, int currentSum, int target) {
        if (i == nums.length) {
            if (currentSum == target) {
                count += 1;
            }
        } else {
            helpFindTargetSumWays(nums, i + 1, currentSum + nums[i], target);
            helpFindTargetSumWays(nums, i + 1, currentSum - nums[i], target);
        }

    }

    /**
     * 枚举法
     *
     * @param nums
     * @param S
     * @return
     */
    public int findTargetSumWays0(int[] nums, int S) {
        helpFindTargetSumWays(nums, 0, 0, S);
        return count;
    }

    /**
     * 假定dp[i][j]表示[0,i]范围内到目标j的方案个数，那么
     * dp[i][j] = dp[i-1][j+nums[i]]+dp[i-1][j-nums[i]]
     * 考虑到数组越界，由于j最大为1000，最小为-1000，所以可以给数组的第二维增加2000
     * dp[i][j+2000] = dp[i-1][j+nums[i]+2000] + dp[i-1][j-nums[i]+2000]
     * 这时候dp的定义就要是dp = new int[nums.length][4001]
     *
     * @param nums
     * @param S
     * @return
     */
    public int findTargetSumWays(int[] nums, int S) {
        if(nums==null || nums.length ==0 || S>1000 || S<-1000){
            return 0;
        }
        int[][] dp = new int[nums.length][4001];
        dp[0][nums[0]+2000] = 1;
        dp[0][-nums[0]+2000] = 1;
        for(int i = 1;i<nums.length;i++){
            for(int j = -1000;j<=1000;j++){
                dp[i][j+2000] = dp[i-1][j+nums[i]+2000]+dp[i-1][j-nums[i]+2000];
            }
        }

        return dp[nums.length-1][S+2000];
    }

    public static void main(String[] args) {
        LeetCode494 leetCode494 = new LeetCode494();
        int[] nums = new int[]{1, 1, 1, 1, 1};
        int S = 3;
        int targetSumWays = leetCode494.findTargetSumWays(nums, S);
        System.out.println("targetSumWays="+targetSumWays);

    }
}

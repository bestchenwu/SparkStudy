package leetCode.medium;

/**
 * https://leetcode-cn.com/problems/longest-increasing-subsequence/
 *
 * 300. 最长上升子序列
 *
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 *
 * 示例:
 *
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 *
 * @author chenwu on 2020.9.19
 */
public class LeetCode300_20200919 {

    /**
     * 假设我们定义dp[]数组 dp[i]代表在索引i的位置的最大上升子序列的长度
     * 那么dp[i] = max(dp[j])+1(nums[i]>nums[j])
     * 最大长度=max(dp[i])
     *
     * @param nums
     * @return int 最大上升子序列长度
     * @author chenwu on 2020.9.19
     */
    public int lengthOfLIS(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        int length = nums.length;
        int[] dp = new int[length];
        dp[0] = 1;
        int result = 1;
        for(int i = 1 ;i<length;i++){
            int maxJLength = 0;
            for(int j = i-1;j>=0;j--){
                if(nums[i]>nums[j]){
                    maxJLength = Math.max(dp[j],maxJLength);
                }
            }
            dp[i] = maxJLength+1;
            result = Math.max(dp[i],result);
        }
        return result;
    }

    public static void main(String[] args) {
        LeetCode300_20200919 leetCode300_20200919 = new LeetCode300_20200919();
        int[] nums = new int[]{10,9,2,5,3,7,101,18};
        int result = leetCode300_20200919.lengthOfLIS(nums);
        System.out.println("result="+result);
    }
}

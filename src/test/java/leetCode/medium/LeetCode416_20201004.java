package leetCode.medium;

import java.util.Arrays;
import java.util.stream.StreamSupport;

/**
 * https://leetcode-cn.com/problems/partition-equal-subset-sum/
 * 416. 分割等和子集
 *
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 * 注意:
 *
 * 每个数组中的元素不会超过 100
 * 数组的大小不会超过 200
 * 示例 1:
 *
 * 输入: [1, 5, 11, 5]
 *
 * 输出: true
 *
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 *  
 *
 * 示例 2:
 *
 * 输入: [1, 2, 3, 5]
 *
 * 输出: false
 *
 * 解释: 数组不能分割成两个元素和相等的子集.
 *
 * @author chenwu on 2020.10.4
 */
public class LeetCode416_20201004 {

    /**
     * 假定target = sum/2,
     * boolean[][] dp = new boolean[][];
     * dp[i][j]表示由0-i能否组成j
     * 那么dp[i][j] = dp[i-1][j] || dp[i][j-nums[i]]
     *
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        if(nums==null || nums.length <=1){
            return false;
        }
        int sum = Arrays.stream(nums).sum();
        if(sum%2!=0){
            return false;
        }
        int target = sum/2;
        int length = nums.length;
        //dp[i][j]表示是否可以由0-i组成j
        boolean[][] dp = new boolean[length][target+1];
        if(nums[0]<=target){
            dp[0][nums[0]] = true;
        }
        for(int i = 1;i<length;i++){
            for(int j = 0;j<=target;j++){
                dp[i][j] = dp[i-1][j];

                if(nums[i] == j){
                    dp[i][j] = true;
                    continue;
                }

                if(j>=nums[i]){
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]];
                }
            }
        }
        return dp[length-1][target];
    }

    public static void main(String[] args) {
        LeetCode416_20201004 leetCode416_20201004 = new LeetCode416_20201004();
        //int[] nums = new int[]{1, 5, 11, 5};
        int[] nums = new int[]{1, 2, 3, 8};
        boolean canPartition = leetCode416_20201004.canPartition(nums);
        System.out.println(canPartition);
    }
}

package leetCode.medium;

import java.util.Arrays;
import java.util.stream.StreamSupport;

/**
 * 416. 分割等和子集
 *
 * https://leetcode-cn.com/problems/partition-equal-subset-sum/
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
 */
public class LeetCode416 {

    /**
     * 假定求出了集合的一半大小定义为sum
     * 那么问题的本质就是是否可以在nums里面找到其和等于sum的两个数组
     * 1、如果nums的和为奇数，则肯定不行
     * 2、假定我们定义dp[i][j] 表示从0-i里面挑选出若干个数，其和等于j的可能性
     * 那么dp[i][j] = dp[i-1][j] or dp[i-1][j-nums[i]]
     *  这里要判断当nums[i]=j的时候肯定为真
     *
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        if(nums==null||nums.length==0){
            return false;
        }
        int sum = Arrays.stream(nums).sum();
        if(sum%2!=0){
            return false;
        }
        int target = sum/2;
        int length = nums.length;
        //考虑容量为0的情况,所有数字凑一个0
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

                if(nums[i]<=j){
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]];
                }
            }
        }
        boolean result = dp[length-1][target];
        return result;
    }

    public static void main(String[] args) {
        LeetCode416 leetCode416 = new LeetCode416();
        //int[] nums = new int[]{1, 5, 11, 5};
        int[] nums = new int[]{1, 2, 3, 5};
        boolean canPartition = leetCode416.canPartition(nums);

        System.out.println("canPartition="+canPartition);
    }
}

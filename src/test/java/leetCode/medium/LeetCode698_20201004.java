package leetCode.medium;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/partition-to-k-equal-sum-subsets/
 *
 * 698. 划分为k个相等的子集
 *
 * 给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
 *
 * 示例 1：
 *
 * 输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * 输出： True
 * 说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
 *  
 *
 * 提示：
 *
 * 1 <= k <= len(nums) <= 16
 * 0 < nums[i] < 10000
 *
 * @author chenwu on 2020.10.4
 */
public class LeetCode698_20201004 {

    private boolean checkCanPartitionKSubsets(int[] nums,int currentSum,int target,int start,int k,boolean[] used){
        if(k==0){
            return true;
        }
        if(currentSum>target){
            return false;
        }
        if(currentSum == target){
            return checkCanPartitionKSubsets(nums,0,target,0,k-1,used);
        }
        for(int i = start;i<nums.length;i++){
            if(!used[i] && currentSum+nums[i]<=target){
                used[i] = true;
                if(checkCanPartitionKSubsets(nums,currentSum+nums[i],target,i+1,k,used)){
                    return true;
                }
                used[i] = false;
            }
        }
        return false;
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        if(nums == null || nums.length<1){
            return false;
        }
        int sum = Arrays.stream(nums).sum();
        if(sum%k!=0){
            return false;
        }
        int target = sum/k;
        boolean[] used = new boolean[nums.length];
        boolean helpPartitionKSubsets = checkCanPartitionKSubsets(nums, 0, k, 0, target, used);
        return helpPartitionKSubsets;
    }

    public static void main(String[] args) {
        LeetCode698_20201004 leetCode698_20201004 = new LeetCode698_20201004();
        int[] nums = new int[]{4, 3, 2, 3, 5, 2, 1};
        int k = 4;
        boolean canPartitionKSubsets = leetCode698_20201004.canPartitionKSubsets(nums, k);
        System.out.println(canPartitionKSubsets);
    }
}

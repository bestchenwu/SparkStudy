package leetCode.simple;

import leetCode.TreeNode;

/**
 * https://leetcode-cn.com/problems/third-maximum-number/
 *
 * 给定一个非空数组，返回此数组中第三大的数。如果不存在，则返回数组中最大的数。要求算法时间复杂度必须是O(n)。
 *
 * 示例 1:
 *
 * 输入: [3, 2, 1]
 *
 * 输出: 1
 *
 * 解释: 第三大的数是 1.
 *
 * 示例 2:
 *
 * 输入: [1, 2]
 *
 * 输出: 2
 *
 * 解释: 第三大的数不存在, 所以返回最大的数 2 .
 *
 * 示例 3:
 *
 * 输入: [2, 2, 3, 1]
 *
 * 输出: 1
 *
 * 解释: 注意，要求返回第三大的数，是指第三大且唯一出现的数。
 * 存在两个值为2的数，它们都排第二。
 *
 */
public class LeetCode414 {

    public int thirdMax(int[] nums) {

        //a[i]>second 但是<max 那么a[i]与second互换 second = third
        //a[i]>max a[i]与max互换  max与second互换
        //a[i]>third 那么a[i]与third互换
        if(nums==null||nums.length==0){
            return -1;
        }
        if(nums.length==1){
            return nums[0];
        }
        long max =  Long.MIN_VALUE;
        long second = Long.MIN_VALUE;
        long third = Long.MIN_VALUE;
        for(int i=0;i<nums.length;i++){
            if(nums[i]>max){
                third = second;
                second = max;
                max = nums[i];
            }else if(nums[i]>second && nums[i]<max){
                third = second;
                second = nums[i];
            }else if(nums[i]>third && nums[i]<second){
                third = nums[i];
            }
        }
        return (long)third==Long.MIN_VALUE?(int)max:(int)third;
    }

    public static void main(String[] args) {
        LeetCode414 leetCode414 = new LeetCode414();
        int[] nums = new int[]{5,2,2};
        int result = leetCode414.thirdMax(nums);
        System.out.println(result);
    }

}

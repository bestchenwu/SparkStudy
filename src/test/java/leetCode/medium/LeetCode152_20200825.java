package leetCode.medium;

/**
 * https://leetcode-cn.com/problems/maximum-product-subarray/
 *
 * 152. 乘积最大子数组
 *
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 *
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 *
 */
public class LeetCode152_20200825 {

    public int maxProduct(int[] nums) {
        if(nums.length == 1){
            return nums[0];
        }
        int min = 1;
        int max = 1;
        int maxProduct = 1;
        for(int i = 0;i<nums.length;i++){
            if(nums[i]<0){
                int tmp = min;
                min = max;
                max = tmp;
            }
            min = Math.min(min*nums[i],nums[i]);
            max = Math.max(max*nums[i],nums[i]);
            maxProduct = Math.max(maxProduct,max);
        }
        return maxProduct;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,3,-2,4};
        LeetCode152_20200825 leetCode152_20200825 = new LeetCode152_20200825();
        int i = leetCode152_20200825.maxProduct(nums);
        System.out.println(i);
    }
}

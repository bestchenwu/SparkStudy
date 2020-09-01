package leetCode.medium;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/product-of-array-except-self/
 *
 * 238. 除自身以外数组的乘积
 *
 * 给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 *
 *  
 *
 * 示例:
 *
 * 输入: [1,2,3,4]
 * 输出: [24,12,8,6]
 *  
 *
 * 提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。
 *
 * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 *
 * 进阶：
 * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 *
 * @author chenwu on 2020.8.31
 */
public class LeetCode238_20200831 {

    /**
     *
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        if(nums == null || nums.length <= 1){
            return nums;
        }
        int length = nums.length;
        int[] result = new int[length];
        //1 2 3 4
        //int[] tmp = 1 1 2 6
        //要输出24,12,8,6
        //tmp2 = 1
        //result[i] = tmp2*tmp[i]
        //tmp2 * = nums[i];
        int[] tmp = new int[length];
        //先从左至右乘
        int sum = 1;
        for(int i = 0;i<length;i++){
            tmp[i] = sum;
            sum *=nums[i];
        }
        sum = 1;
        //再从右往左乘
        for(int i = length-1;i>=0;i--){
            result[i] = sum*tmp[i];
            sum*=nums[i];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4};
        LeetCode238_20200831 leetCode238_20200831 = new LeetCode238_20200831();
        int[] result = leetCode238_20200831.productExceptSelf(nums);
        System.out.println(Arrays.toString(result));
    }
}

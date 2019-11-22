package leetCode.medium;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/product-of-array-except-self/
 *
 * 给定长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 *
 * 示例:
 *
 * 输入: [1,2,3,4]
 * 输出: [24,12,8,6]
 *
 * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 * 进阶：
 * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 */
public class LeetCode238 {

    @Deprecated
    public int[] productExceptSelf0(int[] nums) {
        int[] output = new int[nums.length];
        for(int i = 0;i<nums.length;i++){
            int sum = 1;
            for(int j = 0;j<nums.length;j++){
                if(j==i){
                    continue;
                }
                sum*=nums[j];
            }
            output[i]=sum;
        }
        return output;
    }

    /**
     * 左积和右积
     * int[] output = new int[nums.length]
     * 初始化k = 1;
     * 从左往右遍历
     * 每个位置保存左侧的所有元素的乘积
     * output[i] = k
     * k*=output[i]
     * 这样第一遍遍历后，形成:
     * 1 1 2 6  output[]
     * 第二遍从右往左遍历
     * 重置K=1
     * output[i]*=k
     * k*=nums[i]
     *  24 12  8  6
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int[] output = new int[nums.length];
        int k = 1;
        for(int i=0;i<nums.length;i++){
            output[i]=k;
            k*=nums[i];
        }
        k = 1;
        for(int j=nums.length-1;j>=0;j--){
            output[j]=k*output[j];
            k*=nums[j];
        }
        return output;
    }

    public static void main(String[] args) {
        LeetCode238 leetCode238 = new LeetCode238();
        int[] nums = new int[]{1,2,3,4};
        int[] result = leetCode238.productExceptSelf(nums);
        System.out.println(Arrays.toString(result));
    }
}

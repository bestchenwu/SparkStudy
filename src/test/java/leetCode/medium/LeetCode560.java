package leetCode.medium;

import java.util.Arrays;

/**
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 *
 * 示例 1 :
 *
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 * 说明 :
 *
 * 数组的长度为 [1, 20,000]。
 * 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
 *
 */
public class LeetCode560 {

    /**
     * sum(i,j)=sum(0,j)-sum(0,i) sum(i,j)表示从第i+1加到第j-1的元素之和
     *
     * 假定dp[x] 得到了这样一个数组(数组的每一项等于第j个元素到第0个元素的和),那么问题简化为只要两两相减=k，那么就得到了一个count
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int length = nums.length;
        int dp[]= new int[length];
        for(int i=0;i<nums.length;i++){
            dp[i] = (i==0)?nums[i]:dp[i-1]+nums[i];
        }
        int count = 0;
        for(int dpLength:dp){
            if(dpLength==k){
                count+=1;
            }
        }
        int startIndex = 0;
        for(int i = startIndex;i<length;i++){
            for(int j=i+1;j<length;j++){
                if(dp[j]-dp[i]==k){
                    count+=1;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        LeetCode560 leetCode560 = new LeetCode560();
        int[] nums = new int[]{1,3,5,8,2};
        int k = 2;
        int count = leetCode560.subarraySum(nums,k);
        System.out.println(count);
    }
}

package leetCode;

/**
 * https://leetcode-cn.com/problems/maximum-subarray/submissions/
 */
public class LeetCode53 {

    public int maxSubArray(int[] nums) {
        if(nums==null||nums.length==0){
            return 0;
        }
        if(nums.length==1){
            return nums[0];
        }
        int result = Integer.MIN_VALUE;
        int sum = 0;
        for(int index =0;index<nums.length;index++){
            if(sum>=0){
                sum+=nums[index];
            }else{
                sum = nums[index];
            }
            result = Math.max(result,sum);
        }
        return result;
    }

    public static void main(String[] args){
        LeetCode53 leetCode53 = new LeetCode53();
        int[] nums = new int[]{-2,-1};
        int result = leetCode53.maxSubArray(nums);
        System.out.println(result);
    }
}

package leetCode.simple;

/**
 * https://leetcode-cn.com/problems/missing-number/
 *
 * 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数
 */
public class LeetCode268 {

    public int missingNumber(int[] nums) {
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
            max = Math.max(max,nums[i]);
        }
        if(max!=nums.length){
            return nums.length;
        }
        int needSum = (1+max)*(max)/2;
        return needSum-sum;

    }

    public static void main(String[] args) {
       int[] nums = new int[]{9,6,4,2,3,5,7,0,1};
        LeetCode268 leetCode268 = new LeetCode268();
        int result = leetCode268.missingNumber(nums);
        System.out.println(result);
    }
}

package leetCode;

/**
 * https://leetcode-cn.com/problems/house-robber/
 */
public class LeetCode198 {

    public int rob(int[] nums) {
        if(nums==null||nums.length==0){
            return 0;
        }
        int oddSum = 0;
        int evenSum = 0;
        for(int i = 0;i<nums.length;i++){
            if(i%2==0){
                //偶数
                evenSum+=nums[i];
                if(evenSum<oddSum){
                    evenSum = oddSum;
                }
            }else{
                //奇数
                oddSum+=nums[i];
                if(oddSum<evenSum){
                    oddSum = evenSum;
                }
            }
        }
        int result = Math.max(evenSum,oddSum);
        return result;
    }

    public static void main(String[] args){
        LeetCode198 leetCode198 = new LeetCode198();
        int[] nums = new int[]{2,7,9,3,1};
        int result = leetCode198.rob(nums);
        System.out.println(result);
    }
}

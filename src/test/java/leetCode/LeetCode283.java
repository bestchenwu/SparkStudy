package leetCode;

/**
 * https://leetcode-cn.com/problems/move-zeroes/
 */
public class LeetCode283 {

    public void moveZeroes(int[] nums) {
        if(nums==null||nums.length==0){
            return;
        }
        int lastNonZero = 0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=0){
                nums[lastNonZero++]=nums[i];
            }
        }
        for(int i=lastNonZero;i<nums.length;i++){
            nums[i]=0;
        }
    }

    public static void main(String[] args){
        LeetCode283 leetCode283 = new LeetCode283();
        int[] nums = new int[]{0,1,0,3,12};
        leetCode283.moveZeroes(nums);
    }
}

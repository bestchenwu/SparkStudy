package leetCode;

/**
 * 最短无序连续子数组
 *
 * https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/
 */
public class LeetCode581 {

    public int findUnsortedSubarray(int[] nums) {
        if(nums==null||nums.length<=1) {
            return 0;
        }
        int left =nums.length-1;
        int right = 0;
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[j]<nums[i]){
                    left = Math.min(i,left);
                    right = Math.max(j,right);
                }
            }
        }
        return right-left>0?right-left+1:0;
    }

    public static void main(String[] args) {
        LeetCode581 leetCode581 = new LeetCode581();
        int[] nums=new int[]{2, 6, 4, 8, 10, 9, 15};
        int result = leetCode581.findUnsortedSubarray(nums);
        System.out.println(result);
    }
}

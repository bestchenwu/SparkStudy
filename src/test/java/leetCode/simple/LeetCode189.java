package leetCode.simple;

/**
 * https://leetcode-cn.com/problems/rotate-array/
 *
 * 右移若干位
 */
public class LeetCode189 {

    public void rotate(int[] nums, int k) {
        if(nums==null||nums.length==0){
            return;
        }
        int temp;
        int length = nums.length-1;
        int j = length;
        for(int i=1;i<=k;i++){
            temp = nums[length];
            while(j>=1){
                nums[j]=nums[j-1];
                j--;
            }
            nums[0]=temp;
            j = length;
        }
    }
    public static void main(String[] args) {
        LeetCode189 leetCode189 = new LeetCode189();
        int[] nums = new int[]{1,2,3,4,5,6,7};
        leetCode189.rotate(nums,3);
    }
}

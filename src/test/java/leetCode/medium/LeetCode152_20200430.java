package leetCode.medium;

/**
 * https://leetcode-cn.com/problems/maximum-product-subarray/
 */
public class LeetCode152_20200430 {

    public int maxProduct(int[] nums) {
        if(nums==null || nums.length==0){
            return 0;
        }
        if(nums.length==1){
            return nums[0];
        }
        int min = nums[0];
        int max = nums[0];
        int maxProduct = Integer.MIN_VALUE;
        for(int i = 1;i<nums.length;i++){
            if(nums[i]>0){
                max = max*nums[i];
                min = nums[i];
            }else if(nums[i]<0){
                int tmp = max*nums[i];
                max = min<0?min*nums[i]:nums[i];
                min = tmp;
            }else{
                max = 0;
                min = 0;
            }
            maxProduct = Math.max(max,maxProduct);
        }
        return maxProduct;
    }

    public static void main(String[] args) {
        LeetCode152_20200430 leetCode152_20200430 = new LeetCode152_20200430();
        //int[] nums = new int[]{2,3,-2,4};
        int[] nums = new int[]{-2,0,1};
        //int[] nums = new int[]{2,3,-2,-4};
        int i = leetCode152_20200430.maxProduct(nums);
        System.out.println("i="+i);
    }
}

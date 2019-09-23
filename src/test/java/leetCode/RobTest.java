package leetCode;

/**
 * https://leetcode-cn.com/problems/house-robber/
 *
 * @author  chenwu on 2019.9.23
 */
public class RobTest {

    public int maxSum(int[] nums,int firstIndex){

        if(nums==null){
            return 0;
        }
        int length = nums.length-1;

        if(firstIndex==length){
            return nums[firstIndex];
        }
        if(length-firstIndex==1){
            return Math.max(nums[firstIndex],nums[length]);
        }
        if(length-firstIndex==2){
            return Math.max(nums[firstIndex]+nums[firstIndex+2],nums[firstIndex+1]);
        }
        int sum = 0;
        for(int i=firstIndex;i<nums.length;i++){
            for(int j=i+2;j<nums.length;j++){
                sum = Math.max(sum,nums[i]+maxSum(nums,j));
            }
        }
        return sum;
    }

    public int rob(int[] nums) {
        return maxSum(nums,0);
    }

    public static void main(String[] args){
        int[] nums = new int[]{2,7,9,3,1};
        RobTest test = new RobTest();
        System.out.println(test.rob(nums));
    }
}

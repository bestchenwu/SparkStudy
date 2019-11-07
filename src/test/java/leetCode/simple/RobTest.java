package leetCode.simple;

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

    public int rob1(int[] nums){
        if(nums==null||nums.length==0){
            return 0;
        }
        int sumOdd = 0,sumEven = 0;
        for(int i=0;i<nums.length;i++){
            if(i%2==0){
                //is a odd number
                sumOdd += nums[i];
                sumOdd = Math.max(sumOdd,sumEven);
            }else{
                sumEven +=nums[i];
                sumEven = Math.max(sumEven,sumOdd);
            }
        }
        return Math.max(sumOdd,sumEven);
    }

    public static void main(String[] args){
        int[] nums = new int[]{2,7,9,3,1};
        RobTest test = new RobTest();
        //System.out.println(test.rob(nums));
        System.out.println(test.rob1(nums));
    }
}

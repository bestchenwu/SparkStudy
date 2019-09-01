package leetCode;

import java.util.Random;

public class Solutions2 {

    Random random = new Random(47);


    public int calSum(int[] nums,int begin,int end){
        int sum = 0;
        for(int index=begin;index<=end;index++){
            sum+=nums[index];
        }
        return sum;
    }

    public int maxSubArray(int[] nums) {
        if(nums==null || nums.length == 0){
            return 0;
        }
        int begin = 0;
        int maxEnd = nums.length-1;
        int beginEnd = begin;
        int maxSum = nums[0];
        while(begin<=maxEnd){
            beginEnd = begin;
            while(beginEnd<=maxEnd){
                int sum1 = calSum(nums,begin,beginEnd);
                maxSum =(maxSum>=sum1)?maxSum:sum1;
                beginEnd +=1;
            }
            begin = begin+1;
        }
        return maxSum;
    }

    public static void main(String[] args){
        int[] nums = new int[]{-1};
        Solutions2 Solutions2 = new Solutions2();
        System.out.println(Solutions2.maxSubArray(nums));
    }
}

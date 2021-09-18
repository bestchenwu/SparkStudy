package leetCode.medium;

import org.junit.Test;

import java.util.Arrays;

public class LeetCode16_20210918 {

    public int threeSumClosest(int[] nums, int target) {
        int length = nums.length-1;
        Integer res = null;
        Arrays.sort(nums);
        for(int i = 0;i<=length;i++){
            int j = i+1;
            int k = length;
            while(j<k){
                int res0 = nums[i]+nums[j]+nums[k];
                if(res0 == target){
                    return res0;
                }
                if(res == null){
                    res = res0;
                }else{
                    if(Math.abs(res0-target)<Math.abs(res-target)){
                        res = res0;
                    }
                }
                if(res0<target){
                    j+=1;
                }else{
                    k-=1;
                }
            }
        }
        return res;
    }

    @Test
    public void testThreeSumClosest(){
        int[] nums = new int[]{0,0,0};
        int target = 1;
        int res = threeSumClosest(nums, target);
        System.out.println("res="+res);
    }
}

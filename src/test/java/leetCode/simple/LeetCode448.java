package leetCode.simple;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array/submissions/
 */
public class LeetCode448 {

    public void sort(int[] nums){
        int temp = 0;
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]>nums[j]){
                    temp = nums[i];
                    nums[i]=nums[j];
                    nums[j] = temp;
                }
            }
        }
    }

    public List<Integer> findDisappearedNumbers0(int[] nums) {
        List<Integer> list = new ArrayList<Integer>();
        if(nums==null||nums.length==0){
            return list;
        }
        int value = 0;
        for(int i=0;i<nums.length;i++){
            value = Math.abs(nums[i]);
            if(nums[value-1]>0){
                nums[value-1]=Math.negateExact(nums[value-1]);
            }
        }
        for(int j=0;j<nums.length;j++){
            if(nums[j]>0){
                list.add(j+1);
            }
        }
        return list;

    }


    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        if(nums==null || nums.length == 0){
            return list;
        }
        for(int i = 0;i<nums.length;i++){
            int index = Math.abs(nums[i]);
            if(nums[index-1]>0){
                nums[index-1] = -nums[index-1];
            }
        }
        for(int j = 0;j<nums.length;j++){
            if(nums[j]>0){
                list.add(j+1);
            }
        }
        return list;
    }

    public static void main(String[] args){
        LeetCode448 leetCode448 = new LeetCode448();
        //int[] nums = new int[]{5,4,6,7,9,3,10,9,5,6};
        int[] nums = new int[]{4,3,2,7,8,2,3,1};
        List<Integer> disappearedNumbers = leetCode448.findDisappearedNumbers(nums);
        System.out.println(disappearedNumbers);
    }
}

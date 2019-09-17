package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array/solution/
 */
public class findDisappearedNumbersTest {

    public void sortNums(int[] nums){
        int temp;
        for(int i=0;i<nums.length;i++){
            for(int j=i;j<nums.length;j++){
                if(nums[i]>nums[j]){
                    temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        if(nums==null||nums.length==0){
            return new ArrayList<Integer>();
        }
        sortNums(nums);
        int numsIndex = 0;
        List<Integer> list = new ArrayList<Integer>();
        int index = 1;
        while(index<=nums.length){
            if(nums[numsIndex]==index){
                numsIndex+=1;
                if(numsIndex>=nums.length){
                    break;
                }else{
                    continue;
                }
            }else{
                list.add(index);
                index = index+1;
            }

        }
        if(index<=nums.length){
            for(int j= index;j<=nums.length;j++){
                list.add(j);
            }
        }
        return list;
    }

    public static void main(String[] args){
        findDisappearedNumbersTest test = new findDisappearedNumbersTest();
        int[] nums = new int[]{1,1};
        List<Integer> result = test.findDisappearedNumbers(nums);
        System.out.println(result);
    }
}

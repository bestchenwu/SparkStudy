package leetCode.simple;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        Set<Integer> integer = new HashSet<Integer>();
        List<Integer> list = new ArrayList<Integer>();
        for(int i=0;i<nums.length;i++){
            integer.add(i);
        }
        for(int j=1;j<=nums.length;j++){
            if(!integer.contains(j)){
                list.add(j);
            }
        }
        return list;
    }

    public static void main(String[] args){
        findDisappearedNumbersTest test = new findDisappearedNumbersTest();
        int[] nums = new int[]{2,3,1,3,4};
        List<Integer> result = test.findDisappearedNumbers(nums);
        System.out.println(result);
    }
}

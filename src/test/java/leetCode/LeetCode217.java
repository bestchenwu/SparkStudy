package leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/contains-duplicate/
 *
 * 判断数组是否出现重复元素
 */
public class LeetCode217 {

    public boolean containsDuplicate(int[] nums) {
        if(nums==null||nums.length<=1){
            return false;
        }
        Map<Integer,String> map = new HashMap<Integer,String>();
        for(int i=0;i<nums.length;i++){
            if(map.get(nums[i])!=null){
                return true;
            }else{
                map.put(Integer.valueOf(nums[i]),"");
            }

        }
        return false;
    }

    public static void main(String[] args) {
        LeetCode217 leetCode217 = new LeetCode217();
        int[] nums = new int[]{1,2,3,1};
        boolean result = leetCode217.containsDuplicate(nums);
        System.out.println(result);
    }
}

package nowCode.simple;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/he-wei-sde-liang-ge-shu-zi-lcof/
 *
 * 剑指 Offer 57. 和为s的两个数字
 *
 * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[2,7] 或者 [7,2]
 * 示例 2：
 *
 * 输入：nums = [10,26,30,31,47,60], target = 40
 * 输出：[10,30] 或者 [30,10]
 */
public class NowCode57 {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> numMap = new HashMap<>();
        for(int i : nums){
            if(numMap.containsKey(i)){
                return new int[]{i,numMap.get(i)};
            }else{
                numMap.put(target-i,i);
            }
        }
        return new int[]{-1,-1};
    }

    public static void main(String[] args) {
        NowCode57 nowCode57 = new NowCode57();
        int[] nums = new int[]{2,7,11,15};
        int target = 9;
        int[] res = nowCode57.twoSum(nums,target);
        System.out.println(Arrays.toString(res));
    }
}

package nowCode.medium;


import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

//给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
//
// 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
//
//
//
// 示例 1：
//
//
//输入：nums = [100,4,200,1,3,2]
//输出：4
//解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
//
// 示例 2：
//
//
//输入：nums = [0,3,7,2,5,8,4,6,0,1]
//输出：9
//
//
//
//
// 提示：
//
//
// 0 <= nums.length <= 10⁵
// -10⁹ <= nums[i] <= 10⁹
public class LeetCode128 {

    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for(int num : nums){
            numSet.add(num);
        }
        int longStreak = 0;
        int currentNum = 0;
        int currentLongStreak = 0;
        for(int num : nums){
            if(!numSet.contains(num-1)){
                 currentNum = num;
                 currentLongStreak = 1;
                while(numSet.contains(currentNum+1)){
                    currentNum+=1;
                    currentLongStreak+=1;
                }
                longStreak = Math.max(longStreak,currentLongStreak);
            }
        }
        return longStreak;
    }

    @Test
    public void testLongestConsecutive(){
        int[] nums = new int[]{0,3,7,2,5,8,4,6,0,1};
        int longSteak = longestConsecutive(nums);
        Assert.assertEquals(9,longSteak);
    }
}

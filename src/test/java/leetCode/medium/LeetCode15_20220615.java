package leetCode.medium;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/3sum/
 *
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 */
public class LeetCode15_20220615 {

    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if(nums == null || nums.length<3){
            return list;
        }
        Arrays.sort(nums);
        for(int i = 0;i<nums.length;i++){
            if(nums[i]>0){
                break;
            }else if(i>0 && nums[i]==nums[i-1]){
                continue;
            }
            int j = i+1,k = nums.length-1;
            while(j<k){
                if(nums[i]+nums[j]+nums[k]==0){
                    list.add(Arrays.asList(nums[i],nums[j],nums[k]));
                    while(j<k && nums[j]==nums[j+1]){
                        j+=1;
                    }
                    while(k>j && nums[k]==nums[k-1]){
                        k-=1;
                    }
                    j+=1;
                    k-=1;
                }else if(nums[i]+nums[j]+nums[k]<0){
                    j+=1;
                }else{
                    k-=1;
                }
            }
        }
        return list;
    }

    @Test
    public void testThreeSum2(){
        int[] nums = new int[]{0,0,0};
        List<List<Integer>> list = threeSum2(nums);
        System.out.println(list);
    }
}

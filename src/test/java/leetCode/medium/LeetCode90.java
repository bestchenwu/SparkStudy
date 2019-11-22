package leetCode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: [1,2,2]
 * 输出:
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 *
 */
public class LeetCode90 {

    private void purge(int i , int[] nums, Stack<Integer> path,List<List<Integer>> res){
        List<Integer> list = new ArrayList<>(path);
        Collections.sort(list);
        if(!res.contains(list)){
            res.add(list);
        }
        for(;i<nums.length;i++){
            path.push(nums[i]);
            purge(i+1,nums,path,res);
            path.pop();
        }
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Stack<Integer> path = new Stack<>();
        List<List<Integer>> res = new ArrayList<>();
        purge(0,nums,path,res);
        return res;
    }

    public static void main(String[] args) {
        LeetCode90 leetCode90 = new LeetCode90();
        //int[] nums = new int[]{1,2,2};
        int[] nums = new int[]{4,4,4,1,4};
        List<List<Integer>> lists = leetCode90.subsetsWithDup(nums);
        //预期[[],[1],[1,4],[1,4,4],[1,4,4,4],[1,4,4,4,4],[4],[4,4],[4,4,4],[4,4,4,4]]
        System.out.println(lists);
    }
}

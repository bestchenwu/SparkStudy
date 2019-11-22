package leetCode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/subsets/
 * <p>
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * <p>
 * 说明：解集不能包含重复的子集。
 * <p>
 * 示例:
 * <p>
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 * [3],
 * [1],
 * [2],
 * [1,2,3],
 * [1,3],
 * [2,3],
 * [1,2],
 * []
 * ]
 */
public class LeetCode78 {

    @Deprecated
    private void purge0(int[] nums, boolean[] visited, Stack<Integer> path, List<List<Integer>> results, int size) {
        if (path.size() == size) {
            List<Integer> list = new ArrayList<Integer>();
            list.addAll(path);
            Collections.sort(list);
            if (!results.contains(list)) {
                results.add(list);
            }
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                int num = nums[i];
                path.add(num);
                visited[i] = true;
                purge0(nums, visited, path, results, size);
                path.pop();
                visited[i] = false;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                int num = nums[i];
                path.add(num);
                visited[i] = true;
                purge0(nums, visited, path, results, size);
                path.pop();
                visited[i] = false;
            }
        }
    }

    //超出时间限制
    @Deprecated
    public List<List<Integer>> subsets0(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        Stack<Integer> path = new Stack<>();
        List<List<Integer>> results = new ArrayList<>();
        results.add(new ArrayList<>());
        for (int size = 1; size <= nums.length; size++) {
            purge0(nums, visited, path, results, size);
            visited = new boolean[nums.length];
            path = new Stack<>();
        }

        return results;
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Stack<Integer> path = new Stack<>();
        purge(0,nums,path,res);
        return res;
    }


    /**
     * ()
     * i =0  i+1 = 1
     * 1
     * 1 2
     * 1 2 3
     * 回到path = 1 i+1=1
     * i++  i=>2
     * 1 3
     * path pop两次
     *
     *
     *
     * @param i
     * @param nums
     * @param path
     * @param res
     */
    private void purge(int i,int[] nums,Stack<Integer> path,List<List<Integer>> res){
        List<Integer> list = new ArrayList<>();
        list.addAll(path);
        res.add(list);
        for(;i<nums.length;i++){
            path.push(nums[i]);
            purge(i+1,nums,path,res);
            path.pop();
        }
    }


    public static void main(String[] args) {
        LeetCode78 leetCode78 = new LeetCode78();
        int[] nums = new int[]{1, 2, 3};
        List<List<Integer>> lists = leetCode78.subsets(nums);
        System.out.println(lists);
    }
}

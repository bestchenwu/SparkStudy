package leetCode.medium;


import java.util.*;

/**
 * https://leetcode-cn.com/problems/permutations/
 *
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。<br/>
 * 解题思路:
 * 1、取出数组某个元素,剩下的元素返回List<List<Integer>>
 *
 * @author chenwu on 2019.11.1
 */
public class LeetCode46 {

    /**
     * 回溯算法
     *
     * @param nums 数组
     * @param isVisited 某个数组下标是否被访问过
     * @param path 已经访问过的数组路径
     * @param result 最终结果
     *
     * @author chenwu on 2019.11.1
     */
   private void generatePermuation(int[] nums, boolean[] isVisited, Stack<Integer> path,List<List<Integer>> result){
       if(path.size()==nums.length){
           List<Integer> list0 = new ArrayList<>();
           list0.addAll(path);
           result.add(list0);
           return;
       }
       for(int i=0;i<nums.length;i++){
            if(!isVisited[i]){
                path.add(nums[i]);
                isVisited[i]=true;
                generatePermuation(nums,isVisited,path,result);
                isVisited[i]=false;
                path.pop();
            }
       }
   }

    public List<List<Integer>> permute(int[] nums) {
        if(nums==null||nums.length==0){
            return Arrays.asList(new ArrayList<Integer>());
        }
        if(nums.length==1){
            return Arrays.asList(Arrays.asList(nums[0]));
        }
        boolean[] isVisited = new boolean[nums.length];
        Stack<Integer> stack = new Stack<>();
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        generatePermuation(nums,isVisited,stack,res);
        return res;
    }

    public static void main(String[] args) {
        LeetCode46 leetCode46 = new LeetCode46();
        int[] nums = new int[]{1,2,3};
        List<List<Integer>> lists = leetCode46.permute(nums);
        System.out.println(lists);
    }
}

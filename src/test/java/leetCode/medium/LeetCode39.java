package leetCode.medium;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/combination-sum/
 * <p>
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的数字可以无限制重复被选取。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 * <p>
 * 输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 * [7],
 * [2,2,3]
 * ]
 * 示例 2:
 * <p>
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 */
public class LeetCode39 {

    private void combinationSumHelp(int[] candidates, int target, Stack<Integer> path, List<List<Integer>> result) {
        //计算stack的总和
        int sum = path.stream().mapToInt(item -> item.intValue()).sum();
        if (sum >= target) {
            if (sum == target) {
                List<Integer> validList = new ArrayList<>(path);
                validList.sort((x,y)->x.compareTo(y));
                if(!result.contains(validList)){
                    result.add(validList);
                }
            }
            return;
        }
        for (int i = 0; i < candidates.length; i++) {
            int currentValue = candidates[i];
            path.push(currentValue);
            combinationSumHelp(candidates, target, path, result);
            path.pop();
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        Stack<Integer> path = new Stack<>();
        List<List<Integer>> result = new ArrayList<>();
        combinationSumHelp(candidates, target, path, result);
        return result;
    }

    public static void main(String[] args) {
        LeetCode39 leetCode39 = new LeetCode39();
        int[] candidates = new int[]{2,3,5};
        int target =8;
        List<List<Integer>> result = leetCode39.combinationSum(candidates, target);
        System.out.println(result);
    }
}

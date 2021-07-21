package leetCode.medium;

import leetCode.TreeNode;
import org.junit.Test;

import java.util.*;
//给你一个二叉树的根结点，请你找出出现次数最多的子树元素和。一个结点的「子树元素和」定义为以该结点为根的二叉树上所有结点的元素之和（包括结点本身）。
//
// 你需要返回出现次数最多的子树元素和。如果有多个元素出现的次数相同，返回所有出现次数最多的子树元素和（不限顺序）。
//
//
//
// 示例 1：
//输入:
//
//   5
// /  \
//2   -3
//
//
// 返回 [2, -3, 4]，所有的值均只出现一次，以任意顺序返回所有值。
//
// 示例 2：
//输入：
//
//   5
// /  \
//2   -5
//
//
// 返回 [2]，只有 2 出现两次，-5 只出现 1 次。
public class LeetCode508 {

    private int maxCount = 0;

    public int[] findFrequentTreeSum(TreeNode root) {
        if(root == null){
            return new int[0];
        }
        Map<Integer,Integer> map = new HashMap<>();
        helpSum(root,map );
        List<Integer> res = new ArrayList<>();
        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            Integer sum = entry.getKey();
            Integer count = entry.getValue();
            if(count.intValue() == maxCount){
                res.add(sum);
            }
        }
        int[] result = new int[res.size()];
        for(int i = 0;i<res.size();i++){
            result[i] = res.get(i);
        }
        return result;
    }

    private int helpSum(TreeNode root,Map<Integer,Integer> map){
        if(root == null){
            return 0;
        }
        int sum = root.val+helpSum(root.left,map )+helpSum(root.right,map);
        Integer count = map.getOrDefault(sum, 0);
        count+=1;
        map.put(sum, count);
        maxCount = Math.max(maxCount,count );
        return sum;
    }

    @Test
    public void helpFindFrequentTreeSum(){
        TreeNode root = new TreeNode(5, new TreeNode(2), new TreeNode(-3));
        int[] array = findFrequentTreeSum(root);
        System.out.println("array:"+Arrays.toString(array));
    }
}

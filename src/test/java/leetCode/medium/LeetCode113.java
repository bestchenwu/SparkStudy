package leetCode.medium;

import leetCode.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
//
// 叶子节点 是指没有子节点的节点。
//
//
//
//
//
// 示例 1：
//
//
//输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
//输出：[[5,4,11,2],[5,8,4,5]]
//
//
// 示例 2：
//
//
//输入：root = [1,2,3], targetSum = 5
//输出：[]
//
//
// 示例 3：
//
//
//输入：root = [1,2], targetSum = 0
//输出：[]
//
//
//
//
// 提示：
//
//
// 树中节点总数在范围 [0, 5000] 内
// -1000 <= Node.val <= 1000
// -1000 <= targetSum <= 1000
public class LeetCode113 {

    private void helpPathSum(TreeNode root, int targetSum, Stack<Integer> path, List<List<Integer>> result) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            path.push(root.val);
            int sum = path.stream().reduce(0, (a, b) -> a + b);
            if (targetSum == sum) {
                List<Integer> list = new ArrayList<>(path);
                result.add(list);
            }
            path.pop();
        } else {
            path.push(root.val);
            helpPathSum(root.left, targetSum, path, result);
            helpPathSum(root.right, targetSum, path, result);
            path.pop();
        }
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        Stack<Integer> path = new Stack<>();
        List<List<Integer>> result = new ArrayList<>();
        helpPathSum(root, targetSum, path, result);
        return result;
    }

    @Test
    public void testPathSum() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4, new TreeNode(11, new TreeNode(7), new TreeNode(2)), null);
        root.right = new TreeNode(8, new TreeNode(13), new TreeNode(4, new TreeNode(5), new TreeNode(1)));
        int targetSum = 22;
        List<List<Integer>> result = pathSum(root, targetSum);
        System.out.println("result=" + result);
    }
}

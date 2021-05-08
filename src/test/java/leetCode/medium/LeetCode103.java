package leetCode.medium;

import leetCode.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

//给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
//
// 例如：
//给定二叉树 [3,9,20,null,null,15,7],
//
//
//    3
//   / \
//  9  20
//    /  \
//   15   7
//
//
// 返回锯齿形层序遍历如下：
//
//
//[
//  [3],
//  [20,9],
//  [15,7]
//]
//
public class LeetCode103 {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (root == null) {
            return resultList;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int depth = 1;
        while (!stack.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            List<TreeNode> nodeList = new ArrayList<>();
            while (!stack.isEmpty()) {
                TreeNode tmp = stack.pop();
                if (tmp.left != null) {
                    nodeList.add(tmp.left);
                }
                if (tmp.right != null) {
                    nodeList.add(tmp.right);
                }
                list.add(tmp.val);
            }
            if (depth % 2 == 0) {
                Collections.reverse(list);
            }
            resultList.add(list);
            Collections.reverse(nodeList);
            for (TreeNode treeNode : nodeList) {
                stack.push(treeNode);
            }
            depth += 1;
        }
        return resultList;
    }

    @Test
    public void testZigzagLevelOrder() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20, new TreeNode(15), new TreeNode(7));
        List<List<Integer>> resultList = zigzagLevelOrder(root);
        System.out.println("resultList=" + resultList);
    }
}

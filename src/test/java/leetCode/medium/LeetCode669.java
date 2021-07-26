package leetCode.medium;

import leetCode.TreeNode;
import org.junit.Test;

//给你二叉搜索树的根节点 root ，同时给定最小边界low 和最大边界 high。通过修剪二叉搜索树，使得所有节点的值在[low, high]中。修剪树不应
////该改变保留在树中的元素的相对结构（即，如果没有被移除，原有的父代子代关系都应当保留）。 可以证明，存在唯一的答案。
////
//// 所以结果应当返回修剪好的二叉搜索树的新的根节点。注意，根节点可能会根据给定的边界发生改变。
public class LeetCode669 {

    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        if (root.val < low) {
            return trimBST(root.right, low, high);
        } else if (root.val > high) {
            return trimBST(root.left, low, high);
        } else {
            root.left = trimBST(root.left, low, Math.min(high, root.val));
            root.right = trimBST(root.right, Math.max(root.val, low), high);
            return root;
        }
    }

    @Test
    public void testTrimBST() {
        //TreeNode root = new TreeNode(1, 0, 2);
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(0, null, new TreeNode(2,1 ,null ));
        root.right = new TreeNode(4);
        TreeNode newRoot = trimBST(root, 1, 3);
        System.out.println("newRoot=" + newRoot);
    }
}

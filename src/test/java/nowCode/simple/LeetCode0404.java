package nowCode.simple;

import leetCode.TreeNode;

//实现一个函数，检查二叉树是否平衡。在这个问题中，平衡树的定义如下：任意一个节点，其两棵子树的高度差不超过 1。
public class LeetCode0404 {

    private boolean isBalancedResult = true;

    private int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getDepth(root.left);
        if (left == -1) {
            return -1;
        }
        int right = getDepth(root.right);
        if (right == -1) {
            return -1;
        }
        if (Math.abs(left - right) > 1) {
            isBalancedResult = false;
            return -1;
        }
        return 1 + Math.max(left, right);
    }

    public boolean isBalanced(TreeNode root) {
        getDepth(root);
        return isBalancedResult;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20,new TreeNode(15),new TreeNode(7));
        LeetCode0404 leetCode0404 = new LeetCode0404();
        boolean balanced = leetCode0404.isBalanced(root);
        System.out.println("balanced=" + balanced);
    }

}

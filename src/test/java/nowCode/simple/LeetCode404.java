package nowCode.simple;

import leetCode.TreeNode;

//计算给定二叉树的所有左叶子之和。
//
// 示例：
//
//
//    3
//   / \
//  9  20
//    /  \
//   15   7
//
//在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
public class LeetCode404 {

    private int sum = 0;

    private void helpSumOfLeftLeaves(TreeNode root){
        if(root == null){
            return;
        }
        TreeNode leftNode = root.left;
        TreeNode rightNode = root.right;
        if(leftNode == null){
            return;
        }
        if(leftNode.left==null && leftNode.right == null){
            sum+=leftNode.val;
        }else{
            helpSumOfLeftLeaves(leftNode);
        }
        helpSumOfLeftLeaves(rightNode);
    }

    public int sumOfLeftLeaves(TreeNode root) {
        helpSumOfLeftLeaves(root);
        return sum;
    }

    public static void main(String[] args) {
        LeetCode404 leetCode404 = new LeetCode404();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20,new TreeNode(15),new TreeNode(7));
        int sum = leetCode404.sumOfLeftLeaves(root);
        System.out.println("sum="+sum);
    }
}

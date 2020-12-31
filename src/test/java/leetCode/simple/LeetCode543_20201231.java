package leetCode.simple;

import leetCode.TreeNode;

//给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
//
//
//
// 示例 :
//给定二叉树
//
//           1
//         / \
//        2   3
//       / \
//      4   5
//
//
// 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
//
//
//
// 注意：两结点之间的路径长度是以它们之间边的数目表示。
// Related Topics 树
// 👍 579 👎 0

/**
 * 二叉树的直径
 *
 * @author chenwu on 2020.12.31
 */
public class LeetCode543_20201231 {

    private int getDepth(TreeNode root){
        if(root == null){
            return 0;
        }
        return Math.max(1+getDepth(root.left),1+getDepth(root.right));
    }

    private int helpDiameterOfBinaryTree(TreeNode root){
        if(root == null){
            return 0;
        }
        int rootDiameter = getDepth(root.left)+getDepth(root.right);
        int leftDiameter = helpDiameterOfBinaryTree(root.left);
        int rightDiameter = helpDiameterOfBinaryTree(root.right);
        return Math.max(rootDiameter,Math.max(leftDiameter,rightDiameter));
    }

    public int diameterOfBinaryTree(TreeNode root) {
        int maxDiameter = helpDiameterOfBinaryTree(root);
        return maxDiameter;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2,new TreeNode(4),new TreeNode(5));
        root.right = new TreeNode(3);
        LeetCode543_20201231 leetCode543_20201231 = new LeetCode543_20201231();
        int result = leetCode543_20201231.diameterOfBinaryTree(root);
        System.out.println("result="+result);
    }

}

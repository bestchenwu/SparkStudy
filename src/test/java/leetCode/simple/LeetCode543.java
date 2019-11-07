package leetCode.simple;

import leetCode.TreeNode;

/**
 * https://leetcode-cn.com/problems/diameter-of-binary-tree/
 *
 * 计算树的最大直径
 */
public class LeetCode543 {

    /**
     * 找出树的深度
     *
     * @param root
     * @return
     */
    public int findTreeDepth(TreeNode root){
        if(root ==null){
            return 0;
        }
        return Math.max(1+findTreeDepth(root.left),1+findTreeDepth(root.right));
    }

    /**
     * 找出树的最大直径
     *
     * @param root
     * @return
     */
    public int findMaxLength(TreeNode root){
        if(root ==null){
            return 0;
        }
        int maxLength = findTreeDepth(root.left)+findTreeDepth(root.right);
        return maxLength;
    }

    /**
     * 对每个节点循环调用
     *
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        if(root ==null){
            return 0;
        }
        int rootLength = findMaxLength(root);
        int leftLength = diameterOfBinaryTree(root.left);
        int rightLength = diameterOfBinaryTree(root.right);
        return Math.max(Math.max(rootLength,leftLength),rightLength);
    }

    public static void main(String[] args){
        LeetCode543 leetCode543 = new LeetCode543();
        TreeNode treeNode = new TreeNode(1,new TreeNode(2,new TreeNode(4),new TreeNode(5)),new TreeNode(3));
        int res = leetCode543.diameterOfBinaryTree(treeNode);
        System.out.println(res);
    }
}


package leetCode.medium;

import leetCode.TreeNode;

/**
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 */
public class LeetCode236_20200429 {

    private TreeNode result;

    private boolean findLowestCommonAncestor(TreeNode root,TreeNode p,TreeNode q){
        if(root==null){
            return false;
        }
        int left = findLowestCommonAncestor(root.left,p,q)?1:0;
        int right = findLowestCommonAncestor(root.right,p,q)?1:0;
        int middle = (root==p || root == q) ? 1:0;
        if(left+right+middle>=2){
            result = root;
        }
        return left+right+middle>0;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        findLowestCommonAncestor(root,p,q);
        return result;
    }
}

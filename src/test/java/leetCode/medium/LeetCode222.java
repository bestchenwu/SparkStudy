package leetCode.medium;

import leetCode.TreeNode;
import org.junit.Test;

//给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
//
// 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层
//为第 h 层，则该层包含 1~ 2h 个节点。
//
//
//
// 示例 1：
//
//
//输入：root = [1,2,3,4,5,6]
//输出：6
//
//
// 示例 2：
//
//
//输入：root = []
//输出：0
//
//
// 示例 3：
//
//
//输入：root = [1]
//输出：1
public class LeetCode222 {

    private int depth = 0;

    public int countNodes(TreeNode root) {
        if(root==null){
            return 0;
        }
        int leftDepth = countLevel(root.left);
        int rightDepth = countLevel(root.right);
        //说明左右子树的高度一致,这时求右子树的节点数即可
        //此时已知的节点个数= Math.pow(2,leftDepth)-1 + root = Math.pow(2,leftDepth)
        if(leftDepth == rightDepth){
            return countNodes(root.right)+1<<leftDepth;
        }else{
            return countNodes(root.left)+1<<leftDepth;
        }
    }

    /**
     * 求树的最大深度
     *
     * @param root
     * @return
     */
    private int countLevel(TreeNode root){
        if(root == null){
            return 0;
        }
        return Math.max(countLevel(root.left),countLevel(root.right))+1;
    }

}

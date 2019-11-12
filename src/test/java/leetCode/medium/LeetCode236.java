package leetCode.medium;

import leetCode.TreeNode;

/**
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 *
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 示例 2:
 *
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出: 5
 * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
 *
 */
public class LeetCode236 {

    private boolean find_node(TreeNode root,TreeNode node){
        if(root == null){
            return false;
        }
        if(root==node){
            return false;
        }
        boolean leftFlag = find_node(root.left,node);
        boolean rightFlag = find_node(root.right,node);
        return leftFlag || rightFlag;
    }

    private TreeNode find0(TreeNode root,TreeNode p,TreeNode q){
        if(root == p){
            boolean flag = find_node(root,q);
            if(flag){
                return root;
            }
        }
        if(root == q){
            boolean flag = find_node(root,p);
            if(flag){
                return root;
            }
        }
        return null;
    }

    private TreeNode find(TreeNode root,TreeNode p,TreeNode q){


        //root = p
        //那么q是root的某个节点吗？
        //root = q
        //那么p是root的某个节点吗？
        //p只在root的左节点 q只在root的右节点
        //root.right 找不到p
        //root.left 找不到q
        //或者
        //root.left 找不到q
        //root.right找不到p

        return null;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        return null;
    }
}

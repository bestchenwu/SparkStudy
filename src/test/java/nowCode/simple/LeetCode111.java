package nowCode.simple;

import leetCode.TreeNode;
//给定一个二叉树，找出其最小深度。
//
// 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
//
// 说明：叶子节点是指没有子节点的节点。
//
//
//
// 示例 1：
//
//
//输入：root = [3,9,20,null,null,15,7]
//输出：2
//
//
// 示例 2：
//
//
//输入：root = [2,null,3,null,4,null,5,null,6]
//输出：5
public class LeetCode111 {

    public int minDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        //说明是一个叶子节点
        if(root.left == null && root.right == null){
            return 1;
        }
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        if(left == 0){
            return 1+right;
        }
        if(right == 0){
            return 1+left;
        }
        return 1+Math.min(left,right);
    }
}

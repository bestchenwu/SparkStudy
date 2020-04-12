package leetCode.medium;

import leetCode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 144. 二叉树的前序遍历
 *
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 *
 * 给定一个二叉树，返回它的 前序 遍历。
 *
 *  示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,2,3]
 *
 */
public class LeetCode144 {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root==null){
            return list;
        }
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        stack.add(root);
        while(!stack.isEmpty()){
            //获取链表的最后一个
            TreeNode treeNode = stack.pollLast();
            list.add(treeNode.val);
            if(treeNode.right!=null){
                //添加到链表的第一个
                stack.addLast(treeNode.right);
            }
            if(treeNode.left!=null){
                stack.addLast(treeNode.left);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        LeetCode144 leetCode144 = new LeetCode144();
        TreeNode root = new TreeNode(1,new TreeNode(2,new TreeNode(3),new TreeNode(4)),new TreeNode(5,new TreeNode(6),new TreeNode(7)));
        List<Integer> integers = leetCode144.preorderTraversal(root);
        System.out.println(integers);

    }
}

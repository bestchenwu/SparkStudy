package leetCode.medium;

import leetCode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//给定一个二叉树，返回其节点值自底向上的层序遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
//
// 例如：
//给定二叉树 [3,9,20,null,null,15,7],
//
//
//    3
//   / \
//  9  20
//    /  \
//   15   7
//
//
// 返回其自底向上的层序遍历为：
//
//
//[
//  [15,7],
//  [9,20],
//  [3]
//]
//
public class LeetCode107 {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> resultList = new LinkedList<>();
        if(root==null){
            return resultList;
        }
        Queue<TreeNode> stack = new LinkedList<>();
        stack.offer(root);
        while(!stack.isEmpty()){
            List<Integer> list = new ArrayList<>();
            int size = stack.size();
            for(int i = 0;i<size;i++){
                TreeNode tmp = stack.poll();
                list.add(tmp.val);
                if(tmp.left!=null){
                    stack.offer(tmp.left);
                }
                if(tmp.right!=null){
                    stack.offer(tmp.right);
                }
            }
            resultList.offerFirst(list);
        }
        return resultList;
    }
}

package leetCode.medium;

import leetCode.TreeNode;

/**
 * https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/
 *
 * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
 *
 * 输入: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * 输出: 1
 */
public class LeetCode230 {

    private TreeNode kth_find(TreeNode root,int index,int k){
        if(root==null){
            return null;
        }
        TreeNode leftNode = kth_find(root.left,index,k);
        if(index == k&&leftNode!=null){
            return leftNode;
        }
        if(k-index==1){
            return root;
        }
        if(leftNode!=null){
            index+=2;
        }else{
            index+=1;
        }
        TreeNode rightNode = kth_find(root.right,index,k);

        return rightNode;
    }

    public int kthSmallest(TreeNode root, int k) {
        //左根右遍历
        //如果左边节点找到了,index+1,直到index = k
        return 0;
    }
}

package leetCode.medium;

import leetCode.TreeNode;
import org.junit.Test;

//给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
//
//
//
// 示例 1：
//
//
//输入：root = [3,1,4,null,2], k = 1
//输出：1
//
//
// 示例 2：
//
//
//输入：root = [5,3,6,2,4,null,null,1], k = 3
//输出：3
public class LeetCode230_20210719 {

    private int result;
    private int k = 1;

    private void preorder(TreeNode root, int n){
        if(root == null || k>n){
            return;
        }
        preorder(root.left, n);
        if(k == n){
            result = root.val;
        }else if(k>n){
            return;
        }
        k+=1;
        preorder(root.right, n);
    }

    /**
     * 前序遍历(左跟右遍历,直到找到第K个数)
     *
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        preorder(root,k);
        return result;
    }

    @Test
    public void testKthSmallest(){
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1,null ,new TreeNode(2) );
        root.right = new TreeNode(4);
        int k = 1;
        int result = kthSmallest(root, k);
        System.out.println(result);
    }
}

package leetCode.medium;

import leetCode.TreeNode;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/
 * <p>
 * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
 * <p>
 * 输入: root = [3,1,4,null,2], k = 1
 * 3
 * / \
 * 1   4
 * \
 * 2
 * 输出: 1
 */
public class LeetCode230 {

    private Integer kth_find(TreeNode root, AtomicInteger index, int k) {
        Integer result = null;
        if (root == null) {
            return null;
        } else {
            index.addAndGet(1);
        }
        if(index.get()==k){
            result = root.val;
            return result;
        }
        result = kth_find(root.left, index, k);
        if(result!=null){
            return result;
        }
        if(index.get()==k){
            return root.val;
        }
        result  = kth_find(root.right, index, k);
        if(result!=null){
            return result;
        }
        return null;
    }

    public int kthSmallest(TreeNode root, int k) {
        //左根右遍历
        //如果左边节点找到了,index+1,直到index = k
        if(root==null){
            return -1;
        }
        AtomicInteger index = new AtomicInteger(0);
        Integer result = null;
        result = kth_find(root.left,index,k);
        if(result !=null){
            return result.intValue();
        }
        //由于判断了root非空,所以这里index需要增加1
        index.addAndGet(1);
        if(index.get()==k){
            return root.val;
        }
        result = kth_find(root.right,index,k);
        return result;
    }

    public static void main(String[] args) {
        LeetCode230 leetCode230 = new LeetCode230();
        TreeNode root = new TreeNode(3, new TreeNode(1, null, new TreeNode(2)), new TreeNode(4));
        int result = leetCode230.kthSmallest(root, 4);
        System.out.println(result);
    }
}

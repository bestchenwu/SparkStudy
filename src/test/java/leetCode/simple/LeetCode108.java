package leetCode.simple;

import leetCode.TreeNode;

/**
 * 将有序序列转换为高度平衡二叉树<br/>
 * https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/
 *
 * @author  chenwu on 2019.10.18
 */
public class LeetCode108 {

    private TreeNode toBSTNode(int[] nums, int start, int end){
        if(end<start){
            return null;
        }
        int middle = start+(end-start)/2;
        TreeNode root = new TreeNode(nums[middle]);
        root.left = toBSTNode(nums,start,middle-1);
        root.right = toBSTNode(nums,middle+1,end);
        return root;
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums==null||nums.length==0){
            return null;
        }
        TreeNode rootNode = toBSTNode(nums,0,nums.length-1);
        return rootNode;
    }

    public static void main(String[] args){
        LeetCode108 leetCode108 = new LeetCode108();
        int[] nums = new int[]{-10,-3,0,5,9};
        TreeNode treeNode = leetCode108.sortedArrayToBST(nums);
        System.out.println(treeNode);
    }
}

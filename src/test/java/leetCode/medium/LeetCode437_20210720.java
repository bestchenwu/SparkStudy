package leetCode.medium;

import leetCode.TreeNode;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * https://leetcode-cn.com/problems/path-sum-iii/
 *
 * 437. 路径总和 III
 *
 * 给定一个二叉树，它的每个结点都存放着一个整数值。
 *
 * 找出路径和等于给定数值的路径总数。
 *
 * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 *
 * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
 *
 * 示例：
 *
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 *
 *       10
 *      /  \
 *     5   -3
 *    / \    \
 *   3   2   11
 *  / \   \
 * 3  -2   1
 *
 * 返回 3。和等于 8 的路径有:
 *
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3.  -3 -> 11
 *
 */
public class LeetCode437_20210720 {

    private int count = 0;

    private void helpPathSum(TreeNode root, int currentSum, int targetSum) {
        if (root == null) {
            return;
        }
        currentSum+=root.val;
        if (currentSum == targetSum) {
            count += 1;
        }
        helpPathSum(root.left, currentSum, targetSum);
        helpPathSum(root.right, currentSum, targetSum);
    }

    /**
     * 深度优先遍历
     *
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSum(TreeNode root, int targetSum) {
        if(root==null){
            return count;
        }
        helpPathSum(root,0,targetSum);
        if(root!=null){
            pathSum(root.left,targetSum);
            pathSum(root.right,targetSum);
        }
        return count;
    }

    @Test
    public void testPathSum(){
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5,new TreeNode(3,new TreeNode(3),new TreeNode(-3)),new TreeNode(2,null,new TreeNode(1)));
        root.right = new TreeNode(-3,null,new TreeNode(11));
        int target = 8;
        int count = pathSum(root,target);
        System.out.println("count="+count);
    }
}

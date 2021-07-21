package leetCode.medium;

import leetCode.TreeNode;
import org.junit.Test;

/**
 * 给定一个二叉树，在树的最后一行找到最左边的值。
 * 示例 1:
 * 输入:
 * 2
 * / \
 * 1   3
 * 输出:1
 * 示例 2:
 * 输入:
 *        1
 *      / \
 *     2   3
 *    /   / \
 *   4   5   6
 *      /
 *     7
 * 输出:7
 */

class NodeDepth {
    public int val = 0;
    public int depth = 0;

    public NodeDepth(int val, int depth) {
        this.val = val;
        this.depth = depth;
    }
}

public class LeetCode513 {

    private NodeDepth result;

    public NodeDepth helpFindBottomLeftValue(TreeNode root, int depth) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            return new NodeDepth(root.val, depth);
        }
        NodeDepth left = helpFindBottomLeftValue(root.left, depth + 1);
        NodeDepth right = helpFindBottomLeftValue(root.right, depth + 1);
        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        } else {
            if (left.depth >= right.depth) {
                return left;
            } else {
                return right;
            }
        }
    }

    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return 0;
        }
        NodeDepth result = helpFindBottomLeftValue(root, 0);
        return result.val;
    }

    @Test
    public void testFindBottomLeftValue() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2, new TreeNode(4), null);
        root.right = new TreeNode(3, new TreeNode(5, 7, null), new TreeNode(6));
        int result = findBottomLeftValue(root);
        System.out.println("result=" + result);
    }
}

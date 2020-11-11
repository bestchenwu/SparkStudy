package leetCode.medium;

import leetCode.TreeNode;

import java.util.Map;
import java.util.HashMap;

/**
 * 从前序与中序遍历序列构造二叉树
 * <p>
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * <p>
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * <p>
 * 前序遍历 preorder = [3,9,20,15,7]  根左右
 * 中序遍历 inorder = [9,3,15,20,7]   左根右
 * 返回如下的二叉树：
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 *
 * @author chenwu on 2020.11.11
 */
public class LeetCode105_20201111 {

    private Map<Integer, Integer> inorderMap = new HashMap<>();
    private int rootIndex = 0;

    private TreeNode helpBuildTree(int[] preorder, int[] inorder, int inorderLeft, int inorderRight) {
        if (inorderRight < inorderLeft) {
            return null;
        } else if (inorderLeft == inorderRight) {
            return new TreeNode(preorder[rootIndex++]);
        } else {
            int rootValue = preorder[rootIndex++];
            TreeNode rootNode = new TreeNode(rootValue);
            int inorderIndex = inorderMap.get(rootValue);
            rootNode.left = helpBuildTree(preorder, inorder, inorderLeft, inorderIndex - 1);
            rootNode.right = helpBuildTree(preorder, inorder, inorderIndex + 1, inorderRight);
            return rootNode;
        }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        rootIndex = 0;
        TreeNode rootNode = helpBuildTree(preorder, inorder, 0, inorder.length - 1);
        return rootNode;
    }

    public static void main(String[] args) {
        int[] preorder = new int[]{3,9,20,15,7};
        int[] inorder = new int[]{9,3,15,20,7};
        LeetCode105_20201111 leetCode105_20201111 = new LeetCode105_20201111();
        TreeNode rootNode = leetCode105_20201111.buildTree(preorder, inorder);
        System.out.println(rootNode);
    }
}

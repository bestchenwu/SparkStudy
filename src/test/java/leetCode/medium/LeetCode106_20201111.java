package leetCode.medium;

import leetCode.TreeNode;

import java.util.Map;
import java.util.HashMap;

/**
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 * <p>
 * https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 * <p>
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * <p>
 * 中序遍历 inorder = [9,3,15,20,7] 左根右
 * 后序遍历 postorder = [9,15,7,20,3] 左右根
 * 返回如下的二叉树：
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * 3
 * 9   20
 * 8 10  15   7
 * 中序遍历  8,9,10,3,15,20,7
 * 后序遍历  8,10,9,15,7,20,3
 *
 * @author chenwu on 2020.11.11
 */
public class LeetCode106_20201111 {

    private int[] inorder;
    private int[] postorder;
    private int postIndex;
    private Map<Integer, Integer> inorderMap = new HashMap<>();
    private Map<Integer, Integer> postorderMap = new HashMap<>();

    private TreeNode helpBuildTree(int inorderLeftIndex, int inorderRightIndex) {
        if (inorderLeftIndex > inorderRightIndex) {
            return null;
        }
        //因为后序遍历是左右根，那么如果我们从后序遍历结尾往前递推，尼玛就是根右左
        int rootValue = postorder[postIndex--];
        int inorderIndex = inorderMap.get(rootValue);
        TreeNode rootNode = new TreeNode(rootValue);
        rootNode.right = helpBuildTree(inorderIndex + 1, inorderRightIndex);
        rootNode.left = helpBuildTree(inorderLeftIndex, inorderIndex - 1);
        return rootNode;
    }


    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.inorder = inorder;
        this.postorder = postorder;
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        for (int j = 0; j < postorder.length; j++) {
            postorderMap.put(postorder[j], j);
        }
        postIndex = postorder.length - 1;
        TreeNode rootNode = helpBuildTree(0, inorder.length - 1);
        return rootNode;
    }

    public static void main(String[] args) {
        LeetCode106_20201111 leetCode106_20201111 = new LeetCode106_20201111();
        int[] inorder = new int[]{9, 3, 15, 20, 7};
        int[] postorder = new int[]{9, 15, 7, 20, 3};
        TreeNode rootNode = leetCode106_20201111.buildTree(inorder, postorder);
        System.out.println(rootNode);
    }
}

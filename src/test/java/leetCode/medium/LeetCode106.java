package leetCode.medium;

import leetCode.TreeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

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
 *
 *      3
 *    9   20
 *   8 10  15   7
 *   中序遍历  8,9,10,3,15,20,7
 *   后序遍历  8,10,9,15,7,20,3
 */
public class LeetCode106 {

    private int[] inorder;
    private int[] postorder;
    private int postOrderIndex = 0;
    //存储中序遍历里的节点值与数组下标的对应关系
    private Map<Integer,Integer>   inorderValueToIndexMap = new HashMap<>();
    //存储后序遍历里的节点值与数组下标的对应关系
    private Map<Integer,Integer>   postOrderValueToIndexMap = new HashMap<>();

    private int findRootIndex(int inorderLeftIndex,int inorderRightIndex){
        int rootIndex = 0;
        for(int i = inorderLeftIndex;i<inorderRightIndex;i++){
            rootIndex = Math.max(rootIndex,postOrderValueToIndexMap.get(inorder[i]));
        }
        return rootIndex;
    }

    private TreeNode helper(int inorderLeftIndex, int inorderRightIndex) {
        if (inorderLeftIndex == inorderRightIndex) {
            return null;
        }
        //寻找当前时刻根节点
        postOrderIndex = findRootIndex(inorderLeftIndex,inorderRightIndex);
        int rootValue = postorder[postOrderIndex];
        int index = inorderValueToIndexMap.get(rootValue);
        TreeNode rootNode = new TreeNode(rootValue);
        rootNode.left = helper(inorderLeftIndex, index);
        rootNode.right = helper(index + 1, inorderRightIndex);
        return rootNode;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.inorder = inorder;
        this.postorder = postorder;
        if (postorder == null || postorder.length == 0) {
            return null;
        }
        for(int i = 0;i<inorder.length;i++){
            inorderValueToIndexMap.put(inorder[i],i);
        }
        for(int i=0;i<postorder.length;i++){
            postOrderValueToIndexMap.put(postorder[i],i);
        }
        TreeNode rootNode = helper(0,inorder.length);
        return rootNode;
    }

    public static void main(String[] args) {
        LeetCode106 leetCode106 = new LeetCode106();
        int[] inorder = new int[]{9,3,15,20,7};
        int[] postorder = new int[]{9,15,7,20,3};
        TreeNode root = leetCode106.buildTree(inorder, postorder);
        System.out.println(root);
    }
}

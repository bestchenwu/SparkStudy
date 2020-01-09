package leetCode.medium;

import leetCode.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 从前序与中序遍历序列构造二叉树
 *
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 *
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]  根左右
 * 中序遍历 inorder = [9,3,15,20,7]   左根右
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 */
public class LeetCode105 {

    private int[] preorder ;
    private int[] inorder;
    private int preIndex = 0;
    //存储中序的值和坐标的对应关系
    private Map<Integer,Integer> inorderMap = new HashMap<>();

    /**
     * 这里的leftIndex表示inorder的最左边
     * 这里的rightIndex表示inorder的最右边
     *
     * @param leftIndex
     * @param rightIndex
     * @return
     */
    private TreeNode helper(int leftIndex,int rightIndex){
        if(leftIndex == rightIndex){
            return null;
        }
        int rootValue = preorder[preIndex];
        TreeNode rootNode = new TreeNode(rootValue);
        int index = inorderMap.get(rootValue);
        preIndex++;
        rootNode.left = helper(leftIndex,index);
        rootNode.right = helper(index+1,rightIndex);
        return rootNode;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;
        for(int index = 0;index<inorder.length;index++){
            inorderMap.put(inorder[index],index);
        }
        TreeNode root = helper(0,inorder.length);
        return root;
    }

    public static void main(String[] args) {
        LeetCode105 leetCode105 = new LeetCode105();
        int[] preOrder = new int[]{3,9,20,15,7};
        int[] inOrder = new int[]{9,3,15,20,7};
        TreeNode rootNode = leetCode105.buildTree(preOrder,inOrder);
        System.out.println(rootNode);
    }
}

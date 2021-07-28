package leetCode.medium;

import leetCode.TreeNode;
import org.junit.Test;

//返回与给定前序遍历 preorder 相匹配的二叉搜索树（binary search tree）的根结点。
//
// (回想一下，二叉搜索树是二叉树的一种，其每个节点都满足以下规则，对于 node.left 的任何后代，值总 < node.val，而 node.right
// 的任何后代，值总 > node.val。此外，前序遍历首先显示节点 node 的值，然后遍历 node.left，接着遍历 node.right。）
//
// 题目保证，对于给定的测试用例，总能找到满足要求的二叉搜索树。
//
//
//
// 示例：
//
// 输入：[8,5,1,7,10,12]
//输出：[8,5,10,1,7,null,12]
public class LeetCode1008 {

    private int rootIndex = 0;

    private TreeNode helpBstFromPreorder(int[] preorder, Integer low, Integer high) {
        if(rootIndex>=preorder.length){
            return null;
        }
        int rootVal = preorder[rootIndex];
        if (rootVal < low || rootVal > high) {
            return null;
        }
        rootIndex += 1;
        TreeNode root = new TreeNode(rootVal);
        root.left = helpBstFromPreorder(preorder, low, rootVal);
        root.right = helpBstFromPreorder(preorder, rootVal, high);
        return root;
    }

    public TreeNode bstFromPreorder(int[] preorder) {
        TreeNode root = helpBstFromPreorder(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return root;
    }

    @Test
    public void testBstFromPreorder() {
        int[] preorder = new int[]{8, 5, 1, 7, 10, 12};
        TreeNode root = bstFromPreorder(preorder);
        System.out.println("root=" + root);
    }
}

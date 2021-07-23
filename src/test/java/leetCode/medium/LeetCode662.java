package leetCode.medium;

import leetCode.TreeNode;
import org.junit.Test;

import java.util.*;

//给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。这个二叉树与满二叉树（full binary tree）结构相同，但一些节
//点为空。
//
// 每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。
//
// 示例 1:
//
//
//输入:
//
//           1
//         /   \
//        3     2
//       / \     \
//      5   3     9
//
//输出: 4
//解释: 最大值出现在树的第 3 层，宽度为 4 (5,3,null,9)。
//
//
// 示例 2:
//
//
//输入:
//
//          1
//         /
//        3
//       / \
//      5   3
//
//输出: 2
//解释: 最大值出现在树的第 3 层，宽度为 2 (5,3)。
//
//
// 示例 3:
//
//
//输入:
//
//          1
//         / \
//        3   2
//       /
//      5
//
//输出: 2
//解释: 最大值出现在树的第 2 层，宽度为 2 (3,2)。
//
//
// 示例 4:
//
//
//输入:
//
//          1
//         / \
//        3   2
//       /     \
//      5       9
//     /         \
//    6           7
//输出: 8
//解释: 最大值出现在树的第 4 层，宽度为 8 (6,null,null,null,null,null,null,7)。

class TreeNodeId{
    public TreeNode root;
    public int idx;

    public TreeNodeId(TreeNode root,int idx){
        this.root = root;
        this.idx = idx;
    }
}

public class LeetCode662 {

    private int width = 0;

    private void helpWidthOfBinaryTree(List<TreeNode> nodeList, boolean hasNonNullFlag) {
        if (!hasNonNullFlag) {
            return;
        }
        hasNonNullFlag = false;
        List<TreeNode> subNodeList = new ArrayList<>();
        Integer startIndex = null;
        Integer endIndex = null;
        for (int i = 0; i < nodeList.size(); i++) {
            TreeNode node = nodeList.get(i);
            if (node == null) {
                subNodeList.add(null);
                subNodeList.add(null);
            } else {
                if (startIndex == null) {
                    startIndex = i;
                }
                endIndex = i;
                subNodeList.add(node.left);
                subNodeList.add(node.right);
                hasNonNullFlag = hasNonNullFlag || node.left != null || node.right != null;
            }
        }
        width = Math.max(endIndex - startIndex + 1,width);
        helpWidthOfBinaryTree(subNodeList, hasNonNullFlag);
    }

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        helpWidthOfBinaryTree(Arrays.asList(root), true);
        return width;
    }

    /**
     * 假设当前id为x,树的左子节点ID计为2*x+1,树的右子节点计为2*x+2
     * @param root
     * @return
     */
    public int widthOfBinaryTree1(TreeNode root){
        if(root == null){
            return 0;
        }
        LinkedList<TreeNodeId> queue = new LinkedList<>();
        queue.offer(new TreeNodeId(root,0));
        while(!queue.isEmpty()){
            int startIndex = 0,endIndex = 0;
            int length = queue.size();
            for(int i = 0;i<length;i++){
                TreeNodeId node = queue.pollFirst();
                if(i==0){
                    startIndex = node.idx;
                }
                if(i==length-1){
                    endIndex = node.idx;
                }
                if(node.root.left!=null){
                    queue.offerLast(new TreeNodeId(node.root.left, 2*node.idx+1));
                }
                if(node.root.right!=null){
                    queue.offerLast(new TreeNodeId(node.root.right, 2*node.idx+2));
                }
            }
            width = Math.max(endIndex-startIndex+1,width );
        }
        return width;
    }

    @Test
    public void widthOfBinaryTree(){
//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(3, 5, 3);
//        root.right = new TreeNode(2,null,9);
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(3, 5, null);
        TreeNode right = new TreeNode(2);
        root.left = left;
        root.right = right;
        int width = widthOfBinaryTree1(root);
        System.out.println("width="+width);
    }
}

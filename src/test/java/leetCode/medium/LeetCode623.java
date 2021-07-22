package leetCode.medium;

import leetCode.TreeNode;
import org.junit.Test;

import java.util.*;

//给定一个二叉树，根节点为第1层，深度为 1。在其第 d 层追加一行值为 v 的节点。
//
// 添加规则：给定一个深度值 d （正整数），针对深度为 d-1 层的每一非空节点 N，为 N 创建两个值为 v 的左子树和右子树。
//
// 将 N 原先的左子树，连接为新节点 v 的左子树；将 N 原先的右子树，连接为新节点 v 的右子树。
//
// 如果 d 的值为 1，深度 d - 1 不存在，则创建一个新的根节点 v，原先的整棵树将作为 v 的左子树。
//
// 示例 1:
//
//
//输入:
//二叉树如下所示:
//       4
//     /   \
//    2     6
//   / \   /
//  3   1 5
//
//v = 1
//
//d = 2
//
//输出:
//       4
//      / \
//     1   1
//    /     \
//   2       6
//  / \     /
// 3   1   5
//
//
//
// 示例 2:
//
//
//输入:
//二叉树如下所示:
//      4
//     /
//    2
//   / \
//  3   1
//
//v = 1
//
//d = 3
//
//输出:
//      4
//     /
//    2
//   / \
//  1   1
// /     \
//3       1
public class LeetCode623 {

    public void helpAddOneRow(List<TreeNode> nodeList,int val,int depth,int k){
        List<TreeNode> newNodeList = new ArrayList<>();
        boolean breakFlag = false;
        for(TreeNode node:nodeList){
            if(k+1==depth) {
                breakFlag = true;
                TreeNode leftTmp = node.left;
                TreeNode rightTmp = node.right;
                TreeNode newLeft = new TreeNode(val);
                TreeNode newRight = new TreeNode(val);
                node.left = newLeft;
                newLeft.left = leftTmp;
                node.right = newRight;
                newRight.right = rightTmp;
            }else {
                if(node.left!=null){
                    newNodeList.add(node.left);
                }
                if(node.right!=null){
                    newNodeList.add(node.right);
                }
            }
        }
        if(breakFlag){
            return;
        }else{
            helpAddOneRow(newNodeList, val, depth, k+1);
        }
    }

    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if(root == null){
            return new TreeNode(val);
        }
        if(depth==1){
            TreeNode newRoot = new TreeNode(val);
            newRoot.left = root;
            return newRoot;
        }
        helpAddOneRow(Arrays.asList(root),val,depth,1);
        return root;
    }

    @Test
    public void testAddOneRow(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2,null,3);
        root.right = new TreeNode(4);
        TreeNode newRoot = addOneRow(root,5,4);
        System.out.println(newRoot);
    }
}

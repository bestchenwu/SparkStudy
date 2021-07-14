package leetCode.medium;

import leetCode.TreeNode;

import java.util.*;

/**
 * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层次遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 *
 *
 */
public class LeetCode102 {

    private void  listNode(List<TreeNode> nodes, List<List<Integer>> result){
        if(nodes.isEmpty()){
            return;
        }
        List<Integer> list = new ArrayList<Integer>();
        List<TreeNode> childrens = new ArrayList<TreeNode>();
        for(TreeNode treeNode:nodes){
            list.add(treeNode.val);
            if(treeNode.left!=null){
                childrens.add(treeNode.left);
            }
            if(treeNode.right!=null){
                childrens.add(treeNode.right);
            }
        }
        result.add(list);
        listNode(childrens,result);
    }

    //深度优先遍历
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(root == null){
            return result;
        }
        listNode(Arrays.asList(root),result);
        return result;
    }

    public List<List<Integer>> levelOrder0(TreeNode root) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (root == null) {
            return resultList;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            LinkedList<TreeNode> tmpList = new LinkedList<>();
            while (!stack.isEmpty()) {
                TreeNode tmp = stack.pop();
                list.add(tmp.val);
                if (tmp.left != null) {
                    tmpList.offerFirst(tmp.left);
                }
                if (tmp.right != null) {
                    tmpList.offerFirst(tmp.right);
                }
            }
            for (TreeNode treeNode : tmpList) {
                stack.push(treeNode);
            }
            resultList.add(list);
        }
        return resultList;
    }

    public static void main(String[] args) {

    }
}

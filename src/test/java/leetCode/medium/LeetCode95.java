package leetCode.medium;

import leetCode.TreeNode;

import java.util.*;

/**
 * 不同的二叉搜索树 II
 *
 * https://leetcode-cn.com/problems/unique-binary-search-trees-ii/
 *
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。
 *
 * 示例:
 *
 * 输入: 3
 * 输出:
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * 解释:
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 */
public class LeetCode95 {

    public List<TreeNode> generateTrees(int n){
        if(n==0){
            return new ArrayList<>();
        }
       List<TreeNode> list = generateTreesHelp(1,n);
       return list;
    }

    Map<String,List<TreeNode>> map = new HashMap<>();
    TreeNode emptyNode = new TreeNode(-1);
    private List<TreeNode> generateTreesHelp(int from,int end){
        if(map.get(from+"-"+end)!=null){
            return map.get(from+"-"+end);
        }
        if(from > end){
            List<TreeNode> treeNodeList = Arrays.asList(emptyNode);
            map.put((from+"-"+end),treeNodeList);
            return treeNodeList;
        }else if(from==end){
            List<TreeNode> treeNodeList = Arrays.asList(new TreeNode(from));
            map.put((from+"-"+end),treeNodeList);
            return treeNodeList;
        }else if(end-from ==1 ){
            TreeNode node1 = new TreeNode(end);
            node1.left = new TreeNode(from);
            TreeNode node2 = new TreeNode(from);
            node2.right = new TreeNode(end);
            List<TreeNode> treeNodeList = Arrays.asList(node1, node2);
            map.put((from+"-"+end),treeNodeList);
            return treeNodeList;
        }else{
            List<TreeNode> result = new ArrayList<>();
            for(int i = from;i<=end;i++){
                List<TreeNode> leftList = generateTreesHelp(from,i-1);
                List<TreeNode> rightList = generateTreesHelp(i+1,end);
                for(TreeNode leftNode:leftList){
                    for(TreeNode rightNode:rightList){
                        //以i为根节点
                        TreeNode root = new TreeNode(i);
                        root.left = leftNode==emptyNode?null:leftNode;
                        root.right = rightNode==emptyNode?null:rightNode;
                        result.add(root);
                    }
                }
            }
            map.put(from+"-"+end,result);
            return result;
        }
    }

    private List<TreeNode> helpGenerateTrees(int left,int right){
        if(left>right){
            List<TreeNode> list = new ArrayList<>();
            list.add(null);
            return list;
        }
        List<TreeNode> allNodes = new ArrayList<>();
        for(int i = left;i<=right;i++){
            List<TreeNode> leftNodes = helpGenerateTrees(left,i-1);
            List<TreeNode> rightNodes = helpGenerateTrees(i+1,right);
            for(TreeNode leftNode : leftNodes){
                for(TreeNode rightNode : rightNodes){
                    TreeNode root = new TreeNode(i);
                    root.left = leftNode;
                    root.right = rightNode;
                    allNodes.add(root);
                }
            }
        }
        return allNodes;
    }

    public List<TreeNode> generateTrees1(int n) {
        if(n==1){
            return Arrays.asList(new TreeNode(n));
        }
        List<TreeNode> result = helpGenerateTrees(1, n);
        return result;
    }

}

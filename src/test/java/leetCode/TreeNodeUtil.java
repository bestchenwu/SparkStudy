package leetCode;

import com.spark.constants.SymbolConstants;

/**
 * 二叉树的工具类  用于调试<br/>
 * 包括按照深度优先遍历或者广度优先遍历的方式打印二叉树等
 */
public class TreeNodeUtil {

    /**
     * 按照广度优先的方式打印树
     *
     * @param treeNodes
     * @author chenwu on 2019.9.19
     */
    public static void printTreeInWidth(TreeNode... treeNodes){
        if(treeNodes==null||treeNodes.length==0||treeNodes[0]==null){
            return;
        }
        TreeNode[] lowerTreeNodes = new TreeNode[treeNodes.length*2];
        int index = 0;
        for(TreeNode treeNode:treeNodes){
            System.out.print(treeNode+ SymbolConstants.SYMBOL_TAB);
            if(treeNode.left!=null){
                lowerTreeNodes[index++] = treeNode.left;
            }
            if(treeNode.right!=null){
                lowerTreeNodes[index++] = treeNode.right;
            }
        }
        System.out.println("\n---");
        printTreeInWidth(lowerTreeNodes);
    }

    /**
     * 深度优先遍历<br/>
     * 先根节点,再左节点,再右节点
     *
     * @param treeNode
     */
    public static void printTreeInDepth(TreeNode treeNode){
        if(treeNode == null){
            return;
        }
        System.out.println(treeNode);
        printTreeInDepth(treeNode.left);
        printTreeInDepth(treeNode.right);
    }
}

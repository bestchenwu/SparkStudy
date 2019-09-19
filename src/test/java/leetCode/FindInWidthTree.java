package leetCode;

import com.spark.constants.SymbolConstants;

/**
 * 实现广度优先遍历树
 *
 * @author  chenwu on 2019.9.19
 */
public class FindInWidthTree {

    private void FindAndPrintWithTree(TreeNode... treeNodes){
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
        FindAndPrintWithTree(lowerTreeNodes);
    }

    /**
     * 深度优先遍历<br/>
     * 先根节点,再左节点,再右节点
     *
     * @param treeNode
     */
    private void FindAndPrintInDepth(TreeNode treeNode){
        if(treeNode == null){
            return;
        }
        System.out.println(treeNode);
        FindAndPrintInDepth(treeNode.left);
        FindAndPrintInDepth(treeNode.right);
    }

    public static void main(String[] args){
        FindInWidthTree test = new FindInWidthTree();
        TreeNode node = new TreeNode(1,new TreeNode(2,new TreeNode(4),new TreeNode(5)),new TreeNode(3,new TreeNode(6),new TreeNode(7)));
        //test.FindAndPrintWithTree(node);

        test.FindAndPrintInDepth(node);
    }
}

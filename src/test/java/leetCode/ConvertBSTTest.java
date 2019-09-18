package leetCode;

import sun.reflect.generics.tree.Tree;

/**
 * https://leetcode-cn.com/problems/convert-bst-to-greater-tree/
 *
 * @author  chenwu on 2019.9.18
 */
public class ConvertBSTTest {


    public int findInDepth(TreeNode node,int sum){
        if(node==null){
            return sum;
        }
        sum = findInDepth(node.right,sum);
        int nodeValue = node.val;
        node.val+=sum;
        sum+=nodeValue;
        return findInDepth(node.left,sum);
    }

    public TreeNode convertBST(TreeNode root) {
        if(root==null){
            return null;
        }
        findInDepth(root,0);
        return root;
    }

    public static void main(String[] args){
        ConvertBSTTest test = new ConvertBSTTest();
        TreeNode root = new TreeNode(5,new TreeNode(2,new TreeNode(8),new TreeNode(5)),new TreeNode(13));
        TreeNode resultNode = test.convertBST(root);
        System.out.println(resultNode);
    }
}

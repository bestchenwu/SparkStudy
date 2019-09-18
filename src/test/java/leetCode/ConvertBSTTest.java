package leetCode;

/**
 * https://leetcode-cn.com/problems/convert-bst-to-greater-tree/
 *
 * @author  chenwu on 2019.9.18
 */
public class ConvertBSTTest {



    public TreeNode convertBST(TreeNode root) {

        if(root==null){
            return null;
        }
        TreeNode result = new TreeNode(root.val);
        TreeNode rightNode = null;
        if(root.right!=null){
            rightNode = convertBST(root.right);
            result.right = rightNode;
        }
        result.val+=rightNode!=null?rightNode.val:0;
        TreeNode leftNode = new TreeNode(result.val);
        if(root.left!=null){
            TreeNode leftBSTNode = convertBST(root.left);
            leftNode.val+=leftBSTNode!=null?leftBSTNode.val:0;
        }
        return result;
    }

    public static void main(String[] args){
        ConvertBSTTest test = new ConvertBSTTest();

    }
}

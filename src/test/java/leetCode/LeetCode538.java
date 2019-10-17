package leetCode;

/**
 * https://leetcode-cn.com/problems/convert-bst-to-greater-tree/
 */
public class LeetCode538 {

    public int convertBSTSum(TreeNode root,int sum){
        if(root==null){
            return sum;
        }
        sum=convertBSTSum(root.right,sum);
        root.val  +=sum;
        sum = root.val;
        return convertBSTSum(root.left,sum);
    }

    public TreeNode convertBST(TreeNode root) {
        if(root==null){
            return null;
        }
        convertBSTSum(root,0);
        return root;
    }

    public static void main(String[] args){
        LeetCode538 leetCode538 = new LeetCode538();
        TreeNode root = new TreeNode(5,new TreeNode(3,new TreeNode(2),new TreeNode(4)),new TreeNode(7,new TreeNode(6),new TreeNode(8)));
        TreeNode bstNode = leetCode538.convertBST(root);
        System.out.println(bstNode);
    }

}

package leetCode;

/**
 * https://leetcode-cn.com/problems/merge-two-binary-trees/
 *
 * 合并二叉树
 *
 * @author chenwu on 2019.10.17
 */
public class LeetCode617 {

    public TreeNode mergeNode(TreeNode t1,TreeNode t2){
        if(t1!=null&&t2==null){
            return t1;
        }
        if(t1==null&&t2!=null){
            return t2;
        }
        return new TreeNode(t1.val+t2.val);
    }

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1==null&&t2==null){
            return null;
        }
        TreeNode root = mergeNode(t1,t2);
        root.left = mergeTrees(t1!=null?t1.left:null,t2!=null?t2.left:null);
        root.right = mergeTrees(t1!=null?t1.right:null,t2!=null?t2.right:null);
        return root;
    }

    public static void main(String[] args) {
        LeetCode617 leetCode617 = new LeetCode617();
        TreeNode t1 = new TreeNode(1,new TreeNode(3,new TreeNode(5),null),new TreeNode(2));
        TreeNode t2 = new TreeNode(2,new TreeNode(1,null,new TreeNode(4)),new TreeNode(3,null,new TreeNode(7)));
        TreeNode treeNode = leetCode617.mergeTrees(t1, t2);
        System.out.println(treeNode);
    }
}

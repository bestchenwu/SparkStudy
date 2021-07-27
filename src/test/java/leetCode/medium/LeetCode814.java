package leetCode.medium;

import leetCode.TreeNode;
import org.junit.Test;

//
// ( 节点 X 的子树为 X 本身，以及所有 X 的后代。)
//
//
//示例1:
//输入: [1,null,0,0,1]
//输出: [1,null,0,null,1]
//
//解释:
//只有红色节点满足条件“所有不包含 1 的子树”。
//右图为返回的答案。
//
//
//
//
//
//示例2:
//输入: [1,0,1,0,0,0,1]
//输出: [1,null,1,null,1]
//
//
//
//
//
//
//示例3:
//输入: [1,1,0,1,1,0,1,0]
//输出: [1,1,0,1,1,null,1]
public class LeetCode814 {

    public boolean ifHasOne(TreeNode root){
        if(root == null){
            return false;
        }
        boolean left = ifHasOne(root.left);
        if(!left){
            root.left = null;
        }
        boolean right = ifHasOne(root.right);
        if(!right){
            root.right = null;
        }
        boolean result = (left || right || root.val == 1);
        return result;

    }

    public TreeNode pruneTree(TreeNode root) {
        if(root == null){
            return null;
        }
        boolean result = ifHasOne(root);
        if(!result){
            return null;
        }else{
            return root;
        }
    }

    @Test
    public void testPruneTree(){
        TreeNode root = new TreeNode(1);
        //root.left = new TreeNode(0,0,0);
        //root.right = new TreeNode(1,0,1);
        //root.right = new TreeNode(0,0 ,1 );
        root.left = new TreeNode(1,new TreeNode(1, 0, null),new TreeNode(1));
        root.right = new TreeNode(0,0,1);
        TreeNode newRoot = pruneTree(root);
        System.out.println("newRoot="+newRoot);
    }
}

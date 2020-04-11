package leetCode.simple;

import leetCode.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class LeetCode101 {

    public boolean isSymmetric0(TreeNode root) {
        if(root==null){
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();
            if(t1==null&&t2==null) {
                continue;
            }
            if(t1==null || t2 == null || t1.val != t2.val){
                return false;
            }
            queue.add(t1.left);
            queue.add(t2.right);
            queue.add(t1.right);
            queue.add(t2.left);
        }
        return true;
    }

    private boolean helpIsSymmetric(TreeNode left,TreeNode right){
        if(left ==null && right == null){
            return true;
        }
        if(left!=null && right == null){
            return false;
        }
        if(left == null && right != null){
            return false;
        }
        return left.val == right.val && helpIsSymmetric(left.left,right.right) && helpIsSymmetric(left.right,right.left);
    }

    public boolean isSymmetric(TreeNode root) {
        if(root==null){
            return true;
        }
        boolean result = helpIsSymmetric(root.left,root.right);
        return result;
    }
}

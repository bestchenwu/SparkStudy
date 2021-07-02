package leetCode.medium;

import leetCode.TreeNode;
import org.junit.Test;

import java.util.*;

public class LeetCode99 {

    public void recoverTree(TreeNode root) {
        List<Integer> nums = new ArrayList<>();
        inorder(root,nums);
        int[] data = find(nums);
        recover(root,data[0] ,data[1] ,2 );
    }

    private void inorder(TreeNode root,List<Integer> nums){
        if(root==null){
            return;
        }
        inorder(root.left,nums );
        nums.add(root.val);
        inorder(root.right,nums);
    }

    private int[] find(List<Integer> nums){
        int[] result = new int[2];
        int x = -1,y = -1;
        for(int i = 0;i<nums.size()-1;i++){
            if(nums.get(i+1)<nums.get(i)){
                y = nums.get(i+1);
                if(x==-1){
                    x = nums.get(i);
                }else{
                    break;
                }
            }
        }
        return new int[]{x,y};
    }

    private void recover(TreeNode root,int x,int y,int count){
        if(root==null){
            return;
        }
        if(root.val == x || root.val==y){
            root.val = (root.val==x?y:x);
            if(--count==0){
                return;
            }
        }
        recover(root.left,x,y,count);
        recover(root.right,x,y,count);
    }

    @Test
    public void testRecoverTree(){
        TreeNode root = new TreeNode(1,new TreeNode(3),new TreeNode(2));
        recoverTree(root);
    }
}

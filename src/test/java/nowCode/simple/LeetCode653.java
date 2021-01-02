package nowCode.simple;

import leetCode.TreeNode;

import java.util.ArrayList;
import java.util.List;

//给定一个二叉搜索树和一个目标结果，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。
//
// 案例 1:
//
//
//输入:
//    5
//   / \
//  3   6
// / \   \
//2   4   7
//
//Target = 9
//
//输出: True
//
//
//
//
// 案例 2:
//
//
//输入:
//    5
//   / \
//  3   6
// / \   \
//2   4   7
//
//Target = 28
//
//输出: False
public class LeetCode653 {

    private List<Integer> inorderList = new ArrayList<>();

    private void traverseTreeNode(TreeNode root){
        if(root==null){
            return;
        }
        TreeNode leftNode = root.left;
        TreeNode rightNode = root.right;
        int rootVal = root.val;
        traverseTreeNode(leftNode);
        inorderList.add(rootVal);
        traverseTreeNode(rightNode);
    }

    public boolean findTarget(TreeNode root, int k) {
//        helpFind(root,k);
//        return result;
        traverseTreeNode(root);
        int left = 0;
        int right = inorderList.size()-1;
        int sum;
        while(left<right){
            sum = inorderList.get(left)+inorderList.get(right);
            if(sum==k){
                return true;
            }
            if(sum<k){
                left++;
            }else{
                right--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3,new TreeNode(2),new TreeNode(4));
        root.right = new TreeNode(6,null,new TreeNode(7));
        int k = 9;
        LeetCode653 leetCode653 = new LeetCode653();
        boolean target = leetCode653.findTarget(root, k);
        System.out.println("target="+target);
    }
}

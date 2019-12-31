package leetCode.medium;

import leetCode.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个二叉树，返回它的中序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,3,2]
 *
 */
public class LeetCode94 {

    /**
     * 前序:根左右
     * 中序:左根右
     * 后序:左右跟
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        if(root==null){
            return Collections.emptyList();
        }
        if(root.left==null&&root.right==null){
            return Arrays.asList(root.val);
        }
        List<Integer> list = new ArrayList<>();
        if(root.left!=null){
            List<Integer> leftList = inorderTraversal(root.left);
            if(!leftList.isEmpty()){
                list.addAll(leftList);
            }
        }
        List<Integer> rootList = Arrays.asList(root.val);
        list.addAll(rootList);
        if(root.right!=null){
            List<Integer> rightList = inorderTraversal(root.right);
            if(!rightList.isEmpty()){
                list.addAll(rightList);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        LeetCode94 leetCode94 = new LeetCode94();
        TreeNode root = new TreeNode(1,null,new TreeNode(2,new TreeNode(3),null));
        List<Integer> list = leetCode94.inorderTraversal(root);
        System.out.println(list);
    }
}

package leetCode.medium;

import leetCode.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个二叉树，返回它的 前序 遍历。
 * <p>
 *  示例:
 * <p>
 * 输入: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * 输出: [1,2,3]
 */
public class LeetCode145 {

    /**
     * 前序遍历=根左右
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        if (root.left == null && root.right == null) {
            return Arrays.asList(root.val);
        }
        List<Integer> rootList = Arrays.asList(root.val);
        list.addAll(rootList);
        if (root.left != null) {
            List<Integer> leftList = preorderTraversal(root.left);
            if (!leftList.isEmpty()) {
                list.addAll(leftList);
            }
        }
        if (root.right != null) {
            List<Integer> rightList = preorderTraversal(root.right);
            if (!rightList.isEmpty()) {
                list.addAll(rightList);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        LeetCode145 leetCode145 = new LeetCode145();
        TreeNode root = new TreeNode(1,null,new TreeNode(2,new TreeNode(3),null));
        List<Integer> list = leetCode145.preorderTraversal(root);
        System.out.println(list);
    }
}

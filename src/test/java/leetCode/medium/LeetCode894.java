package leetCode.medium;

import leetCode.TreeNode;

import java.util.*;

public class LeetCode894 {


    /**
     * 满二叉树必须都是奇数个节点
     *
     * @param n
     * @return
     */
    public List<TreeNode> allPossibleFBT(int n) {
        List<TreeNode> res = new ArrayList<>();
        if (n == 1) {
            return Arrays.asList(new TreeNode(0));
        } else if (n % 2 == 1) {
            for (int i = 1; i < n; i++) {
                List<TreeNode> left = allPossibleFBT(i);
                //留一个空当给根节点
                List<TreeNode> right = allPossibleFBT(n - i - 1);
                for (TreeNode node1 : left) {
                    for (TreeNode node2 : right) {
                        TreeNode root = new TreeNode(0);
                        root.left = node1;
                        root.right = node2;
                        res.add(root);
                    }
                }
            }
        }
        return res;
    }
}

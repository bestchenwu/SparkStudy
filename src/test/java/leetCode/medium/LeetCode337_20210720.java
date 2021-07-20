package leetCode.medium;

import leetCode.TreeNode;
import org.junit.Test;

import java.util.*;

//在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“
//房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
//
// 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
//
// 示例 1:
//
// 输入: [3,2,3,null,3,null,1]
//
//     3
//    / \
//   2   3
//    \   \
//     3   1
//
//输出: 7
//解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
//
// 示例 2:
//
// 输入: [3,4,5,1,3,null,1]
//
//     3
//    / \
//   4   5
//  / \   \
// 1   3   1
//
//输出: 9
//解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
public class LeetCode337_20210720 {

    private Map<TreeNode, Integer> memo = new HashMap<>();

    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (memo.get(root) != null) {
            return memo.get(root);
        }
        //只爬爷爷和孙子
        int rob1 =
                root.val + (root.left != null ? (rob(root.left.left) + rob(root.left.right)) : 0) + (root.right != null ?
                        (rob(root.right.left) + rob(root.right.right)) : 0);
        int rob2 = rob(root.left) + rob(root.right);
        int result = Math.max(rob1, rob2);
        memo.put(root, result);
        return result;
    }

    @Test
    public void testRob() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2, null, new TreeNode(3));
        root.right = new TreeNode(3, null, new TreeNode(1));
        int result = rob(root);
        System.out.println("result=" + result);
    }
}

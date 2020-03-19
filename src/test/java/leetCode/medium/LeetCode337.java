package leetCode.medium;

import leetCode.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/house-robber-iii/
 *
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 *
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 *
 * 示例 1:
 *
 * 输入: [3,2,3,null,3,null,1]
 *
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 *
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 * 示例 2:
 *
 * 输入: [3,4,5,1,3,null,1]
 *
 *      3
 *     / \
 *    4   5
 *   / \   \
 *  1   3   1
 *
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 *
 * 可以参考LeetCode543 求树的最大深度
 */
public class LeetCode337 {

    public int robInternal(TreeNode root, HashMap<TreeNode,Integer> map) {
        if(root==null){
            return 0;
        }
        if(map.containsKey(root)){
            return map.get(root);
        }
        //先计算root root.left.left root.left.right root.right.left,root.right.right的四个孙子和爷爷的钱
        int money = root.val;
        if(root.left!=null){
            money+=robInternal(root.left.left,map)+robInternal(root.left.right,map);
        }
        if(root.right!=null){
            money+=robInternal(root.right.left,map)+robInternal(root.right.right,map);
        }
        //两个儿子偷的钱
        int result = Math.max(money,robInternal(root.left,map)+robInternal(root.right,map));
        map.put(root,result);
        return result;
    }

    public int rob(TreeNode root) {
        HashMap<TreeNode,Integer> map = new HashMap<>();
        return robInternal(root,map);
    }

//    public int rob0(TreeNode root) {
//        if(root==null){
//            return 0;
//        }
//        //先计算root root.left.left root.left.right root.right.left,root.right.right的四个孙子和爷爷的钱
//        int money = root.val;
//        if(root.left!=null){
//            money+=rob(root.left.left)+rob(root.left.right);
//        }
//        if(root.right!=null){
//            money+=rob(root.right.left)+rob(root.right.left);
//        }
//        //两个儿子偷的钱
//        int result = Math.max(money,rob(root.left)+rob(root.right));
//        return result;
//    }

    public static void main(String[] args) {
        LeetCode337 leetCode337 = new LeetCode337();
        TreeNode root = new TreeNode(3,new TreeNode(4,new TreeNode(1),new TreeNode(3)),new TreeNode(5,null,new TreeNode(1)));
        int result = leetCode337.rob(root);
        System.out.println(result);
    }
}

package leetCode.simple;

import leetCode.TreeNode;
import org.apache.spark.sql.sources.In;

/**
 * https://leetcode-cn.com/problems/second-minimum-node-in-a-binary-tree/
 * <p>
 * 给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。如果一个节点有两个子节点的话，那么这个节点的值不大于它的子节点的值。
 * <p>
 * 给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。
 * <p>
 * 输入:
 * 2
 * / \
 * 2   5
 * / \
 * 5   7
 * <p>
 * 输出: 5
 * 说明: 最小的值是 2 ，第二小的值是 5 。
 *
 * @author chenwu on 2019.11.7
 */
public class LeetCode671 {

    public Integer findValue(TreeNode node, int root_val) {
        if (node == null) {
            return null;
        }
        if (node.val != root_val) {
            return node.val;
        } else {
            Integer leftValue = findValue(node.left, root_val);
            Integer rightValue = findValue(node.right, root_val);
            if(leftValue==null&&rightValue==null){
                return null;
            }
            if(leftValue==null&&rightValue!=null){
                return rightValue.intValue();
            }
            if(leftValue!=null&&rightValue==null){
                return leftValue.intValue();
            }
            return Math.min(leftValue.intValue(),rightValue.intValue());
        }
    }

    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) {
            return -1;
        }
        //找寻左子树的大于root的节点,如果节点在一个父节点，则返回两个,取较小值
        //找寻右子树的大于root的节点,如果节点在一个父节点，则返回两个,取较小值
        Integer result = findValue(root, root.val);
        return result!=null?result.intValue():-1;
    }

    public static void main(String[] args) {
        //TreeNode root = new TreeNode(2, new TreeNode(2), new TreeNode(Integer.MAX_VALUE));
        TreeNode root = new TreeNode(2,new TreeNode(2),new TreeNode(5,new TreeNode(5),new TreeNode(7)));
        LeetCode671 leetCode671 = new LeetCode671();
        int result = leetCode671.findSecondMinimumValue(root);
        System.out.println(result);
    }
}

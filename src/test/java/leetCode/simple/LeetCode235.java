package leetCode.simple;

import leetCode.TreeNode;

/**
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 * <p>
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * <p>
 * 示例 1:
 * <p>
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * 输出: 6
 * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
 * <p>
 * 示例 2:
 * <p>
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * 输出: 2
 * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
 * <p>
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 */
public class LeetCode235 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//        //做一个检测，始终让p的节点值小
//        TreeNode temp ;
//        if(p.val>q.val){
//            temp = p;
//            p = q;
//            q = temp;
//        }
        if (root.val >=p.val && root.val <=q.val || root.val >=q.val && root.val <=p.val) {
            return root;
        } else if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return lowestCommonAncestor(root.left, p, q);
        }
    }

    public static void main(String[] args) {
        LeetCode235 leetCode235 = new LeetCode235();
        TreeNode root = new TreeNode(6, new TreeNode(2, new TreeNode(0), new TreeNode(4, new TreeNode(3), new TreeNode(5))), new TreeNode(8, new TreeNode(7), new TreeNode(9)));
        TreeNode node = leetCode235.lowestCommonAncestor(root, new TreeNode(2), new TreeNode(4));
        System.out.println(node.val);
    }
}

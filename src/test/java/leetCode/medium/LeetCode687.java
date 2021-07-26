package leetCode.medium;

import leetCode.TreeNode;

//给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。
//
// 注意：两个节点之间的路径长度由它们之间的边数表示。
//
// 示例 1:
//
// 输入:
//
//
//              5
//             / \
//            4   5
//           / \   \
//          1   1   5
//
//
// 输出:
//
//
//2
//
//
// 示例 2:
//
// 输入:
//
//
//              1
//             / \
//            4   5
//           / \   \
//          4   4   5
//
//
// 输出:
//
//
//2
public class LeetCode687 {

    private int ans;

    public int longestUnivaluePath(TreeNode root) {
        if(root == null){
            return 0;
        }
        dfs(root,root.val);
        return ans;
    }

    public int dfs(TreeNode root,int pre){
        if(root == null){
            return 0;
        }
        int l = dfs(root.left,root.val);
        int r = dfs(root.right,root.val);
        int sum = l+r;
        ans = Math.max(sum,ans);
        if(root.val == pre){
            return Math.max(l,r)+1;
        }
        return 0;
    }
}

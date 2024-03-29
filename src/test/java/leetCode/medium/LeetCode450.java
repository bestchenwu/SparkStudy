package leetCode.medium;

import leetCode.TreeNode;

//给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的
//根节点的引用。
//
// 一般来说，删除节点可分为两个步骤：
//
//
// 首先找到需要删除的节点；
// 如果找到了，删除它。
//
//
// 说明： 要求算法时间复杂度为 O(h)，h 为树的高度。
//
// 示例:
//
//
//root = [5,3,6,2,4,null,7]
//key = 3
//
//    5
//   / \
//  3   6
// / \   \
//2   4   7
//
//给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
//
//一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
//
//    5
//   / \
//  4   6
// /     \
//2       7
//
//另一个正确答案是 [5,2,6,null,4,null,7]。
//
//    5
//   / \
//  2   6
//   \   \
//    4   7
public class LeetCode450 {

    /**
     * 如果key大于root,则说明删除的节点位于右子树
     * 如果key小于root,则说明删除的节点位于左子树
     * 如果key=root,如果root.left为空,返回root.right
     * 如果root.right为空,返回root.left
     * 最后寻找root.left的最右侧节点,将其right节点设置为root.right
     *
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return root;
        }
        if(key>root.val){
            root.right = deleteNode(root.right, key);
        }else if(key<root.val){
            root.left = deleteNode(root.left, key);
        }else{
            if(root.left == null){
                return root.right;
            }
            if(root.right == null){
                return root.left;
            }
            TreeNode tmp = root.left;
            while(tmp.right!=null){
                tmp = tmp.right;
            }
            tmp.right = root.right;
            return root.left;
        }
        return root;
    }
}

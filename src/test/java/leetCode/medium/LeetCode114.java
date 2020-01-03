package leetCode.medium;

import leetCode.TreeNode;

/**
 * 给定一个二叉树，原地将它展开为链表。
 * <p>
 * 例如，给定二叉树
 * <p>
 * 1
 * / \
 * 2   5
 * / \   \
 * 3   4   6
 * 将其展开为：
 * <p>
 * 1
 * \
 * 2
 * \
 * 3
 * \
 * 4
 * \
 * 5
 * \
 * 6
 */
public class LeetCode114 {

    private TreeNode newRoot = new TreeNode(0);
    private TreeNode head = newRoot;

    private void flattenHelp(TreeNode root) {
        if (root == null) {
            return;
        }
        newRoot.right = new TreeNode(root.val);
        if (root.left != null) {
            newRoot = newRoot.right;
            flattenHelp(root.left);
        }
        if (root.right != null) {
            newRoot = newRoot.right;
            flattenHelp(root.right);
        }
    }

    /**
     * todo:其实这里计算出来的root已经是符合要求了,不知道为何leetcode判定为错误
     *
     * @param root
     */
    @Deprecated
    public void flatten(TreeNode root) {
       flattenHelp(root);
        root = head.right;
        System.out.println(root);
    }

    private TreeNode pre;

    /**
     * 采用变形的后序遍历 右左根
     * 先访问到元素6,将6.right = pre
     * 然后命令pre = 6
     * 这时候再回到5  5.right = pre 也就是6
     *
     * @param root
     */
    public void flatten1(TreeNode root){
        if(root==null){
            return;
        }
        flatten1(root.right);
        flatten1(root.left);
        root.right = pre;
        root.left = null;
        pre = root;
    }

    public static void main(String[] args) {
        LeetCode114 leetCode114 = new LeetCode114();
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(3), new TreeNode(4)), new TreeNode(5, new TreeNode(6), new TreeNode(7)));
        //leetCode114.flatten(root);
        leetCode114.flatten1(root);
    }
}

package nowCode.medium;

import leetCode.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
//
// 如果指定节点没有对应的“下一个”节点，则返回null。
//
// 示例 1:
//
// 输入: root = [2,1,3], p = 1
//
//  2
// / \
//1   3
//
//输出: 2
//
// 示例 2:
//
// 输入: root = [5,3,6,2,4,null,null,1], p = 6
//
//      5
//     / \
//    3   6
//   / \
//  2   4
// /
//1
public class NowCode0406 {

    private List<TreeNode> res = new ArrayList<>();

    private void dfs(TreeNode root, TreeNode p) {
        if (root == null) {
            return;
        }
        dfs(root.left, p);
        res.add(root);
        dfs(root.right, p);
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        dfs(root, p);
        int i = 0;
        for (; i < res.size(); i++) {
            if (res.get(i) == p) {
                break;
            }
        }
        return i == res.size() - 1 ? null : res.get(i + 1);
    }

    public TreeNode inorderSuccessor1(TreeNode root, TreeNode p) {
        if(root == null){
            return root;
        }
        Stack<TreeNode> stack = new Stack<>();
        boolean flag = false;
        TreeNode cur = root;
        while(!stack.isEmpty() || cur!=null){
            while(cur!=null){
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode tmp = stack.pop();
            if(flag){
                return tmp;
            }
            if(tmp == p){
                flag = true;
            }
            cur = tmp.right;
        }
        return null;
    }

    @Test
    public void helpInorderSuccessor() {
        TreeNode root = new TreeNode(2);
        TreeNode p = new TreeNode(1);
        TreeNode right = new TreeNode(3);
        root.left = p;
        root.right = right;
        TreeNode res = inorderSuccessor1(root, p);
        System.out.println("res=" + res);
    }
}

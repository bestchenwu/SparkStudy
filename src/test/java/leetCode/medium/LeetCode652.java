package leetCode.medium;

import leetCode.TreeNode;
import org.junit.Test;

//给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
//
// 两棵树重复是指它们具有相同的结构以及相同的结点值。
//
// 示例 1：
//
//         1
//       / \
//      2   3
//     /   / \
//    4   2   4
//       /
//      4
//
//
// 下面是两个重复的子树：
//
//       2
//     /
//    4
//
//
// 和
//
//     4
//
//
// 因此，你需要以列表的形式返回上述重复子树的根结点。

import java.util.*;

public class LeetCode652 {

    private Map<String,Integer> map;
    private List<TreeNode> ans;

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        map = new HashMap<>();
        ans = new ArrayList<>();
        lookup(root);
        return ans;
    }

    private String lookup(TreeNode root){
        if(root == null){
            return "#";
        }
        String serial = root.val+","+lookup(root.left)+","+lookup(root.right);
        map.put(serial,map.getOrDefault(serial,0 )+1 );
        if(map.get(serial).equals(2)){
            ans.add(root);
        }
        return serial;
    }

    @Test
    public void testLookup(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2,4 ,null );
        root.right = new TreeNode(3,new TreeNode(2,4,null),new TreeNode(4));
        List<TreeNode> treeNodes = findDuplicateSubtrees(root);
        System.out.println(treeNodes);
    }
}

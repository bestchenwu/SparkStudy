package leetCode.medium;

//给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 K 。
//
// 返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。
//
//
//
//
//
//
// 示例 1：
//
// 输入：root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
//输出：[7,4,1]
//解释：
//所求结点为与目标结点（值为 5）距离为 2 的结点，
//值分别为 7，4，以及 1

import leetCode.TreeNode;
import org.junit.Test;

import java.util.*;


public class LeetCode863 {

    private Map<TreeNode, List<TreeNode>> nodeMap = new HashMap<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        if (k == 0) {
            return Arrays.asList(target.val);
        }
        Set<TreeNode> set = new HashSet<>();
        set.add(target);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(target);
        Map<TreeNode, Integer> nodeNumMap = new HashMap<>();
        nodeNumMap.put(target, 0);
        dfs(root);
        while (!queue.isEmpty()) {
            TreeNode tmp = queue.poll();
            int num = nodeNumMap.getOrDefault(tmp, 0);
            for (TreeNode node : nodeMap.getOrDefault(tmp, new ArrayList<>())) {
                if (!set.contains(node)) {
                    set.add(node);
                    queue.offer(node);
                    nodeNumMap.put(node, num + 1);
                }
            }
        }
        for (Map.Entry<TreeNode, Integer> entry : nodeNumMap.entrySet()) {
            if (entry.getValue().intValue() == k) {
                ans.add(entry.getKey().val);
            }
        }
        return ans;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            List<TreeNode> list1 = nodeMap.getOrDefault(root, new ArrayList<>());
            list1.add(root.left);
            nodeMap.put(root, list1);
            List<TreeNode> list2 = nodeMap.getOrDefault(root.left, new ArrayList<>());
            list2.add(root);
            nodeMap.put(root.left, list2);
        }
        if (root.right != null) {
            List<TreeNode> list1 = nodeMap.getOrDefault(root, new ArrayList<>());
            list1.add(root.right);
            nodeMap.put(root, list1);
            List<TreeNode> list2 = nodeMap.getOrDefault(root.right, new ArrayList<>());
            list2.add(root);
            nodeMap.put(root.right, list2);
        }
        dfs(root.left);
        dfs(root.right);
    }

    @Test
    public void testDistanceK() {
//        TreeNode root = new TreeNode(3);
//        root.left = new TreeNode(5, new TreeNode(6), new TreeNode(2, 7, 4));
//        root.right = new TreeNode(1, 0, 8);
        TreeNode root = new TreeNode(1);
        int k = 3;
        List<Integer> list = distanceK(root, root, 3);
        System.out.println("list:" + list);
    }
}

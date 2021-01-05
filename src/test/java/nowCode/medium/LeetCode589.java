package nowCode.medium;

//给定一个 N 叉树，返回其节点值的前序遍历。

import java.util.*;

public class LeetCode589 {

    public List<Integer> preorder(Node root) {
        if (root == null) {
            return new ArrayList<Integer>();
        }
        Deque<Node> queue = new LinkedList<>();
        queue.offer(root);
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            Node node = queue.pollLast();
            list.add(node.val);
            if (node.children != null && node.children.size() > 0) {
                Collections.reverse(node.children);
                for (Node child : node.children) {
                    queue.offer(child);
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        LeetCode589 leetCode589 = new LeetCode589();
        Node root = new Node(1);
        Node node1 = new Node(2);
        node1.children = Arrays.asList(new Node(3), new Node(4));
        Node node2 = new Node(5);
        node2.children = Arrays.asList(new Node(6));
        Node node3 = new Node(7);
        node3.children = Arrays.asList(new Node(8));
        root.children = Arrays.asList(node1, node2, node3);
        List<Integer> preorder = leetCode589.preorder(root);
        System.out.println(preorder);
    }
}

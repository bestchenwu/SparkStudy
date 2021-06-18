package leetCode.medium;

import leetCode.ListNode;
import org.junit.Test;

import java.util.*;

//给出一个以头节点 head 作为第一个节点的链表。链表中的节点分别编号为：node_1, node_2, node_3, ... 。
//
// 每个节点都可能有下一个更大值（next larger value）：对于 node_i，如果其 next_larger(node_i) 是 node_j.
//val，那么就有 j > i 且 node_j.val > node_i.val，而 j 是可能的选项中最小的那个。如果不存在这样的 j，那么下一个更大值为 0
// 。
//
// 返回整数答案数组 answer，其中 answer[i] = next_larger(node_{i+1}) 。
//
// 注意：在下面的示例中，诸如 [2,1,5] 这样的输入（不是输出）是链表的序列化表示，其头节点的值为 2，第二个节点值为 1，第三个节点值为 5 。
//
//
//
// 示例 1：
//
// 输入：[2,1,5]
//输出：[5,5,0]
//
//
// 示例 2：
//
// 输入：[2,7,4,3,5]
//输出：[7,0,5,5,0]
//
//
// 示例 3：
//
// 输入：[1,7,5,1,9,2,5,1]
//输出：[7,9,9,9,0,5,0,0]
//
//
//
//
// 提示：
//
//
// 对于链表中的每个节点，1 <= node.val <= 10^9
// 给定列表的长度在 [0, 10000] 范围内
public class LeetCode1019 {

    public int[] nextLargerNodes(ListNode head) {
        //建立一个单调递增栈
        //栈里面存放数组下标和数组值
        int index = 0;
        Stack<Node> stack = new Stack<>();
        //key是数组下标,value是下一个更大的元素
        Map<Integer, Integer> sortMap = new TreeMap<>();
        while (head != null) {
            while (!stack.isEmpty() && head.val > stack.peek().value) {
                Node node = stack.pop();
                sortMap.put(node.index, head.val);
            }
            stack.push(new Node(index, head.val));
            index += 1;
            head = head.next;
        }
        int len = sortMap.size() + stack.size();
        int[] result = new int[len];
        for (Map.Entry<Integer, Integer> entry : sortMap.entrySet()) {
            result[entry.getKey()] = entry.getValue();
        }
        while (!stack.isEmpty()) {
            result[stack.pop().index] = 0;
        }
        return result;
    }

    class Node {
        public int index;
        public int value;

        public Node(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    @Test
    public void testNextLargerNodes() {
        //2,1,5
        //ListNode listNode = new ListNode(2, new ListNode(1, new ListNode(5)));
        //2,7,4,3,5
        //ListNode listNode = new ListNode(2, new ListNode(7, new ListNode(4,new ListNode(3,new ListNode(5)))));
        // 输入：[1,7,5,1,9,2,5,1]
//输出：[7,9,9,9,0,5,0,0]
        //ListNode listNode = new ListNode(1, new ListNode(7, new ListNode(5, new ListNode(1, new ListNode(9,
        //        new ListNode(2, new ListNode(5, new ListNode(1))))))));
        ListNode listNode = new ListNode(1,new ListNode(3));
        int[] result = nextLargerNodes(listNode);
        System.out.println("result=" + Arrays.toString(result));
    }
}

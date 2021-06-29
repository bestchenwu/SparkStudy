package leetCode.medium;

import leetCode.ListNode;

import java.util.Stack;

//给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
//
// 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
//
//
//
// 进阶：
//
// 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
//
//
//
// 示例：
//
// 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
//输出：7 -> 8 -> 0 -> 7
public class LeetCode445 {

    public ListNode reverse(ListNode head) {
        ListNode root = null, tmp = null;
        while (head != null) {
            root = head;
            head = head.next;
            root.next = tmp;
            tmp = root;
        }
        return root;
    }

    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode l11 = reverse(l1);
        ListNode l22 = reverse(l2);
        ListNode root = new ListNode(-1);
        ListNode head = root;
        boolean updateFlag = false;
        while (l11 != null || l22 != null) {
            int value = (l11 != null ? l11.val : 0) + (l22 != null ? l22.val : 0) + (updateFlag ? 1 : 0);
            if (value >= 10) {
                value = value - 10;
                updateFlag = true;
            } else {
                updateFlag = false;
            }
            head.next = new ListNode(value);
            l11 = l11 != null ? l11.next : null;
            l22 = l22 != null ? l22.next : null;
            head = head.next;
        }
        if (updateFlag) {
            head.next = new ListNode(1);
            head = head.next;
        }
        return reverse(root.next);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        ListNode cur = null;
        int updateFlag = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            int value = (!stack1.isEmpty() ? stack1.pop() : 0) + (!stack2.isEmpty() ? stack2.pop() : 0) + updateFlag;
            updateFlag = value/10;
            value = value%10;
            ListNode tmp = new ListNode(value);
            tmp.next = cur;
            cur = tmp;
        }
        return cur;
    }
}

package leetCode.medium;

import leetCode.ListNode;
import org.junit.Test;

//给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
//
//
//
// 示例 1：
//
//
//输入：head = [1,2,3,4,5], k = 2
//输出：[4,5,1,2,3]
//
//
// 示例 2：
//
//
//输入：head = [0,1,2], k = 4
//输出：[2,0,1]
public class LeetCode61_20210802 {

    private int getLength(ListNode head) {
        int depth = 0;
        while (head != null) {
            depth += 1;
            head = head.next;
        }
        return depth;
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        ListNode tmp = head;
        int listLength = getLength(tmp);
        k = k % listLength;
        ListNode slow = head, fast = head;
        ListNode pre = fast;
        for (int i = 1; i <= listLength-k; i++) {
            pre = fast;
            fast = fast.next;
        }
        ListNode newHead = fast;
        pre.next = null;
        if (newHead == null) {
            return head;
        }
        ListNode newTmp = newHead;
        while (newTmp.next != null) {
            newTmp = newTmp.next;
        }
        newTmp.next = head;
        return newHead;
    }

    @Test
    public void testRotateRight() {
        ListNode head = ListNode.createListNode(1, 2, 3, 4, 5);
        int k = 5;
        ListNode newNode = rotateRight(head, k);
        System.out.println("newNode=" + newNode);
    }
}

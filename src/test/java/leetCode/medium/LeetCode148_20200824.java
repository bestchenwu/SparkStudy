package leetCode.medium;

import leetCode.ListNode;

/**
 * https://leetcode-cn.com/problems/sort-list/
 * <p>
 * 148. 排序链表
 * <p>
 * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2:
 * <p>
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 *
 * @author chenwu on 2020.8.24
 */
public class LeetCode148_20200824 {

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head, fast = head;
        if (fast.next.next == null) {
            //说明该链表只有两个节点 head 和head.next
            if (head.val > head.next.val) {
                swapValue(head, head.next);
            }
            return head;
        } else {
            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            ListNode newHalfNode = slow.next;
            slow.next = null;
            ListNode list1 = sortList(head);
            ListNode list2 = sortList(newHalfNode);
            //将两个有序链表list1 list2进行合并
            ListNode newNode = new ListNode();
            ListNode newNodeRoot = newNode;
            while (list1 != null || list2 != null) {
                //ListNode tmp = new ListNode();
                if (list2 == null) {
                    //tmp.val = list1.val;
                    newNodeRoot.next = list1;
                    list1 = list1.next;
                } else if (list1 == null) {
                    //tmp.val = list2.val;
                    newNodeRoot.next = list2;
                    list2 = list2.next;
                } else if (list1.val < list2.val) {
                    //tmp.val = list1.val;
                    newNodeRoot.next = list1;
                    list1 = list1.next;
                } else {
                    //tmp.val = list2.val;
                    newNodeRoot.next = list2;
                    list2 = list2.next;
                }
                newNodeRoot = newNodeRoot.next;
            }
            return newNode.next;
        }
    }

    private void swapValue(ListNode node1, ListNode node2) {
        int tmp = node1.val;
        node1.val = node2.val;
        node2.val = tmp;
    }

    public static void main(String[] args) {
//        ListNode node1 = new ListNode(4);
//        ListNode node2 = new ListNode(2);
//        ListNode node3 = new ListNode(1);
//        ListNode node4 = new ListNode(3);
//        ListNode node5 = new ListNode(0);
        ListNode node1 = new ListNode(-1);
        ListNode node2 = new ListNode(5);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(0);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        LeetCode148_20200824 leetCode148_20200824 = new LeetCode148_20200824();
        ListNode root = leetCode148_20200824.sortList(node1);
        System.out.println(root);
    }
}

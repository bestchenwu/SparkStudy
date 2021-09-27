package leetCode.medium;

import leetCode.ListNode;
import org.junit.Test;

//存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。
//
// 返回同样按升序排列的结果链表。
//
//
//
// 示例 1：
//
//
//输入：head = [1,2,3,3,4,4,5]
//输出：[1,2,5]
//
//
// 示例 2：
//
//
//输入：head = [1,1,1,2,3]
//输出：[2,3]
//
//
//
//
// 提示：
//
//
// 链表中节点数目在范围 [0, 300] 内
// -100 <= Node.val <= 100
// 题目数据保证链表已经按升序排列
public class LeetCode82_20210927 {

    public ListNode deleteDuplicates(ListNode head) {
        //dummyNode pre
        //if(head.val!=head.next.val) pre = pre.next,head = head.next
        //
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head, fast = head.next;
        ListNode dummyNode = new ListNode(0);
        ListNode pre = dummyNode;
        dummyNode.next = head;
        while (fast != null) {
            if (slow.val == fast.val) {
                while (fast != null && slow.val == fast.val) {
                    fast = fast.next;
                }
                pre.next = fast;
                slow = fast;
                fast = fast == null ? null : fast.next;
            } else {
                pre = pre.next;
                slow = slow.next;
                fast = fast.next;
            }
        }
        return dummyNode.next;
    }

    @Test
    public void testDeleteDuplicates() {
        ListNode head = ListNode.createListNode(1, 2, 3, 3, 4, 4, 5);
        ListNode newHead = deleteDuplicates(head);
        System.out.println(newHead);
    }
}

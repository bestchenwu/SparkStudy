package leetCode.medium;

import leetCode.ListNode;
import org.junit.Test;

//给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
//将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
//
// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
//
// 示例 1:
//
// 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
//
// 示例 2:
//
// 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
public class LeetCode143 {

    private ListNode reverseList(ListNode head) {
        ListNode root = null, tmp = null;
        while (head != null) {
            root = head;
            head = head.next;
            root.next = tmp;
            tmp = root;
        }
        return root;
    }

    public void reorderList1(ListNode head) {
        if(head == null || head.next == null){
            return;
        }
        ListNode slow = head,fast = head;
        while(fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode halfNode = slow.next;
        slow.next = null;
        //反转snow开头的链表
        ListNode reverseHead = reverseList(halfNode);
        ListNode newListNode = new ListNode(0);
        ListNode tmp = newListNode;
        while(head!=null || reverseHead!=null){
            if(head!=null){
                tmp.next = head;
                tmp = tmp.next;
                head = head.next;
            }
            if(reverseHead!=null){
                tmp.next = reverseHead;
                tmp = tmp.next;
                reverseHead = reverseHead.next;
            }
        }
        head = newListNode.next;
    }

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        //先将链表反转
        ListNode slow = head,fast = head;
        while(fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode halfNode = slow.next;
        slow.next = null;
        //反转fast的链表
        ListNode reverseFastNode = reverseList(halfNode);
        ListNode head1 = head;
        while(reverseFastNode!=null && head1!=null){
            ListNode tmp1 = head1.next;
            ListNode tmp2 = reverseFastNode;
            reverseFastNode = reverseFastNode.next;
            tmp2.next = tmp1;
            head1.next = tmp2;
            head1 = tmp1;
        }
    }

    @Test
    public void testReorderList() {
        //ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        ListNode head = ListNode.createListNode(1,2,3,4);
        reorderList1(head);
        System.out.println("list:" + head);
    }
}

package leetCode.medium;

import leetCode.ListNode;
import org.junit.Test;

//对链表进行插入排序。
//
//
//插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
//每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。
//
//
//
// 插入排序算法：
//
//
// 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
// 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
// 重复直到所有输入数据插入完为止。
//
//
//
//
// 示例 1：
//
// 输入: 4->2->1->3
//输出: 1->2->3->4
//
//
// 示例 2：
//
// 输入: -1->5->3->4->0
//输出: -1->0->3->4->5
public class LeetCode147 {

    private ListNode helpInsertSortList(ListNode head, ListNode newNode) {
        ListNode tmp = head;
        ListNode pre = tmp;
        while (tmp != null && tmp.val <= newNode.val) {
            pre = tmp;
            tmp = tmp.next;
        }
        //说明新节点是最小的节点
        if (tmp == head) {
            newNode.next = head;
            return newNode;
        } else if (tmp == null) {
            pre.next = newNode;
            return head;
        } else {
            pre.next = newNode;
            newNode.next = tmp;
            return head;
        }
    }

    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode slow = head, fast = head.next;
        slow.next = null;
        while (fast != null) {
            ListNode tmpFast = fast;
            fast = fast.next;
            tmpFast.next = null;
            slow = helpInsertSortList(slow, tmpFast);
        }
        return slow;
    }

    public ListNode insertionSortList1(ListNode head){
        if(head == null || head.next==null){
            return head;
        }
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode p = head;
        ListNode pre;
        //保证p和p之前的链表都是有序
        while(p!=null && p.next!=null){
            if(p.val<=p.next.val){
                p=p.next;
                continue;
            }
            //q就是要插入的节点
            ListNode q = p.next;
            pre = dummyHead;
            while(pre.next!=null && pre.next.val<q.val){
                pre = pre.next;
            }
            //要在pre和pre.next之间插入q
            p.next = q.next;
            q.next = pre.next;
            pre.next = q;
        }
        return dummyHead.next;
    }

    @Test
    public void testInsertionSortList() {
        ListNode head = ListNode.createListNode(4, 2, 1, 3);
        ListNode newHead = insertionSortList1(head);
        System.out.println("newHead=" + newHead);
    }
}

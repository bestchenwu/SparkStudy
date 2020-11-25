package leetCode.medium;

import leetCode.ListNode;

//删除链表中等于给定值 val 的所有节点。
//
// 示例:
//
// 输入: 1->2->6->3->4->5->6, val = 6
//输出: 1->2->3->4->5
//

/**
 * 删除链表中指定值的节点
 *
 * @author chenwu on 2020.11.25
 */
public class LeetCode203 {

    public ListNode removeElements(ListNode head, int val) {
        ListNode fast = head;
        ListNode snow = fast;
        while(fast!=null){
            if(fast.val == val){
                if(fast == head){
                    head = head.next;
                }else{
                    snow.next = fast.next;
                    fast = fast.next;
                }
            }else{
                snow = fast;
                fast = fast.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode p = new ListNode(6);
        ListNode p1 = new ListNode(2);
        ListNode p2 = new ListNode(6);
        ListNode p3 = new ListNode(3);
        ListNode p4 = new ListNode(4);
        ListNode p5 = new ListNode(5);
        ListNode p6 = new ListNode(6);
//                ListNode p = new ListNode(1);
//        ListNode p1 = new ListNode(2);
//        ListNode p2 = new ListNode(2);
//        ListNode p3 = new ListNode(1);
        p.next = p1;
        p1.next = p2;
        p2.next = p3;
        p3.next = p4;
        p4.next = p5;
        p5.next = p6;
        LeetCode203 leetCode203 = new LeetCode203();
        ListNode listNode = leetCode203.removeElements(p, 6);
        System.out.println(listNode);
    }
}

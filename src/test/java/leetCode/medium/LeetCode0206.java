package leetCode.medium;

import leetCode.ListNode;

//编写一个函数，检查输入的链表是否是回文的。
//
//
//
// 示例 1：
//
// 输入： 1->2
//输出： false
//
//
// 示例 2：
//
// 输入： 1->2->2->1
//输出： true
public class LeetCode0206 {

    private ListNode reverseNode(ListNode head){
        ListNode root = null;
        while(head!=null){
            ListNode next = head.next;
            head.next = root;
            root = head;
            head = next;
        }
        return root;
    }

    public boolean isPalindrome(ListNode head) {
        if(head == null){
            return true;
        }
        ListNode slow = head;
        ListNode fast = head;
        //使用快慢指针找到链表中间节点
        while(fast!=null && fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        //反转中间节点以后的链表
        ListNode halfNode = reverseNode(slow.next);
        while(halfNode!=null && head != null && head.val == halfNode.val){
            halfNode = halfNode.next;
            head = head.next;
        }
        return head == null && halfNode==null;
    }

    public static void main(String[] args) {
        ListNode root = new ListNode(1);
        LeetCode0206 leetCode0206 = new LeetCode0206();
    }

}

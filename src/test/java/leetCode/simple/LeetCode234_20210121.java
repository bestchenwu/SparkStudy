package leetCode.simple;

import leetCode.ListNode;

//请判断一个链表是否为回文链表。
//
// 示例 1:
//
// 输入: 1->2
//输出: false
//
// 示例 2:
//
// 输入: 1->2->2->1
//输出: true
public class LeetCode234_20210121 {

    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return false;
        }
        //首先找到链表的中点
        ListNode p = head;
        ListNode q = head;
        //1 2 3 4    p =1 ,q = 1, p =2 q = 3
        //1 2 3 4 5 p = 1 q = 1 p=2 q=3 p=3 q=5
        while (q != null && q.next != null && q.next.next != null) {
            p = p.next;
            q = q.next.next;
        }
        ListNode halfNode = p.next;
        //反转halfNode
        ListNode reverseNode = reverseList(halfNode);
        while (reverseNode != null && head.val == reverseNode.val) {
            head = head.next;
            reverseNode = reverseNode.next;
        }
        return reverseNode == null;
    }

    private ListNode reverseList(ListNode head){
        ListNode root = null;
        ListNode tmp = null;
        while(head!=null){
            tmp = head;
            head = head.next;
            tmp.next = root;
            root = tmp;
        }
        return root;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1,new ListNode(2,new ListNode(2,new ListNode(1))));
        LeetCode234_20210121 leetCode234_20210121 = new LeetCode234_20210121();
        boolean palindrome = leetCode234_20210121.isPalindrome(listNode);
        System.out.println("palindrome="+palindrome);
    }
}

package leetCode.medium;

import leetCode.ListNode;

/**
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 *
 * 删除链表的倒数第N个节点
 *
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 *
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 *
 * 给定的 n 保证是有效的。
 *
 * @author chenwu on 2020.8.14
 */
public class LeetCode19_20200814 {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head==null){
            return null;
        }
        ListNode head1 = head;
        ListNode fastNode = head1;
        ListNode prevNode = head;
        for(int i = 1;i<=n;i++){
            fastNode = fastNode.next;
        }
        if(fastNode==null){
            return head.next;
        }else{
            while(fastNode!=null){
                fastNode = fastNode.next;
                prevNode = head1;
                head1 = head1.next;
            }
            //head1就是要删除的
            prevNode.next = head1.next;
            return head;
        }
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(3);
        ListNode listNode3 = new ListNode(4);
        ListNode listNode4 = new ListNode(5);
        listNode.next = listNode1;
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        LeetCode19_20200814 leetCode19_20200814 = new LeetCode19_20200814();
        ListNode removeNode = leetCode19_20200814.removeNthFromEnd(listNode, 1);
        System.out.println(removeNode);
    }
}

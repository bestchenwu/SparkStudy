package leetCode.medium;

import leetCode.ListNode;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * <p>
 * 示例：
 * <p>
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * <p>
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 * <p>
 * 给定的 n 保证是有效的。
 * <p>
 * 进阶：
 * <p>
 * 你能尝试使用一趟扫描实现吗？
 */
public class LeetCode19 {

    //先反转链表
    public ListNode reverseNode(ListNode head, AtomicInteger length) {
        ListNode root = head;
        ListNode firstNode = null;
        ListNode currentNode = null;
        while (root != null) {
            firstNode = new ListNode(root.val);
            firstNode.next = currentNode;
            currentNode = firstNode;
            root = root.next;
            length.addAndGet(1);
        }
        return currentNode;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(n==0){
            return head;
        }
        AtomicInteger length = new AtomicInteger(0);
        ListNode reverseNode = reverseNode(head,length);
        //真实要寻找的步骤
        n = n%length.get();
        if(n==0){
            n = length.get();
        }
        int index = 1;
        ListNode head0 = reverseNode;
        ListNode prevNode = null;
        while(index<n){
            prevNode = head0;
            head0 = head0.next;
            index++;
        }
        if(head0==reverseNode){
            return reverseNode(reverseNode.next,length);
        }else if(head0==null){
            prevNode.next = null;
            return reverseNode(reverseNode,length);
        }else{
            prevNode.next = head0.next;
            return reverseNode(reverseNode,length);
        }
    }

    public static void main(String[] args) {
        LeetCode19 leetCode19 = new LeetCode19();
        ListNode listNode = new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4,new ListNode(5)))));
        ListNode root = leetCode19.removeNthFromEnd(listNode,2);
        System.out.println(root);
    }

}

package leetCode.simple;

import leetCode.ListNode;

/**
 * https://leetcode-cn.com/problems/linked-list-cycle/
 */
public class LeetCode141 {

    public boolean hasCycle(ListNode head) {
        if(head==null||head.next==null){
            return false;
        }
        ListNode fastNode = head;
        while(fastNode!=null && fastNode.next!=null ){
            head = head.next;
            fastNode = fastNode.next.next;
            if(fastNode!=null&&fastNode==head){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args){
        LeetCode141 leetCode = new LeetCode141();
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        //ListNode node3 = new ListNode(-4);
        node1.next = node2;
        //node2.next = node3;
        //node3.next = node2;
        System.out.println(leetCode.hasCycle(node1));
    }
}

package nowCode.medium;

import leetCode.ListNode;
import java.util.*;

public class LeetCode0201 {

    public ListNode removeDuplicateNodes(ListNode head) {
        if(head == null){
            return head;
        }
        Set<Integer> valueSet = new HashSet<>();
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null){
            int val = fast.val;
            if(valueSet.contains(val)){
                slow.next = fast.next;
            }else{
                valueSet.add(val);
                slow = fast;
            }
            fast = fast.next;
        }
        return head;
    }

    public static void main(String[] args) {
        LeetCode0201 leetCode0201 = new LeetCode0201();
        ListNode root = new ListNode(1);
        ListNode p1 = new ListNode(2);
        ListNode p2 = new ListNode(3);
        ListNode p3 = new ListNode(3);
        root.next = p1;
        p1.next = p2;
        p2.next = p3;
        ListNode listNode = leetCode0201.removeDuplicateNodes(root);
        System.out.println(listNode);
    }
}

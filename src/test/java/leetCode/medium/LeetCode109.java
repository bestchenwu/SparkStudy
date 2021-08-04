package leetCode.medium;

import leetCode.ListNode;
import leetCode.TreeNode;
import org.junit.Test;

public class LeetCode109 {

    public TreeNode sortedListToBST1(ListNode head) {
        if(head == null ){
            return null;
        }
        if(head.next == null){
            return new TreeNode(head.val);
        }else{
            ListNode slow = head,fast = head,pre = head;
            while(fast.next!=null && fast.next.next!=null){
                pre = slow;
                slow = slow.next;
                fast = fast.next.next;
            }
            TreeNode root;
            if(slow == head){
                //说明链表只有两个节点
                root = new TreeNode(slow.val);
                root.right = (slow.next==null?null:new TreeNode(slow.next.val));
            }else{
                //slow节点是root节点
                root = new TreeNode(slow.val);
                pre.next = null;
                root.left = sortedListToBST1(head);
                root.right = sortedListToBST1(slow.next);
            }

            return root;
        }
    }

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        //获取ListNode的中点
        //1->2->3->4->5
        ListNode pre = head, slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        if (head == slow) {
            //说明树只有一个节点 或者只有两个节点
            if (head.next == null) {
                return new TreeNode(head.val);
            } else {
                TreeNode root = new TreeNode(head.val);
                root.right = new TreeNode(head.next.val);
                return root;
            }
        } else {
            //以snow作为数目的中点
            pre.next = null;
            ListNode secondHalfNode = slow.next;
            slow.next = null;
            TreeNode root = new TreeNode(slow.val);
            root.left = sortedListToBST(head);
            root.right = sortedListToBST(secondHalfNode);
            return root;
        }
    }

    @Test
    public void testSortedListToBST() {
        //ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode head =  ListNode.createListNode(1,2,3,4);
        TreeNode root = sortedListToBST1(head);
        System.out.println("root:" + root);
    }
}

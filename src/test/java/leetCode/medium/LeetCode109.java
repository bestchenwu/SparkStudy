package leetCode.medium;

import leetCode.ListNode;
import leetCode.TreeNode;
import org.junit.Test;

public class LeetCode109 {

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
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        TreeNode root = sortedListToBST(head);
        System.out.println("root:" + root);
    }
}

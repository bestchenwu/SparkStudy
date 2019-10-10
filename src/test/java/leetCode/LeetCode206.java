package leetCode;

/**
 * https://leetcode-cn.com/problems/reverse-linked-list/
 *
 * @author  chenwu on 2019.10.10
 */
public class LeetCode206 {

    public ListNode reverseList(ListNode head) {
        if(head==null){
            return null;
        }
        ListNode currentNode = null;
        ListNode lastNode = null;
        while(head!=null){
            currentNode = new ListNode(head.val);
            currentNode.next = lastNode;
            lastNode = currentNode;
            head = head.next;
        }
        return currentNode;
    }
}

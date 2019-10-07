package leetCode;

/**
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/
 *
 * @author  chenwu on 2019.9.27
 */
public class LeetCode21 {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1==null&&l2==null){
            return null;
        }
        ListNode resultNode = new ListNode(0);
        ListNode rootNode = resultNode;
        while(l1!=null&&l2!=null){
            int l1Value = l1.val;
            int l2Value = l2.val;
            if(l1Value<=l2Value){
                resultNode.next = new ListNode(l1Value);
                l1 = l1.next;
            }else{
                resultNode.next = new ListNode(l2Value);
                l2 = l2.next;
            }
            resultNode = resultNode.next;
        }
        if(l1!=null){
            resultNode.next = l1;
        }else{
            resultNode.next = l2;
        }
        return rootNode.next;
    }

    public static void main(String[] args){
        LeetCode21 leetCode = new LeetCode21();
        ListNode l1 = new ListNode(1,new ListNode(2,new ListNode(4)));
        ListNode l2 = new ListNode(1,new ListNode(3,new ListNode(4)));
        ListNode result = leetCode.mergeTwoLists(l1,l2);
        System.out.println(result);
    }
}
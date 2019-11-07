package leetCode.simple;

import leetCode.ListNode;

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

    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        if(l1==null&&l2==null){
            return null;
        }
        ListNode root = new ListNode(0);
        ListNode first = root;
        while(l1!=null||l2!=null){
            if(l1==null){
                root.next = l2;
                l2 = l2.next;
            }else if(l2==null){
                root.next = l1;
                l1 = l1.next;
            }else{
                if(l1.val<=l2.val){
                    root.next = l1;
                    l1 = l1.next;
                }else{
                    root.next = l2;
                    l2 = l2.next;
                }
            }
            root = root.next;
        }


        return first.next;
    }



    public static void main(String[] args){
        LeetCode21 leetCode = new LeetCode21();
        ListNode l1 = new ListNode(1,new ListNode(2,new ListNode(4)));
        ListNode l2 = new ListNode(1,new ListNode(3,new ListNode(4)));
        ListNode result = leetCode.mergeTwoLists1(l1,l2);
        System.out.println(result);
    }
}

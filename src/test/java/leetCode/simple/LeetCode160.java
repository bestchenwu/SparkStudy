package leetCode.simple;

import leetCode.ListNode;

/**
 * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/submissions/
 */
public class LeetCode160 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA==null&&headB==null){
            return null;
        }
        if(headA==null&&headB!=null){
            return null;
        }
        if(headA!=null&&headB==null){
            return null;
        }
        ListNode pheadA = headA;
        ListNode pheadB = headB;
        while(pheadA!=pheadB){
            pheadA = pheadA!=null?pheadA.next:headB;
            pheadB = pheadB!=null?pheadB.next:headA;
        }
        return pheadA;
    }
}

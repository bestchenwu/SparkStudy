package leetCode.simple;

import leetCode.ListNode;

/**
 * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/submissions/
 */
public class LeetCode160 {


    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null){
            return null;
        }
        ListNode pa = headA;
        ListNode pb = headB;
        while(pa!=pb){
            pa = pa!=null?pa.next:headB;
            pb = pb!=null?pb.next:headA;
        }
        return pa;
    }

    public ListNode getIntersectionNode0(ListNode headA, ListNode headB) {
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

    public static void main(String[] args) {
        LeetCode160 leetCode160 = new LeetCode160();
//        ListNode listNodeA = new ListNode(4,new ListNode(1,new ListNode(8,new ListNode(4,new ListNode(5)))));
//        ListNode listNodeB = new ListNode(5,new ListNode(0,new ListNode(1,new ListNode(8,new ListNode(4,new ListNode(5))))));
        ListNode listNodeA = new ListNode(2,new ListNode(6,new ListNode(4)));
        ListNode listNodeB = new ListNode(1,new ListNode(5));

        ListNode intersectionNode = leetCode160.getIntersectionNode(listNodeA, listNodeB);
        System.out.println("val:"+intersectionNode.val);
    }
}

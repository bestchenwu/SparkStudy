package leetCode;

/**
 * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/submissions/
 */
public class LeetCode161 {
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
        while(true){
            if(pheadA.val==pheadB.val){
                return pheadA;
            }
            pheadA = pheadA.next!=null?pheadA.next:headB;
            pheadB = pheadB.next!=null?pheadB.next:headA;
            if(pheadA==null||pheadB==null){
                break;
            }
        }
        return null;
    }
}

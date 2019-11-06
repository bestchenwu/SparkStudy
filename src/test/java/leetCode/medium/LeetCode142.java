package leetCode.medium;

import leetCode.ListNode;

/**
 * https://leetcode-cn.com/problems/linked-list-cycle-ii/
 *
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 */
public class LeetCode142 {

    /**
     * 先找出两个节点相遇的节点
     *
     * 1->2->3->4
     *   |      |
     *   <-   <-
     *
     *  假定相遇在3节点
     *  那么A站在1节点，B一直走，如果走了两次走到3节点都没有找到A，
     *  则A往前挪一步
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if(head==null||head.next==null){
            return null;
        }
        ListNode low = head;
        ListNode fast = head;
        ListNode a = head;
        ListNode b;
        int index;
        while(fast!=null&&fast.next!=null){
            low = low.next;
            fast = fast.next.next;
            if(low==fast){
                index = 0;
                while(a!=null) {
                    b = a;
                    while (index < 2) {
                        b = b.next;
                        if (a == b) {
                            return a;
                        }
                        if (b == low) {
                            index += 1;
                        }
                    }
                    a = a.next;
                    index = 0;
                }
            }
        }
        return null;
    }

    /**
     * [-1,-7,7,-4,19,6,-9,-5,-2,-5]
     * 6
     * 这一组测试用例超出时间限制
     *
     * @param head
     * @return
     */
    //todo:
    public ListNode detectCycle1(ListNode head) {
        if(head==null||head.next==null){
            return null;
        }
        ListNode start = head;
        ListNode nextNode ;
        ListNode circleStartNode ;
        while(start!=null){
            nextNode = start.next;
            circleStartNode = nextNode;
            while(true){
                nextNode = nextNode.next;
                if(nextNode==null){
                    return null;
                }
                if(nextNode==start){
                    return start;
                }
                if(nextNode==circleStartNode){
                    break;
                }
            }
            start = start.next;
        }
        return null;
    }

    public static void main(String[] args) {
        LeetCode142 leetCode142 = new LeetCode142();
        ListNode listNode1 = new ListNode(3);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(0);
        ListNode listNode4 = new ListNode(-4);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next=null;
//        ListNode listNode1 = new ListNode(1);
//        ListNode listNode2 = new ListNode(2);
//        listNode1.next = listNode2;
//        listNode2.next = listNode1;
        ListNode node = leetCode142.detectCycle1(listNode1);
        System.out.println(node!=null?node.val:-999);
    }
}

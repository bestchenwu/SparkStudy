package leetCode.simple;

import leetCode.ListNode;

/**
 * 判断两个链表是否有相交<br/>
 * 可参见https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
 *
 * @author chenwu on 2019.9.10
 */
public class IntersectionListNode {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null||headB==null){
            return null;
        }
        ListNode reverseListNodeA = reverseListNode(headA);
        ListNode reverseListNodeB = reverseListNode(headB);
        //如果尾部节点不相同,则证明肯定不相交
        if(reverseListNodeA.val!=reverseListNodeB.val){
            return null;
        }
        ListNode previousNodeA = reverseListNodeA;
        ListNode previousNodeB = reverseListNodeB;
        while(reverseListNodeA!=null&&reverseListNodeB!=null){
            if(reverseListNodeA.next==null||reverseListNodeB.next==null){
                break;
            }
            if(reverseListNodeA.next.val!=reverseListNodeB.next.val){
                break;
            }
            previousNodeA = reverseListNodeA;
            previousNodeB = reverseListNodeB;
            reverseListNodeA = reverseListNodeA.next;
            reverseListNodeB = reverseListNodeB.next;
        }

        return previousNodeA;
    }

    public ListNode reverseListNode(ListNode node){
        if(node==null){
            return null;
        }
        ListNode rootNode = null;
        ListNode currentNode = null;
        while(node!=null){
            currentNode = new ListNode(node.val);
            currentNode.next=rootNode;
            node = node.next;
            rootNode=currentNode;
        }
        return currentNode;
    }

    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        if(headA==null||headB==null){
            return null;
        }
        ListNode pa = headA;
        ListNode pb = headB;
        while(pa!=pb){
            pa = (pa==null)?headB:pa.next;
            pb = (pb==null)?headA:pb.next;
        }
        return pa;
    }

    public static void main(String[] args){
        IntersectionListNode test = new IntersectionListNode();
        //ListNode list1 = new ListNode(4,new ListNode(1,new ListNode(8,new ListNode(4,new ListNode(5)))));
        ListNode list1 = new ListNode(0,new ListNode(9));
        ListNode list2 = new ListNode(3,new ListNode(2,new ListNode(10)));
        //ListNode list2 = new ListNode(5,new ListNode(0,new ListNode(1,new ListNode(8,new ListNode(4,new ListNode(5))))));
        System.out.println(test.getIntersectionNode1(list1,list2));
    }
}

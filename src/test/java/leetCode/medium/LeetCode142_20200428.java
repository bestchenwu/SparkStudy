package leetCode.medium;

import leetCode.ListNode;

import java.util.HashSet;
import java.util.Set;

public class LeetCode142_20200428 {

    public ListNode detectCycle(ListNode head) {
        if(head==null || head.next==null){
            return null;
        }
        //先寻找环形链表里是否可以相遇
        //如果不能相遇，则返回null
        //假定相遇在node2
        //那么我们从首个节点开始，
        //先假定首节点是相交节点，往后遍历，第一次访问node2的时候记cycle为1
        //如果再遍历到node2的时候,如果首部节点访问次数没有+1，那么说明首部节点不是相交节点
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                break;
            }
        }
        if(fast.next==null || fast.next.next==null){
            return null;
        }
        ListNode meetNode = slow;
        int cylcleNum = 0;
        int intercetionNodeVisitNum = 0;
        ListNode intercetionNode = head;
        while(intercetionNode!=null){
            ListNode tmp = intercetionNode.next;
            while(true){
                if(tmp == intercetionNode){
                    intercetionNodeVisitNum+=1;
                }
                if(tmp == meetNode){
                    cylcleNum +=1;
                }
                if(cylcleNum==2){
                    break;
                }
                tmp = tmp.next;
            }
            if(intercetionNodeVisitNum>=1){
                return intercetionNode;
            }else{
                intercetionNode = intercetionNode.next;
                cylcleNum = 0;
                intercetionNodeVisitNum = 0;
            }

        }
        return intercetionNode;

    }


    public ListNode detectCycle1(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while(head!=null){
            if(set.contains(head)){
                break;
            }
            set.add(head);
            head = head.next;
        }
        return head;
    }

    public ListNode detectCycle3(ListNode head) {
        if(head==null || head.next==null){
            return null;
        }
        //先寻找环形链表里是否可以相遇
        //如果不能相遇，则返回null
        //假定相遇在node2
        //那么我们从首个节点开始，
        //先假定首节点是相交节点，记为meetnode
        //
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                break;
            }
        }
        if(fast.next==null || fast.next.next==null){
            return null;
        }
        ListNode meetNode = slow;
        while(head!=meetNode){
            head = head.next;
            meetNode = meetNode.next;
        }
        return head;
    }

    public static void main(String[] args) {
        LeetCode142_20200428 leetCode142_20200428 = new LeetCode142_20200428();
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(-4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;
        ListNode listNode = leetCode142_20200428.detectCycle(node1);
        System.out.println(listNode.val);
    }
}

package leetCode.medium;

import leetCode.ListNode;

import java.util.List;

/**
 * https://leetcode-cn.com/problems/rotate-list/
 *
 * 旋转链表<br/>
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL<br/>
 *
 * @author chenwu on 2019.11.1
 */
public class LeetCode61 {

    /**
     * 这个方法实质上是把头节点往后挪K位，但是题目的意思是尾结点往后挪K位
     * k=0 1->2->3->4->5
     * k=1 5->1->2->3->4
     * k=2 4->5->1->2->3
     * 链表长度为5,尾结点挪动了2次，实际上头节点挪动了length-k%length次
     *
     * @param head
     * @param k
     * @return
     */
    @Deprecated
    public ListNode rotateRight(ListNode head, int k) {
        if(head==null||k==0){
            return head;
        }
        ListNode head0 = head;
        //计算链表的长度
        int length = 1;
        while(head.next!=null){
            head = head.next;
            length+=1;
        }
        head = head0;
        ListNode firstNode = head;
        int head_need_to_move_length = length-k%length;
        for(int i=1;i<=head_need_to_move_length;i++){
            head = head.next;
        }
        head=(head==null)?firstNode:head;
        ListNode finalNode = head;
        while(head.next!=null){
            head=head.next;
        }
        while(firstNode!=finalNode){
            head.next = firstNode;
            firstNode = firstNode.next;
            head = head.next;
        }
        head.next=null;
        return finalNode;
    }

    public static void main(String[] args) {
        LeetCode61 leetCode61 = new LeetCode61();
        ListNode listNode = new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4,new ListNode(5,null)))));
        //ListNode listNode = new ListNode(0,new ListNode(1,new ListNode(2,null)));
        //ListNode listNode = new ListNode(1,null);
        ListNode result = leetCode61.rotateRight(listNode,2);
        System.out.println(result);
    }
}

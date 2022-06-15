package leetCode.hard;

import leetCode.ListNode;
import org.junit.Test;

/**
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/reverse-nodes-in-k-group
 *
 * @author chenwu on 2022.6.8
 */


public class ListNode25_20220608 {

    private ListNode reverseList(ListNode head, int k){
        int index = 1;
        ListNode root=null,tmp=null;
        //head = 1 2 3 4 k = 3
        //index = 1 head = 1
        //index = 2 head = 2
        //index = 3 head = 3
        //index = 4 break
        while(index<=k){
            root = head;
            head = head.next;
            root.next = tmp;
            tmp = root;
            index+=1;
        }
        return root;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if(head==null || head.next == null || k<=1){
            return head;
        }
        //先获取链表的长度,同时定义一个头指针
        //判断链表的长度-头指针是否>k
        //记录反转前的尾部节点和准备反转的头节点，反转指定k长度的链表,返回新链表以及链表的下一个节点A
        //新链表的前置节点是反转前的尾部节点，并且将准备反转的头节点指向下一个节点
        ListNode dummyNode = new ListNode(-1);
        ListNode dummyTmp = dummyNode;
        ListNode head1 = head;
        int listLength = 0;
        while(head1!=null){
            head1=head1.next;
            listLength+=1;
        }
        int index = 0;
        ListNode curNode = head;
        while(curNode!=null){
            index+=1;
            curNode=curNode.next;
            if(index%k==0){
                //说明从head到现在满足了可以反转
                ListNode root = reverseList(head,k);
                dummyTmp.next = root;
                dummyTmp = head;
                if(listLength-index<k) {
                    //说明剩余元素不足k个
                    dummyTmp.next = curNode;
                    break;
                }else{
                    head = curNode;
                }
            }
        }
        return dummyNode.next;
    }

    @Test
    public void testReverseKGroup(){
        ListNode list = ListNode.createListNode(1,2,3,4,5,6,7,8);
        int k = 3;
        ListNode listNode = reverseKGroup(list, k);
        System.out.println(listNode);
    }
}

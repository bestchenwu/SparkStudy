package leetCode.medium;

import leetCode.ListNode;
import org.junit.Test;

//给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
//
// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
//
//
//
// 示例 1：
//
//
//输入：head = [1,2,3,4]
//输出：[2,1,4,3]
//
//
// 示例 2：
//
//
//输入：head = []
//输出：[]
//
//
// 示例 3：
//
//
//输入：head = [1]
//输出：[1]
//
//
//
//
// 提示：
//
//
// 链表中节点的数目在范围 [0, 100] 内
// 0 <= Node.val <= 100
//
//
//
//
// 进阶：你能在不修改链表节点值的情况下解决这个问题吗?（也就是说，仅修改节点本身。）
public class LeetCode24 {

    public ListNode swapPairs(ListNode head) {
        ListNode pre = head,nextNode=head,tmp = head;
        ListNode nextHead = null;
        while(tmp!=null){
            if(tmp.next==null){
                return head;
            }else{
                pre = tmp;
                nextNode = tmp.next;
                nextHead = nextNode.next;
                //将pre和nextNode的值交换
                int tmpVal = pre.val;
                pre.val = nextNode.val;
                nextNode.val = tmpVal;
            }
            tmp = nextHead;
        }
        return head;
    }

    public ListNode swapPairs0(ListNode head) {
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        ListNode temp = newHead;
        while(temp.next!=null && temp.next.next!=null){
            ListNode start = temp.next;
            ListNode end = temp.next.next;
            temp.next = end;
            start.next = end.next;
            end.next = start;
            temp = start;
        }
        return newHead.next;
    }

    @Test
    public void testSwapPairs(){
        //ListNode head = new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4))));
        //head  = null;
        ListNode head = new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4,new ListNode(5)))));
        ListNode listNode = swapPairs0(head);
        System.out.println("listNode="+listNode);
    }
}

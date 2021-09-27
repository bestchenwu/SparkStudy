package leetCode.medium;

import leetCode.ListNode;
import org.junit.Test;

//给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
//
// 你应当 保留 两个分区中每个节点的初始相对位置。
//
//
//
// 示例 1：
//
//
//输入：head = [1,4,3,2,5,2], x = 3
//输出：[1,2,2,4,3,5]
//
//
// 示例 2：
//
//
//输入：head = [2,1], x = 2
//输出：[1,2]
//
//
//
//
// 提示：
//
//
// 链表中节点的数目在范围 [0, 200] 内
// -100 <= Node.val <= 100
// -200 <= x <= 200
public class LeetCode86 {

    public ListNode partition(ListNode head, int x) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode smallHead = new ListNode(0);
        ListNode smallTail = smallHead;
        ListNode bigHead = new ListNode(0);
        ListNode bigTail = bigHead;
        while(head!=null){
            if(head.val<x){
                //归到小链表里面
                smallTail.next = head;
                smallTail = smallTail.next;
            }else{
                bigTail.next = head;
                bigTail = bigTail.next;
            }
            head = head.next;
        }
        smallTail.next = bigHead.next;
        bigTail.next = null;
        return smallHead.next;
    }

    @Test
    public void testPartition(){
        ListNode head = ListNode.createListNode(1,4,3,2,5,2);
        int x = 3;
        ListNode newHead = partition(head, x);
        System.out.println(newHead);
    }
}

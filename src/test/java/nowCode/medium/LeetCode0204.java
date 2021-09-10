package nowCode.medium;

import leetCode.ListNode;
import org.junit.Test;

//给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
//
// 你不需要 保留 每个分区中各节点的初始相对位置。
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
//
// Related Topics 链表 双指针 👍 67 👎 0
public class LeetCode0204 {

    public ListNode partition(ListNode head, int x) {
        if(head == null){
            return head;
        }
        ListNode small = new ListNode(0);
        ListNode small1 = small;
        ListNode big = new ListNode(0);
        ListNode big1 = big;
        ListNode medium = new ListNode(0);
        ListNode medium1 = medium;
        while(head!=null){
            if(head.val<x){
                small1.next = new ListNode(head.val);
                small1 = small1.next;
            }else if(head.val == x){
                medium1.next = new ListNode(head.val);
                medium1 = medium1.next;
            }else{
                big1.next = new ListNode(head.val);
                big1 = big1.next;
            }
            head = head.next;
        }
        medium1.next = big.next;
        small1.next = medium.next;
        return small.next;
    }

    @Test
    public void testPartition(){
        //ListNode head = ListNode.createListNode(1,4,3,2,5,2);
        ListNode head = new ListNode(1);
        int x = 2;
        ListNode newNode = partition(head, x);
        System.out.println(newNode);
    }
}

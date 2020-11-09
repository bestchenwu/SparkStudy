package leetCode.medium;

import leetCode.ListNode;

//反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
//
// 说明:
//1 ≤ m ≤ n ≤ 链表长度。
//
// 示例:
//
// 输入: 1->2->3->4->5->NULL, m = 2, n = 4
//输出: 1->4->3->2->5->NULL

/**
 * 反转从位置 m 到 n 的链表<br/>
 * https://leetcode-cn.com/problems/reverse-linked-list-ii/
 *
 * @author chenwu on 2020.11.9
 */
public class LeetCode92 {

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null){
            return null;
        }
        ListNode finalNode = new ListNode(-1);
        ListNode finalTmpNode = finalNode;
        int start = 1;
        while(start<m){
            finalTmpNode.next = head;
            head = head.next;
            finalTmpNode = finalTmpNode.next;
            start++;
        }
        finalTmpNode.next = null;
        //从这个时候 head开始以后的节点到n都要反转
        ListNode rootNode = new ListNode(head.val);
        //同时要把当前节点记下来,因为将来需要把链表剩余未反转的部分连接到该节点上
        ListNode lastNode = rootNode;
        while(start<n && head!=null){
            head = head.next;
            ListNode tmp = new ListNode(head.val);
            tmp.next = rootNode;
            rootNode = tmp;
            start++;
        }
        finalTmpNode.next = rootNode;
        //此时head位于n节点上,需要将head下移一位
       if(head!=null){
           lastNode.next = head.next;
       }
        return finalNode.next;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        LeetCode92 leetCode92 = new LeetCode92();
        ListNode resultNode = leetCode92.reverseBetween(listNode1, 2, 2);
        System.out.println(resultNode);
    }
}

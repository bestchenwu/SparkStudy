package leetCode.medium;

import leetCode.ListNode;
import org.junit.Test;

//给你链表的头节点 head 和一个整数 k 。
//
// 交换 链表正数第 k 个节点和倒数第 k 个节点的值后，返回链表的头节点（链表 从 1 开始索引）。
//
//
//
// 示例 1：
//
//
//输入：head = [1,2,3,4,5], k = 2
//输出：[1,4,3,2,5]
//
//
// 示例 2：
//
//
//输入：head = [7,9,6,6,7,8,3,0,9,5], k = 5
//输出：[7,9,6,6,8,7,3,0,9,5]
//
//
// 示例 3：
//
//
//输入：head = [1], k = 1
//输出：[1]
//
//
// 示例 4：
//
//
//输入：head = [1,2], k = 1
//输出：[2,1]
//
//
// 示例 5：
//
//
//输入：head = [1,2,3], k = 2
//输出：[1,2,3]
//
//
//
//
// 提示：
//
//
// 链表中节点的数目是 n
// 1 <= k <= n <= 105
// 0 <= Node.val <= 100
public class LeetCode1721 {

    public ListNode swapNodes(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode slow = dummyHead;
        for (int i = 1; i <= k; i++) {
            slow = slow.next;
        }
        //slow节点就是正数第K个节点
        ListNode fast = slow;
        ListNode slow1 = dummyHead;
        while (fast != null) {
            slow1 = slow1.next;
            fast = fast.next;
        }
        //slow1就是倒数第K个节点
        int tmp = slow.val;
        slow.val = slow1.val;
        slow1.val = tmp;
        return dummyHead.next;
    }

    @Test
    public void testSwapNodes(){
        ListNode head = ListNode.createListNode(1,2,3,4,5);
        int k = 3;
        ListNode result = swapNodes(head, k);
        System.out.println(result);
    }
}

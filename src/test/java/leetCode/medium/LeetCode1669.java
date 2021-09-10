package leetCode.medium;

import leetCode.ListNode;
import org.junit.Test;

//给你两个链表 list1 和 list2 ，它们包含的元素分别为 n 个和 m 个。
//
// 请你将 list1 中第 a 个节点到第 b 个节点删除，并将list2 接在被删除节点的位置。
//
// 下图中蓝色边和节点展示了操作后的结果：
//
// 请你返回结果链表的头指针。
//
//
//
// 示例 1：
//
//
//
//
//输入：list1 = [0,1,2,3,4,5], a = 3, b = 4, list2 = [1000000,1000001,1000002]
//输出：[0,1,2,1000000,1000001,1000002,5]
//解释：我们删除 list1 中第三和第四个节点，并将 list2 接在该位置。上图中蓝色的边和节点为答案链表。
//
//
// 示例 2：
//
//
//输入：list1 = [0,1,2,3,4,5,6], a = 2, b = 5, list2 = [1000000,1000001,1000002,100
//0003,1000004]
//输出：[0,1,1000000,1000001,1000002,1000003,1000004,6]
//解释：上图中蓝色的边和节点为答案链表。
// 提示：
//
//
// 3 <= list1.length <= 104
// 1 <= a <= b < list1.length - 1
// 1 <= list2.length <= 104
//
public class LeetCode1669 {

    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        int i = 0;
        ListNode head = list1;
        ListNode pre = list1;
        while (i < a) {
            pre = head;
            head = head.next;
            i++;
        }
        while (i <= b) {
            head = head.next;
            i++;
        }
        ListNode lastNode = head;
        pre.next = list2;
        ListNode tmp = list2;
        while (tmp.next != null) {
            tmp = tmp.next;
        }
        tmp.next = lastNode;
        return list1;
    }

    @Test
    public void testMergeInBetween() {
        ListNode list1 = ListNode.createListNode(0,1,2,3,4,5,6);
        int a = 2;
        int b = 5;
        ListNode list2 = ListNode.createListNode(1000000,1000001,1000002,1000003,1000004);
        ListNode listNode = mergeInBetween(list1, a, b, list2);
        System.out.println("listNode=" + listNode);
    }
}

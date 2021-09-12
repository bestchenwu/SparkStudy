package leetCode.medium;

import leetCode.ListNode;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

//给你一个链表的头节点 head，请你编写代码，反复删去链表中由 总和 值为 0 的连续节点组成的序列，直到不存在这样的序列为止。
//
// 删除完毕后，请你返回最终结果链表的头节点。
//
//
//
// 你可以返回任何满足题目要求的答案。
//
// （注意，下面示例中的所有序列，都是对 ListNode 对象序列化的表示。）
//
// 示例 1：
//
// 输入：head = [1,2,-3,3,1]
//输出：[3,1]
//提示：答案 [1,2,1] 也是正确的。
//
//
// 示例 2：
//
// 输入：head = [1,2,3,-3,4]
//输出：[1,2,4]
//
//
// 示例 3：
//
// 输入：head = [1,2,3,-3,-2]
//输出：[1]
import java.util.*;

public class LeetCode1171 {


    public ListNode removeZeroSumSublists(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode pre = dummyHead;
        while (pre != null) {
            ListNode p = pre.next;
            int sum = 0;
            while (p != null) {
                sum += p.val;
                if (sum == 0) {
                    pre.next = p.next;
                    break;
                } else {
                    p = p.next;
                }
            }
            if (p == null) {
                pre = pre.next;
            }
        }
        return dummyHead.next;
    }

    @Test
    public void testLeetCode1171() {
        ListNode head = ListNode.createListNode(1,2,3,-3,-2);
        ListNode newNode = removeZeroSumSublists(head);
        System.out.println(newNode);
    }
}

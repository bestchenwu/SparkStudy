package leetCode.medium;

import leetCode.ListNode;

import java.util.Random;

//给定一个单链表，随机选择链表的一个节点，并返回相应的节点值。保证每个节点被选的概率一样。
//
// 进阶:
//如果链表十分大且长度未知，如何解决这个问题？你能否使用常数级空间复杂度实现？
//
// 示例:
//
//
//// 初始化一个单链表 [1,2,3].
//ListNode head = new ListNode(1);
//head.next = new ListNode(2);
//head.next.next = new ListNode(3);
//Solution solution = new Solution(head);
//
//// getRandom()方法应随机返回1,2,3中的一个，保证每个元素被返回的概率相等。
public class LeetCode382 {

    private ListNode head;
    private Random random = new Random(47);

    public LeetCode382(ListNode head) {
        this.head = head;
    }

    public int getRandom() {
        if (head == null) {
            return 0;
        }
        ListNode first = head;
        int res = first.val;
        ListNode cur = first.next;
        int i = 2;
        //始终保持池子里面只有2个元素
        while (cur != null) {
            int j = random.nextInt(i);
            if (j == 0) {
                res = cur.val;
            }
            i++;
            cur = cur.next;
        }
        //第一个元素还保留在里面的概率是1 * 1/2 * 2/3 = 1/3
        return res;
    }
}

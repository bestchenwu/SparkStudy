//给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
//
// k 是一个正整数，它的值小于或等于链表的长度。
//
// 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
//
// 进阶：
//
//
// 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
// 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
//
//
//
//
// 示例 1：
//
//
//输入：head = [1,2,3,4,5], k = 2
//输出：[2,1,4,3,5]
//
//
// 示例 2：
//
//
//输入：head = [1,2,3,4,5], k = 3
//输出：[3,2,1,4,5]
//
//
// 示例 3：
//
//
//输入：head = [1,2,3,4,5], k = 1
//输出：[1,2,3,4,5]
//
//
// 示例 4：
//
//
//输入：head = [1], k = 1
//输出：[1]
//
//
//
//
//
// 提示：
//
//
// 列表中节点的数量在范围 sz 内
// 1 <= sz <= 5000
// 0 <= Node.val <= 1000
// 1 <= k <= sz
//
// Related Topics 递归 链表 👍 1566 👎 0
package leetCode.hard;

import leetCode.ListNode;
import org.junit.Test;

class Pair{
    public ListNode first;
    public ListNode second;
    public Pair(ListNode first,ListNode second){
        this.first = first;
        this.second = second;
    }
}

public class LeetCode25 {

    private int remain = 0;

    public Pair reverse(ListNode node,int k){
        ListNode root = null,tmp = null;
        int index = 1;
        for(;index<=k;index++){
            root = node;
            node = node.next;
            root.next = tmp;
            tmp = root;
        }
        return new Pair(root,node);
    }

    private int getListLength(ListNode head){
        int i = 0;
        while(head!=null){
            i+=1;
            head = head.next;
        }
        return i;
    }

    public ListNode reverseKGroup(ListNode head,int k){
        ListNode tmpNode = head;
        remain = getListLength(tmpNode);
        if(remain<k){
            return head;
        }
        ListNode res = helpReverseKGroup(head,k);
        return res;
    }

    private ListNode helpReverseKGroup(ListNode head, int k){
        if(head == null || remain < k){
            return head;
        }
        ListNode dummyNode = new ListNode();
        //第一项是反转后的链表,第二项是反转完成后剩余的链表节点
        Pair pair = reverse(head,k);
        dummyNode.next = pair.first;
        remain-=k;
        head.next = helpReverseKGroup(pair.second,k);
        return dummyNode.next;
    }

    @Test
    public void testReverseKGroup(){
        ListNode head = ListNode.createListNode(1,2,3,4,5);
        int k = 2;
        ListNode res = reverseKGroup(head, k);
        System.out.println(res);
    }
}

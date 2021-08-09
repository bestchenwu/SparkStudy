package leetCode.medium;

import leetCode.ListNode;
import org.junit.Test;

/**
 * //给定一个头结点为 root 的链表, 编写一个函数以将链表分隔为 k 个连续的部分。
 * //
 * // 每部分的长度应该尽可能的相等: 任意两部分的长度差距不能超过 1，也就是说可能有些部分为 null。
 * //
 * // 这k个部分应该按照在链表中出现的顺序进行输出，并且排在前面的部分的长度应该大于或等于后面的长度。
 * //
 * // 返回一个符合上述规则的链表的列表。
 * //
 * // 举例： 1->2->3->4, k = 5 // 5 结果 [ [1], [2], [3], [4], null ]
 * //
 * // 示例 1：
 * //
 * //
 * //输入:
 * //root = [1, 2, 3], k = 5
 * //输出: [[1],[2],[3],[],[]]
 * //解释:
 * //输入输出各部分都应该是链表，而不是数组。
 * //例如, 输入的结点 root 的 val= 1, root.next.val = 2, \root.next.next.val = 3, 且 root.ne
 * //xt.next.next = null。
 * //第一个输出 output[0] 是 output[0].val = 1, output[0].next = null。
 * //最后一个元素 output[4] 为 null, 它代表了最后一个部分为空链表。
 * //
 * //
 * // 示例 2：
 * //
 * //
 * //输入:
 * //root = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], k = 3
 * //输出: [[1, 2, 3, 4], [5, 6, 7], [8, 9, 10]]
 * //解释:
 * //输入被分成了几个连续的部分，并且每部分的长度相差不超过1.前面部分的长度大于等于后面部分的长度。
 *
 * https://blog.csdn.net/gx17864373822/article/details/104009288
 *
 * @author chenwu on 2021.8.9
 */
public class LeetCode725 {

    private int getLength(ListNode head){
        int length = 0;
        while(head!=null){
            length+=1;
            head = head.next;
        }
        return length;
    }

    public ListNode[] splitListToParts(ListNode head, int k){
        if(head == null){
            return new ListNode[0];
        }
        int length = getLength(head);
        //size是每段的节点个数
        int size = length/k;
        //mod表示余数
        int mod = length%k;
        ListNode pre = head;
        ListNode[] list = new ListNode[k];
        for(int i = 0;i<k;i++){
            list[i]=head;
            //给前mod段加一个节点,这样正好把余数分配均匀
            for(int j = 0;j<size+(i<mod?1:0);j++){
                pre = head;
                head = head.next;
            }
            if(pre!=null){
                pre.next = null;
            }
        }
        return list;
    }

    @Test
    public void testSplitListToParts(){
        ListNode head = ListNode.createListNode(1,2,3,4,5,6,7,8,9,10);
        int k = 3;
        ListNode[] listNodes = splitListToParts(head, k);
        for(ListNode listNode : listNodes){
            System.out.println(listNode);
        }
    }

}

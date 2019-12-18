package leetCode.medium;

import leetCode.ListNode;

/**
 * 每隔k步反转链表,例如
 * 1->2->3->4->5  k = 2
 * 反转后得到2->1->3->4->5
 *
 * 1->2->3->4->5->6->7->8 k=3
 * 反转后得到3->2->1->4->5->6->8->7
 *
 * 相似的题目可以参考:{@link leetCode.simple.LeetCode541}
 *
 * @author chenwu on 2019.12.18
 */
public class ReverseListNodeByK {

    public ListNode reverseListNode(ListNode root,int k){
        ListNode resultNode = new ListNode(0);
        ListNode finalResultNode = resultNode;
        int start = 0;
        boolean isReverse = true;
        ListNode tmp = null;
        ListNode head = null;
        int end = start;
        while(root!=null){
            head = new ListNode(root.val);
            if(isReverse){
                if(tmp == null){
                    tmp = head;
                    tmp.next = null;
                }else{
                    head.next = tmp;
                    tmp = head;
                }
            }else{
                if(tmp == null){
                    tmp = head;
                    tmp.next = null;
                }else{
                    ListNode tmp1 = tmp;
                    while(tmp1.next!=null){
                        tmp1=tmp.next;
                    }
                    tmp1.next = head;
                }
            }
            end+=1;
            root = root.next;
            if(end-start >=k){
                while(tmp!=null){
                    resultNode.next = tmp;
                    tmp = tmp.next;
                    resultNode = resultNode.next;
                }
                isReverse = isReverse?false:true;
                start = end;
                end = start;
            }
        }
        if(tmp!=null){
            while(tmp!=null){
                resultNode.next = tmp;
                resultNode = resultNode.next;
                tmp = tmp.next;
            }
        }
        return finalResultNode.next;
    }

    public static void main(String[] args) {
        ReverseListNodeByK reverseListNodeByK = new ReverseListNodeByK();
        ListNode listNode = new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4,new ListNode(5,new ListNode(6,new ListNode(7,new ListNode(8))))))));
        ListNode result = reverseListNodeByK.reverseListNode(listNode,3);
        System.out.println(result);
    }
}

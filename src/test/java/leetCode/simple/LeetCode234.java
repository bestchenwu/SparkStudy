package leetCode.simple;

import leetCode.ListNode;

/**
 * https://leetcode-cn.com/problems/palindrome-linked-list
 */
public class LeetCode234 {

    public boolean isPalindrome0(ListNode head) {
        if(head==null||head.next==null){
            return false;
        }
        //计算链表的长度
        int len = 0;
        ListNode tmp = head;
        while(tmp!=null){
            len+=1;
            tmp = tmp.next;
        }
        //反转前半段链表
        ListNode preRoot = null;//前半段链表的头节点
        ListNode preLast = null;//前半段链表的尾结点
        tmp = head;//将指针仍然指向头节点
        for(int i=0;i<len/2;i++){
            preRoot = new ListNode(tmp.val);
            preRoot .next = preLast;
            preLast = preRoot;
            tmp = tmp.next;
        }
        //如果是奇数个数的链表,则后半段链表的头节点需要向后挪一位
        ListNode afterRoot = tmp;
        if(len%2==1){
            afterRoot = tmp.next;
        }
        while(preRoot!=null&&afterRoot!=null){
            if(preRoot.val!=afterRoot.val){
                return false;
            }
            preRoot = preRoot.next;
            afterRoot = afterRoot.next;
        }
        return afterRoot==null&&preRoot==null;
    }

    public boolean isPalindrome(ListNode head) {
        if(head==null ){
            return false;
        }
        if(head.next==null){
            return true;
        }
        ListNode slowNode = head;
        ListNode fastNode = head;
        while(fastNode.next!=null&& fastNode.next.next!=null){
            fastNode = fastNode.next.next;
            slowNode = slowNode.next;
        }
        ListNode secondHalfRootNode = slowNode.next;
        slowNode.next = null;
        //反转后半截链表
        ListNode reverseNode = null;
        while(secondHalfRootNode!=null){
            ListNode tmp = secondHalfRootNode;
            secondHalfRootNode = secondHalfRootNode.next;
            tmp.next = reverseNode;
            reverseNode = tmp;
        }
        while(reverseNode!=null && head!=null && reverseNode.val==head.val){
            reverseNode = reverseNode.next;
            head = head.next;
        }
        return reverseNode==null?true:false;
    }


    public static void main(String[] args){
        LeetCode234 leetCode234 = new LeetCode234();
        //ListNode node = new ListNode(1,new ListNode(2));
        //ListNode node = new ListNode(1,new ListNode(2,new ListNode(2,new ListNode(1))));
        //ListNode node = new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(2,new ListNode(1)))));
        ListNode node = new ListNode(1,new ListNode(2,new ListNode(2,new ListNode(2,new ListNode(1)))));
        //ListNode node = new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(3,new ListNode(2,new ListNode(1))))));
        boolean result = leetCode234.isPalindrome(node);
        System.out.println(result);
    }
}

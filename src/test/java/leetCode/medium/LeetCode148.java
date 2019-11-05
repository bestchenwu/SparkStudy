package leetCode.medium;

import leetCode.ListNode;

/**
 * https://leetcode-cn.com/problems/sort-list/
 *
 * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 *
 * @author chenwu on 2019.11.5
 */
public class LeetCode148 {

    /**
     * 拿root和newNode进行比较
     *
     * @param newNode
     * @author chenwu on 2019.11.5
     */
    private ListNode insertNewNode(ListNode root,ListNode newNode){
        if(newNode.val<root.val){
            ListNode temp = root;
            root = new ListNode(newNode.val);
            root.next = temp;
            return root;
        }else{
            ListNode firstNode = root;
            ListNode previous = root;
            while(root!=null&&root.val<newNode.val){
                previous = root;
                root = root.next;
            }
            //在previous和root之间插入newNode
            previous.next = new ListNode(newNode.val);
            previous.next.next = root;
            return firstNode;
        }
    }

    public ListNode sortList(ListNode head) {
        if(head==null){
            return null;
        }
        ListNode root = new ListNode(head.val);
        if(head.next==null){
            return root;
        }
        ListNode newNode = head.next;
        while(newNode!=null){
            root = insertNewNode(root,newNode);
            newNode = newNode.next;
        }
        return root;
    }

    public ListNode sortList1(ListNode head) {
        if(head==null){
            return null;
        }
        ListNode root = head;
        if(head.next==null){
            return root;
        }
        ListNode newNode = head.next;
        ListNode firstNode = head;
        ListNode previousNode = null;
        while(newNode!=null){
            if(root.val>newNode.val){
                newNode.next = root;
                root = newNode;
            }else{
                firstNode = root;
                while(root!=null&&root.val<newNode.val){
                    previousNode = root;
                    root = root.next;
                }
                previousNode.next = newNode;
                newNode.next = root;
            }
            newNode = newNode.next;
        }
        return firstNode;
    }


    public static void main(String[] args) {
        LeetCode148 leetCode148 = new LeetCode148();
        ListNode listNode = new ListNode(-1,new ListNode(5,new ListNode(3,new ListNode(4,new ListNode(0,null)))));
        ListNode node = leetCode148.sortList1(listNode);
        System.out.println(node);
    }
}

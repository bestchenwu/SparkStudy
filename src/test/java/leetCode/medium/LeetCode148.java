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

    class Pair<T>{
        T first;
        T second;
        public Pair(T first,T second){
            this.first = first;
            this.second = second;
        }
    }

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

    /**
     * 始终在头节点和尾部节点之间插入一个新的节点
     *
     * @param root
     * @param newNode
     * @return
     */
    private Pair<ListNode> insertList(ListNode root,ListNode newNode){
        ListNode firstNode = root;
        ListNode previousNode = null;
        while(firstNode!=null&&firstNode.val<=newNode.val){
            previousNode = firstNode;
            firstNode = firstNode.next;
        }
        if(firstNode==null){
            root.next = newNode.next;
            previousNode.next = newNode;
            newNode.next = null;
            return new Pair<>(root,root.next!=null?root.next.next:null);
        }
        if(previousNode==null){
            ListNode temp = root;
            root.val = newNode.val;
            root.next = temp;
            newNode = temp.next;
            return new Pair<>(root,newNode);
        }else{
            //这个时候是拿newNode.val和firstNode的val进行交换
            int tempValue = newNode.val;
            newNode.val = firstNode.val;
            firstNode.val = tempValue;
            newNode = firstNode.next;
            return new Pair<>(root,newNode);
        }
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
        Pair<ListNode> pair = null;
        do{
            pair = insertList(root,newNode);
            root = pair.first;
            newNode = pair.second;
        }while(newNode!=null);
        return root;
    }


    public static void main(String[] args) {
        LeetCode148 leetCode148 = new LeetCode148();
        ListNode listNode = new ListNode(-1,new ListNode(5,new ListNode(3,new ListNode(4,new ListNode(0,null)))));
        ListNode node = leetCode148.sortList1(listNode);
        System.out.println(node);
    }
}

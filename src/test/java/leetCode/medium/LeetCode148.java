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

    public ListNode sortList2(ListNode head) {
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
            //这个时候是拿previousNode.val和newNode的val进行交换
            int tempValue = previousNode.val;
            newNode.val = previousNode.val;
            firstNode.val = tempValue;
            newNode = previousNode;
            return new Pair<>(root,newNode);
        }
    }

    public ListNode sortList1(ListNode head) {
        if(head==null){
            return null;
        }
        ListNode first = head;
        ListNode fast = null;
        ListNode low = null;
        int temp = 0;
        ListNode minNode = null;
        while(first!=null){
            low = first;
            fast = low.next;
            minNode = first;
            while(fast!=null){
                if(fast.val<minNode.val){
                    minNode = fast;
                }
                fast = fast.next;
            }
            //将minNode和first的值进行交换
            if(minNode!=first){
                temp = minNode.val;
                minNode.val = first.val;
                first.val = temp;
            }
            first = first.next;
        }
        return head;
    }


    private ListNode insertNode(ListNode root,ListNode newNode){
        ListNode newRoot = root;
        ListNode previous = null;
        while(root!=null && root.val<=newNode.val){
            previous = root;
            root = root.next;
        }
        //说明要插入的元素在previous 和 root之间
        ListNode insertNode = new ListNode(newNode.val);
        if(previous==null){
            //说明newNode比所有元素都大
            insertNode.next = newRoot;
            return insertNode;
        }else{
            previous.next = insertNode;
            insertNode.next = root;
            return newRoot;
        }

    }


    public ListNode sortList3(ListNode head) {
        if(head==null){
            return null;
        }
        ListNode root = new ListNode(head.val);
        while(head.next!=null){
            root = insertNode(root,head.next);
            head = head.next;
        }
        return root;
    }

    public ListNode sortList(ListNode head){
        if(head==null||head.next==null){
            return head;
        }
        ListNode slow = head,fast = head.next;
        while(fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode tmp = slow.next;
        slow.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(tmp);
        ListNode h = new ListNode(0);
        ListNode h0 = h;
        while(left!=null && right!=null){
            if(left.val<=right.val){
                h.next = left;
                left = left.next;
            }else{
                h.next = right;
                right= right.next;
            }
            h = h.next;
        }
        h.next = left!=null?left:right;
        return h0.next;
    }

    public static void main(String[] args) {
        LeetCode148 leetCode148 = new LeetCode148();
        ListNode listNode = new ListNode(-1,new ListNode(5,new ListNode(3,new ListNode(4,new ListNode(0,null)))));
        //ListNode listNode = new ListNode(4,new ListNode(2,new ListNode(1,new ListNode(3,null))));
        ListNode node = leetCode148.sortList(listNode);
        System.out.println(node);
    }
}

package nowCode.medium;

import leetCode.ListNode;
import org.junit.Test;

public class LeetCode029 {

    class Node {
        public int val;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    }

    public Node insert(Node head, int insertVal) {
        Node newHead = new Node(insertVal);
        if(head==null){
            newHead.next = newHead;
            return newHead;
        }
        Node p = head;
        /**
         * 考虑三种情况
         *
         * 1、链表元素值都相同
         * 2、新节点正处于链表的某个中间位置 p.val<=insertVal<=p.next.val
         * 3、新节点比首节点值小 或者新节点比末尾节点值大，那么就需要先判断是否在末尾 p.val>p.next.val,如果是比首节点小(p.next.val>insertVal),
         * 如果是比末尾节点大,那么就是p.val<=insertVal
         */
        while(p!=null){
            if(p.next == head || (p.val<=insertVal && insertVal<=p.next.val) || (p.val>p.next.val && (insertVal<p.next.val || p.val<insertVal))){
                newHead.next = p.next;
                p.next = newHead;
                break;
            }
            p = p.next;
        }
        return head;
    }

    @Test
    public void testInsert(){
        Node head = new Node(3);
        Node node1 = new Node(4);
        Node node2 = new Node(1);
        head.next = node1;
        node1.next = node2;
        node2.next = head;
        int insertValue = 2;
        Node insertNode = insert(head, insertValue);
        System.out.println(insertNode);
    }
}

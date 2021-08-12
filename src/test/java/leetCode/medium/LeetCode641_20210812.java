package leetCode.medium;

import org.junit.Test;

//设计实现双端队列。
//你的实现需要支持以下操作：
//
//
// MyCircularDeque(k)：构造函数,双端队列的大小为k。
// insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true。
// insertLast()：将一个元素添加到双端队列尾部。如果操作成功返回 true。
// deleteFront()：从双端队列头部删除一个元素。 如果操作成功返回 true。
// deleteLast()：从双端队列尾部删除一个元素。如果操作成功返回 true。
// getFront()：从双端队列头部获得一个元素。如果双端队列为空，返回 -1。
// getRear()：获得双端队列的最后一个元素。 如果双端队列为空，返回 -1。
// isEmpty()：检查双端队列是否为空。
// isFull()：检查双端队列是否满了。
//
//
// 示例：
//
// MyCircularDeque circularDeque = new MycircularDeque(3); // 设置容量大小为3
//circularDeque.insertLast(1);			        // 返回 true
//circularDeque.insertLast(2);			        // 返回 true
//circularDeque.insertFront(3);			        // 返回 true
//circularDeque.insertFront(4);			        // 已经满了，返回 false
//circularDeque.getRear();  				// 返回 2
//circularDeque.isFull();				        // 返回 true
//circularDeque.deleteLast();			        // 返回 true
//circularDeque.insertFront(4);			        // 返回 true
//circularDeque.getFront();				// 返回 4
public class LeetCode641_20210812 {

    class Node {
        private int val;
        private Node pre;
        private Node next;

        public Node(int val) {
            this.val = val;
        }

        public int getVal() {
            return this.val;
        }
    }

    private int maxLength;
    private int size;
    private Node head;
    private Node tail;

    /**
     * Initialize your data structure here. Set the size of the deque to be k.
     */
    public LeetCode641_20210812(int k) {
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.pre = head;
        maxLength = k;
        size = 0;
    }

    /**
     * Adds an item at the front of Deque. Return true if the operation is successful.
     */
    public boolean insertFront(int value) {
        if (size >= maxLength) {
            return false;
        }
        Node newNode = new Node(value);
        newNode.pre = head;
        newNode.next = head.next;
        head.next.pre = newNode;
        head.next = newNode;
        this.size += 1;
        return true;
    }

    /**
     * Adds an item at the rear of Deque. Return true if the operation is successful.
     */
    public boolean insertLast(int value) {
        if (size >= maxLength) {
            return false;
        }
        Node newNode = new Node(value);
        newNode.next = tail;
        newNode.pre = tail.pre;
        tail.pre.next = newNode;
        tail.pre = newNode;
        this.size += 1;
        return true;
    }

    /**
     * Deletes an item from the front of Deque. Return true if the operation is successful.
     */
    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        Node nextNode = head.next.next;
        head.next = nextNode;
        nextNode.pre = head;
        this.size -= 1;
        return true;
    }

    /**
     * Deletes an item from the rear of Deque. Return true if the operation is successful.
     */
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        Node beforeNode = tail.pre.pre;
        beforeNode.next = tail;
        tail.pre = beforeNode;
        this.size -= 1;
        return true;
    }

    /**
     * Get the front item from the deque.
     */
    public int getFront() {
        if (isEmpty()) {
            return -1;
        }
        return head.next.getVal();
    }

    /**
     * Get the last item from the deque.
     */
    public int getRear() {
        if (isEmpty()) {
            return -1;
        }
        return tail.pre.val;
    }

    /**
     * Checks whether the circular deque is empty or not.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks whether the circular deque is full or not.
     */
    public boolean isFull() {
        return size == maxLength;
    }

    public static void main(String[] args) {
        int k = 3;
        LeetCode641_20210812 circularDeque = new LeetCode641_20210812(k);
        boolean res = circularDeque.insertLast(1);			        // 返回 true
        System.out.println("res="+res);
        res = circularDeque.insertLast(2);			        // 返回 true
        System.out.println("res="+res);
        res = circularDeque.insertFront(3);			        // 返回 true
        System.out.println("res="+res);
        res = circularDeque.insertFront(4);			        // 已经满了，返回 false
        System.out.println("res="+res);
        int value = circularDeque.getRear();  				// 返回 2
        System.out.println("value="+value);
        res = circularDeque.isFull();				        // 返回 true
        System.out.println("res="+res);
        res = circularDeque.deleteLast();			        // 返回 true
        System.out.println("res="+res);
        res = circularDeque.insertFront(4);			        // 返回 true
        System.out.println("res="+res);
        value = circularDeque.getFront();				// 返回 4
        System.out.println("value="+value);
    }
}

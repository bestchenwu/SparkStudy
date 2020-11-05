package leetCode.medium;


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
// 
//

class MyCircularDeque {

    private int maxLength;
    private int validLength;
    private Node first;
    private Node last;

    /**
     * Initialize your data structure here. Set the size of the deque to be k.
     */
    public MyCircularDeque(int k) {
        this.maxLength = k;
        this.validLength = 0;
        this.first = new Node(-1);
        this.last = new Node(-1);
        first.next = last;
        last.previous = first;
    }

    /**
     * 表示在beforeNode和afterNode之间插入一个新节点
     *
     * @param beforeNode
     * @param newNode
     * @param afterNode
     * @author chenwu on 2020.11.5
     */
    private void insertBefore(Node beforeNode, Node newNode, Node afterNode) {
        beforeNode.next = newNode;
        newNode.previous = beforeNode;
        newNode.next = afterNode;
        afterNode.previous = newNode;
    }

    /**
     * Adds an item at the front of Deque. Return true if the operation is successful.
     */
    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        } else {
            Node newNode = new Node(value);
            Node beforeNode = first;
            Node afterNode = first.next;
            insertBefore(beforeNode, newNode, afterNode);
            validLength += 1;
            return true;
        }
    }

    /**
     * Adds an item at the rear of Deque. Return true if the operation is successful.
     */
    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        } else {
            Node newNode = new Node(value);
            Node afterNode = last;
            Node beforeNode = last.previous;
            insertBefore(beforeNode, newNode, afterNode);
            validLength += 1;
            return true;
        }
    }

    private void removeNode(Node deleteNode) {
        deleteNode.previous.next = deleteNode.next;
        deleteNode.next.previous = deleteNode.previous;
        deleteNode = null;
    }

    /**
     * Deletes an item from the front of Deque. Return true if the operation is successful.
     */
    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        } else {
            Node deleteNode = first.next;
            removeNode(deleteNode);
            validLength -= 1;
            return true;
        }
    }

    /**
     * Deletes an item from the rear of Deque. Return true if the operation is successful.
     */
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        } else {
            Node deleteNode = last.previous;
            removeNode(deleteNode);
            validLength -= 1;
            return true;
        }
    }

    /**
     * Get the front item from the deque.
     */
    public int getFront() {
        Node frontNode = first.next;
        return frontNode.value;
    }

    /**
     * Get the last item from the deque.
     */
    public int getRear() {
        Node lastNode = last.previous;
        return lastNode.value;
    }

    /**
     * Checks whether the circular deque is empty or not.
     */
    public boolean isEmpty() {
        return validLength == 0;
    }

    /**
     * Checks whether the circular deque is full or not.
     */
    public boolean isFull() {
        return validLength == maxLength;
    }
}

public class LeetCode641 {

    public static void main(String[] args) {
        MyCircularDeque circularDeque = new MyCircularDeque(3); // 设置容量大小为3
        System.out.println(circularDeque.insertLast(1));                    // 返回 true
        System.out.println(circularDeque.insertLast(2));                    // 返回 true
        System.out.println(circularDeque.insertFront(3));                    // 返回 true
        System.out.println(circularDeque.insertFront(4));                    // 已经满了，返回 false
        System.out.println(circularDeque.getRear());                // 返回 2
        System.out.println(circularDeque.isFull());                        // 返回 true
        System.out.println(circularDeque.deleteLast());                    // 返回 true
        System.out.println(circularDeque.insertFront(4));                    // 返回 true
        System.out.println(circularDeque.getFront());                // 返回 4
    }
}

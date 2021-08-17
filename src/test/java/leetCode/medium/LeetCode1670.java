package leetCode.medium;

import org.junit.Test;

//请你设计一个队列，支持在前，中，后三个位置的 push 和 pop 操作。
//
// 请你完成 FrontMiddleBack 类：
//
//
// FrontMiddleBack() 初始化队列。
// void pushFront(int val) 将 val 添加到队列的 最前面 。
// void pushMiddle(int val) 将 val 添加到队列的 正中间 。
// void pushBack(int val) 将 val 添加到队里的 最后面 。
// int popFront() 将 最前面 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
// int popMiddle() 将 正中间 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
// int popBack() 将 最后面 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
//
//
// 请注意当有 两个 中间位置的时候，选择靠前面的位置进行操作。比方说：
//
//
// 将 6 添加到 [1, 2, 3, 4, 5] 的中间位置，结果数组为 [1, 2, 6, 3, 4, 5] 。
// 从 [1, 2, 3, 4, 5, 6] 的中间位置弹出元素，返回 3 ，数组变为 [1, 2, 4, 5, 6] 。
//
//
//
//
// 示例 1：
//
//
//输入：
//["FrontMiddleBackQueue", "pushFront", "pushBack", "pushMiddle", "pushMiddle",
//"popFront", "popMiddle", "popMiddle", "popBack", "popFront"]
//[[], [1], [2], [3], [4], [], [], [], [], []]
//输出：
//[null, null, null, null, null, 1, 3, 4, 2, -1]
//
//解释：
//FrontMiddleBackQueue q = new FrontMiddleBackQueue();
//q.pushFront(1);   // [1]
//q.pushBack(2);    // [1, 2]
//q.pushMiddle(3);  // [1, 3, 2]
//q.pushMiddle(4);  // [1, 4, 3, 2]
//q.popFront();     // 返回 1 -> [4, 3, 2]
//q.popMiddle();    // 返回 3 -> [4, 2]
//q.popMiddle();    // 返回 4 -> [2]
//q.popBack();      // 返回 2 -> []
//q.popFront();     // 返回 -1 -> [] （队列为空）
public class LeetCode1670 {

    class Node {
        public int val;
        public Node pre;
        public Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public LeetCode1670() {
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.pre = head;
        size = 0;
    }

    public void pushFront(int val) {
        Node newNode = new Node(val);
        newNode.pre = head;
        newNode.next = head.next;
        head.next.pre = newNode;
        head.next = newNode;
        this.size += 1;
    }

    public void pushMiddle(int val) {
        int k = this.size / 2;
        Node slow = head;
        Node fast = head.next;
        for (int i = 0; i < k; i++) {
            slow = fast;
            fast = fast.next;
        }
        //在slow和fast之间插入val
        Node newNode = new Node(val);
        slow.next = newNode;
        newNode.pre = slow;
        newNode.next = fast;
        fast.pre = newNode;
        this.size += 1;
    }

    public void pushBack(int val) {
        Node newNode = new Node(val);
        newNode.next = tail;
        newNode.pre = tail.pre;
        tail.pre.next = newNode;
        tail.pre = newNode;
        this.size += 1;
    }

    public int popFront() {
        if (isEmpty()) {
            return -1;
        }
        Node popNode = head.next;
        head.next = popNode.next;
        popNode.next.pre = head;
        this.size -= 1;
        return popNode.val;
    }

    public int popMiddle() {
        if (isEmpty()) {
            return -1;
        }
        int k = this.size % 2 == 0 ? (this.size / 2) - 1 : this.size / 2;
        Node slow = head;
        Node fast = head.next;
        for (int i = 0; i < k; i++) {
            slow = fast;
            fast = fast.next;
        }
        //要删除的就是fast元素
        slow.next = fast.next;
        fast.next.pre = slow;
        this.size -= 1;
        return fast.val;
    }

    public int popBack() {
        if (isEmpty()) {
            return -1;
        }
        Node popNode = tail.pre;
        popNode.pre.next = tail;
        tail.pre = popNode.pre;
        this.size -= 1;
        return popNode.val;
    }

    public boolean isEmpty() {
        return this.size <= 0;
    }

    @Test
    public void testFrontMiddleBackQueue() {
        LeetCode1670 q = new LeetCode1670();
//        q.pushFront(1);   // [1]
//        q.pushBack(2);    // [1, 2]
//        q.pushMiddle(3);  // [1, 3, 2]
//        q.pushMiddle(4);  // [1, 4, 3, 2]
//        int value = q.popFront();     // 返回 1 -> [4, 3, 2]
//        System.out.println("value=" + value);
//        value = q.popMiddle();    // 返回 3 -> [4, 2]
//        System.out.println("value=" + value);
//        value = q.popMiddle();    // 返回 4 -> [2]
//        System.out.println("value=" + value);
//        value = q.popBack();      // 返回 2 -> []
//        System.out.println("value=" + value);
//        value = q.popFront();     // 返回 -1 -> [] （队列为空）
//        System.out.println("value=" + value);

//         q.pushFront(1);
//        q.pushFront(2);
//        q.pushFront(3);
//        q.pushFront(4);
//        int value = q.popBack();
//        System.out.println("value=" + value);
//        value = q.popBack();
//        System.out.println("value=" + value);
//        value = q.popBack();
//        System.out.println("value=" + value);
//        value = q.popBack();
//        System.out.println("value=" + value);
        q.popMiddle();
        q.popMiddle();
        q.pushMiddle(8);
        q.popBack();
        q.popFront();
        q.popMiddle();
    }
}

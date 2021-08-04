package leetCode.medium;

//运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制 。
//
//
//
// 实现 LRUCache 类：
//
//
// LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
// int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
// void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上
//限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
//
//
//
//
//
//
// 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？
//
//
//
// 示例：
//
//
//输入
//["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
//输出
//[null, null, null, 1, null, -1, null, -1, 3, 4]
//
//解释
//LRUCache lRUCache = new LRUCache(2);
//lRUCache.put(1, 1); // 缓存是 {1=1}
//lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
//lRUCache.get(1);    // 返回 1
//lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
//lRUCache.get(2);    // 返回 -1 (未找到)
//lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
//lRUCache.get(1);    // 返回 -1 (未找到)
//lRUCache.get(3);    // 返回 3
//lRUCache.get(4);    // 返回 4
import org.junit.Test;

import java.util.*;
public class LeetCode146_20210804 {

    public LinkedListNode head;
    public LinkedListNode tail;

    class LinkedListNode {
        public LinkedListNode pre;
        public LinkedListNode next;
        public int key;
        public int value;

        public LinkedListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public void appendNode(LinkedListNode newNode) {
        LinkedListNode end = head.next;
        head.next = newNode;
        newNode.next = end;
        newNode.pre = head;
        end.pre = newNode;
    }

    public void deleteNode(LinkedListNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    public void moveToHead(LinkedListNode node) {
        deleteNode(node);
        appendNode(node);
    }

    private Map<Integer, LinkedListNode> map = new HashMap<>();
    private int size = 0;

    public LeetCode146_20210804(int capacity) {
        this.size = capacity;
        head = new LinkedListNode(0, 0);
        tail = new LinkedListNode(0, 0);
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        LinkedListNode node = map.get(key);
        if (node == null) {
            return -1;
        } else {
            int value = node.value;
            moveToHead(node);
            return value;
        }
    }

    public void put(int key, int value) {
        LinkedListNode node = map.get(key);
        if(node!=null){
            node.value = value;
            moveToHead(node);
        }else{
            LinkedListNode newNode = new LinkedListNode(key, value);
            map.put(key, newNode);
            appendNode(newNode);
            if (map.size() > size) {
                //找出尾部最后一个节点
                LinkedListNode lastNode = tail.pre;
                int lastKey = lastNode.key;
                deleteNode(lastNode);
                map.remove(lastKey);
            }
        }
    }

    public static void main(String[] args) {
        LeetCode146_20210804 lru = new LeetCode146_20210804(2);
        lru.put(2,1 );
        lru.put(1,1);
        lru.put(2,3);
        lru.put(4,1);
        int value = lru.get(1);
        System.out.println("value="+value);
        value = lru.get(2);
        System.out.println("value="+value);
    }

}

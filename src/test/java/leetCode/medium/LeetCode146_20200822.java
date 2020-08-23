package leetCode.medium;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/lru-cache/
 * <p>
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 * <p>
 * 获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 * <p>
 *  
 * <p>
 * 进阶:
 * <p>
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * LRUCache cache = new LRUCache( 2  缓存容量  )
 * <p>
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1);       // 返回  1
 * cache.put(3, 3);    // 该操作会使得关键字 2 作废
 * cache.get(2);       // 返回 -1 (未找到)
 * cache.put(4, 4);    // 该操作会使得关键字 1 作废
 * cache.get(1);       // 返回 -1 (未找到)
 * cache.get(3);       // 返回  3
 * cache.get(4);       // 返回  4
 *
 * @author chenwu on 2020.8.22
 */
public class LeetCode146_20200822 {

    static class LRUCache {

        private class LinkedNode {
            public Integer key = -1;
            public Integer value = -1;
            public LinkedNode pre;
            public LinkedNode next;
        }

        private LinkedNode head = new LinkedNode(), tail = new LinkedNode();

        /**
         * 每次来一个新节点,都放在最前面
         *
         * @param newNode
         */
        private void addFirst(LinkedNode newNode) {
            head.next.pre = newNode;
            newNode.next = head.next;
            newNode.pre = head;
            head.next = newNode;
        }

        private void moveToHead(LinkedNode node) {
            removeNode(node);
            addFirst(node);
        }

        private void removeNode(LinkedNode node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }

        private LinkedNode removeLast() {
            if (tail.pre != head) {
               LinkedNode node = tail.pre;
               removeNode(node);
               return node;
            } else {
                return null;
            }

        }

        private Map<Integer, LinkedNode> map = new HashMap<>();

        private int size = 0;
        private int capacity = 0;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            head.next = tail;
            tail.pre = head;
        }

        public int get(int key) {
            LinkedNode linkedNode = map.get(key);
            if (linkedNode != null) {
                moveToHead(linkedNode);
                return linkedNode.value;
            } else {
                return -1;
            }
        }

        public void put(int key, int value) {
            LinkedNode linkedNode = map.get(key);
            if (linkedNode == null) {
                linkedNode = new LinkedNode();
                linkedNode.key = key;
                linkedNode.value = value;
                map.put(key, linkedNode);
                addFirst(linkedNode);
                this.size += 1;
            } else {
                linkedNode.value = value;
                moveToHead(linkedNode);
            }
            if (this.size > capacity) {
                LinkedNode removeNode = removeLast();
                map.remove(removeNode.key);
                removeNode = null;
                this.size -= 1;
            }
        }


    }

//    static class LRUCache extends LinkedHashMap<Integer, Integer> {
//
//
//        private int size = 0;
//
//        public LRUCache(int capacity) {
//            //super();
    //这里必须指定访问顺序
//            super(capacity,1,true);
//            this.size = capacity;
//        }
//
//        public int get(int key) {
//            return super.get(key)!=null?super.get(key):-1;
//        }
//
//        public void put(int key, int value) {
//            super.put(key,value);
//        }
//
//        @Override
//        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
//            return size()>this.size;
//        }
//    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);

//        cache.put(1, 1);
//        cache.put(2, 2);
//        System.out.println(cache.get(1));       // 返回  1
//        cache.put(3, 3);    // 该操作会使得关键字 2 作废
//        System.out.println(cache.get(2));       // 返回 -1 (未找到)
//        cache.put(4, 4);    // 该操作会使得关键字 1 作废
//        System.out.println(cache.get(1));       // 返回 -1 (未找到)
//        System.out.println(cache.get(3));       // 返回  3
//        System.out.println(cache.get(4));       // 返回  4;
        int result = cache.get(1);
        System.out.println(result);
        cache.put(2,6);
        result = cache.get(1);
        System.out.println(result);
        cache.put(1,5);
        cache.put(1,2);
        result = cache.get(1);
        System.out.println(result);
        result = cache.get(2);
        System.out.println(result);
//        cache.put(2, 1);
//        cache.put(1, 1);
//        cache.put(2, 3);
//        cache.put(4, 1);
//        int result = cache.get(1);
//        System.out.println(result);
//        result = cache.get(2);
//        System.out.println(result);
    }

}

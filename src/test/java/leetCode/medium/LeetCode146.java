package leetCode.medium;


import java.util.Hashtable;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/lru-cache/
 * <p>
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 * <p>
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
 * 示例:
 */
class LRUListNode {

    public int key = -1;
    public int value = -1;
    public LRUListNode previousNode;
    public LRUListNode nextNode;
    public int size = 0;
    public int nodeCount = 0;
    public long useTimestamp = Long.MAX_VALUE;

    public LRUListNode() {
        super();
    }

    public LRUListNode(int size) {
        this.size = size;
    }

    private void refresh() {
        if (nodeCount > size) {
            //从链表中找出useTimeStamp最小的
            LRUListNode lastNotUseNode = null;
            long min = Long.MAX_VALUE;
            LRUListNode root = this;
            while (root.nextNode != null) {
                root = root.nextNode;
                if (root.useTimestamp < min) {
                    min = root.useTimestamp;
                    lastNotUseNode = root;
                }
            }
            lastNotUseNode.previousNode.nextNode = lastNotUseNode.nextNode;
            lastNotUseNode.nextNode.previousNode = lastNotUseNode.previousNode;
            lastNotUseNode = null;
            this.nodeCount -= 1;
        }
    }

    public void addNode(int key, int value) {
        LRUListNode root = this;
        while (root.nextNode != null) {
            root = root.nextNode;
            if (root.key == key) {
                root.value = value;
                try {
                    Thread.currentThread().sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                root.useTimestamp = System.currentTimeMillis();
                return;
            }
        }
        LRUListNode newRootNode = new LRUListNode();
        newRootNode.key = key;
        newRootNode.value = value;
        try {
            Thread.currentThread().sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        newRootNode.useTimestamp = System.currentTimeMillis();
        root.nextNode = newRootNode;
        newRootNode.previousNode = root;
        this.nodeCount += 1;
        refresh();
    }

    public int getValue(int key) {
        LRUListNode root = this;
        while (root.nextNode != null) {
            root = root.nextNode;
            if (root.key == key) {
                try {
                    Thread.currentThread().sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                root.useTimestamp = System.currentTimeMillis();
                return root.value;
            }
        }
        return -1;
    }
}

@Deprecated
class LRUCache0 {

    private LRUListNode listNode;

    public LRUCache0(int capacity) {
        listNode = new LRUListNode(capacity);
    }

    public int get(int key) {
        return listNode.getValue(key);
    }

    public void put(int key, int value) {
        listNode.addNode(key, value);
    }
}



//利用java的linkedhashMap来实现
@Deprecated
class LRUCache1 extends LinkedHashMap<Integer, Integer> {

    private int capacity;

    public LRUCache1(int size){
        super(size,0.5F,true);
        this.capacity = size;
    }

    public int get(int key){
        return super.getOrDefault(key,-1);
    }

    public void put(int key,int value){
        super.put(key,value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return super.size()>capacity;
    }
}
//利用hashTable来存储key,利用双向链表来辅助存储value


class LRUCache{

    class DLinkListNode {
        public int key;
        public int value;
        public DLinkListNode next;
        public DLinkListNode prev;

    }

    public void addNode(DLinkListNode node){
        node.next = head.next;
        head.next = node;
        node.prev = head;
        node.next.prev = node;
    }

    public void removeNode(DLinkListNode node){
         node.prev.next = node.next;
         node.next.prev = node.prev;
         node = null;
    }

    public void moveToHead(DLinkListNode node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
        addNode(node);
    }

    public int removeTail(){
        DLinkListNode removeNode = tail.prev;
        int key = removeNode.key;
        removeNode(removeNode);
        return key;
    }

    private Hashtable<Integer,DLinkListNode> hashTable = new Hashtable<Integer, DLinkListNode>();
    private int capacity;
    private DLinkListNode head,tail;
    private int size = 0;

    public LRUCache(int size){
        this.capacity = size;
        head = new DLinkListNode();
        tail = new DLinkListNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key){
        DLinkListNode node = hashTable.get(key);
        if(node==null){
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    public void put(int key,int value){
       DLinkListNode node =  hashTable.get(key);
        if(node == null){
            DLinkListNode newNode = new DLinkListNode();
            newNode.key = key;
            newNode.value = value;
            hashTable.put(key,newNode);
            addNode(newNode);
            size++;
            if(size>capacity){
               int removeKey = removeTail();
                hashTable.remove(removeKey);
            }
        }else{
            node.value = value;
            moveToHead(node);
        }
    }

}

public class LeetCode146 {

    //    LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );
//
//        cache.put(1, 1);
//        cache.put(2, 2);
//        cache.get(1);       // 返回  1
//        cache.put(3, 3);    // 该操作会使得密钥 2 作废
//        cache.get(2);       // 返回 -1 (未找到)
//        cache.put(4, 4);    // 该操作会使得密钥 1 作废
//        cache.get(1);       // 返回 -1 (未找到)
//        cache.get(3);       // 返回  3
//        cache.get(4);       // 返回  4
//使用链表来存储
//先入先出结构
//每次都在尾部添加节点
//在队列满的时候从头部删除节点


    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        System.out.println(cache.get(2));       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        System.out.println(cache.get(1));       // 返回 -1 (未找到)
        System.out.println(cache.get(3));       // 返回  3
        System.out.println(cache.get(4));       // 返回  4
//         cache.put(2,1);
//         cache.put(2,2);
//        System.out.println(cache.get(2));
//          cache.put(1,1);
//          cache.put(4,1);
//        System.out.println(cache.get(2));;
    }
}

package leetCode.medium;

import java.util.HashMap;
import java.util.Map;

class LRUCache20200427 {

    class ListNode{

        public ListNode prev;
        public ListNode next;
        public Integer key;
        public Integer value;
    }

    class DpList{

        private ListNode head = new ListNode();
        private ListNode tail = new ListNode();

        public DpList(){
            head.next = tail;
            tail.prev = head;
        }

        public void addNode(ListNode newNode){
            ListNode headNext = head.next;
            head.next = newNode;
            newNode.prev = head;
            newNode.next = headNext;
            headNext.prev = newNode;
        }

        public void removeNode(ListNode removeNode){
            removeNode.prev.next = removeNode.next;
            removeNode.next.prev = removeNode.prev;
        }

        public void moveToHead(ListNode listNode){
            removeNode(listNode);
            addNode(listNode);
        }

        public Integer removeTail(){
            ListNode tailBefore = tail.prev;
            if(tailBefore.key!=null){
                //说明尾部节点真实存在
                removeNode(tailBefore);
                return tailBefore.key;
            }else{
                return null;
            }
        }
    }

    private Map<Integer,ListNode> map = new HashMap<>();
    private DpList dpList = new DpList();
    private int maxSize;

    public LRUCache20200427(int capacity) {
        this.maxSize = capacity;
    }

    public int get(int key) {
        ListNode node = map.get(key);
        if(node==null){
            return -1;
        }else{
            dpList.moveToHead(node);
            return node.value;
        }
    }

    public void put(int key, int value) {
        ListNode node = map.get(key);
        if(node!=null){
            dpList.moveToHead(node);
            node.value = value;
        }else{
            ListNode newNode = new ListNode();
            newNode.key = key;
            newNode.value = value;
            dpList.addNode(newNode);
            map.put(key,newNode);
            if(map.size()>maxSize){
                Integer removeKey =  dpList.removeTail();
                if(removeKey!=null){
                    map.remove(removeKey);
                }
            }
        }

    }
}

public class ListNode146_20200427 {



    public static void main(String[] args) {
        LRUCache20200427 cache = new LRUCache20200427( 2 );
        //输出1 -1 -1 3 4
//        cache.put(1, 1);
//        cache.put(2, 2);
//        int value = cache.get(1);       // 返回  1
//        System.out.println("value="+value);
//        cache.put(3, 3);    // 该操作会使得密钥 2 作废
//        value = cache.get(2);       // 返回 -1 (未找到)
//        System.out.println("value="+value);
//        cache.put(4, 4);    // 该操作会使得密钥 1 作废
//        value = cache.get(1);       // 返回 -1 (未找到)
//        System.out.println("value="+value);
//        value = cache.get(3);       // 返回  3
//        System.out.println("value="+value);
//        value = cache.get(4);       // 返回  4
//        System.out.println("value="+value);

        cache.put(2,1);
        cache.put(1,1);
        cache.put(2,3);
        cache.put(4,1);
        int value = cache.get(1);
        System.out.println("value="+value);
        value = cache.get(2);
        System.out.println("value="+value);
    }
}

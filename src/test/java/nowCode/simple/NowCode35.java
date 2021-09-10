package nowCode.simple;

import java.util.HashMap;
import java.util.Map;

public class NowCode35 {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    private Map<Node,Node> cacheMap = new HashMap<>();

    public Node copyRandomList(Node head) {
        if(head == null){
            return null;
        }
        if(!cacheMap.containsKey(head)){
            Node newHead = new Node(head.val);
            cacheMap.put(head,newHead);
            newHead.next = copyRandomList(head.next);
            newHead.random = copyRandomList(head.random);
        }
        return cacheMap.get(head);
    }
}

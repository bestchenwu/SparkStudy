package leetCode;

/**
 * 带多级链表的节点
 *
 * @author chenwu on 2021.8.9
 */
public class MultiListNode {

    public int val;
    public MultiListNode prev;
    public MultiListNode next;
    public MultiListNode child;

    public MultiListNode(int val,MultiListNode prev,MultiListNode next,MultiListNode child){
        this.val = val;
        this.prev = prev;
        this.next = next;
        this.child = child;
    }
}

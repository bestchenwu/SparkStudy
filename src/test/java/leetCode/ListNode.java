package leetCode;

public class ListNode {

    public int val;
    public ListNode next;

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }

    public ListNode(){

    }

    public ListNode(int x) {
        val = x;
    }

    public ListNode(int x, ListNode y) {
        val = x;
        next = y;
    }

    public static ListNode createListNode(int... valList){
        ListNode head = new ListNode(0);
        ListNode tmp = head;
        for(int i : valList){
            tmp.next = new ListNode(i);
            tmp = tmp.next;
        }
        return head.next;
    }

}

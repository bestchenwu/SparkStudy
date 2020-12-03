package nowCode.simple;
import leetCode.ListNode;

public class NowCode22 {

    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode slow = head;
        ListNode fast = head;
        int index = 1;
        while(index<k){
            fast = fast.next;
            index ++;
        }
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        ListNode p1 = new ListNode(1);
        ListNode p2 = new ListNode(2);
        ListNode p3 = new ListNode(3);
        ListNode p4 = new ListNode(4);
        ListNode p5 = new ListNode(5);
        p1.next = p2;
        p2.next = p3;
        p3.next = p4;
        p4.next = p5;
        NowCode22 nowCode22 = new NowCode22();
        ListNode kthFromEnd = nowCode22.getKthFromEnd(p1, 2);
        System.out.println(kthFromEnd);
    }
}

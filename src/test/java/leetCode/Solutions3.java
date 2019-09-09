package leetCode;


public class Solutions3 {

    public boolean checkCycle(ListNode top, ListNode next) {
        if (top == null) {
            return false;
        }
        if (top != null && next == null) {
            return false;
        }
        boolean flag = false;
        while (next != null) {
            if (top.val == next.val) {
                flag = true;
                break;
            }
            next = next.next;
        }
        return flag;
    }

    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        boolean flag = false;
        while (head != null) {
            flag = checkCycle(head, head.next);
            if (flag == true) {
                break;
            }
            head = head.next;
        }
        return flag;
    }

    public static void main(String[] args){
        ListNode node = new ListNode(3);
        node.next = new ListNode(2);
        node.next.next = new ListNode(0);

    }
}

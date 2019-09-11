package leetCode;

import java.util.List;

class ListNode {
    int val;
    ListNode next;

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }

    ListNode(int x) {
        val = x;
    }

    ListNode(int x, ListNode y){
        val = x ;
        next = y;
    }

}

public class Solutions {

    public long getIntNumber(ListNode listNode, int index) {
        if (listNode == null) {
            return 0l;
        }
        long result = (long)listNode.val + 10l*getIntNumber(listNode.next, index+1);
        return result;
    }

    public ListNode parseIntToListNode(String number) {
        int length = number.length();
        int index = 0;
        ListNode node = null;
        if(length>1){
            char a = number.charAt(index);
            node = new ListNode(a-'0');
            node.next = parseIntToListNode(number.substring(index+1));
        }else{
            node=new ListNode(number.charAt(index)-'0');
        }
        return node;
    }

    public ListNode reverseNode(ListNode node){
        ListNode p=null,q=node,r=node.next;
        while(r!=null){
            q.next = p;
            p = q;
            q = r;
            r = r.next;
        }
        q.next = p;
        return q;
    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        long val1 = getIntNumber(l1, 1);
        long val2 = getIntNumber(l2, 1);
        long val = val1 + val2;
        ListNode list =  parseIntToListNode(String.valueOf(val));
        ListNode result = reverseNode(list);
        return result;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(9);
//        ListNode l1 = new ListNode(2);
//        l1.next = new ListNode(4);
//        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(9);
        l2.next.next = new ListNode(9);
        l2.next.next.next = new ListNode(9);
        l2.next.next.next.next = new ListNode(9);
        l2.next.next.next.next.next = new ListNode(9);
        l2.next.next.next.next.next.next = new ListNode(9);
        l2.next.next.next.next.next.next.next = new ListNode(9);
        l2.next.next.next.next.next.next.next.next = new ListNode(9);
        l2.next.next.next.next.next.next.next.next.next = new ListNode(9);

//        ListNode l2 = new ListNode(5);
//        l2.next = new ListNode(6);
//        l2.next.next = new ListNode(4);
        Solutions1 solutions = new Solutions1();
        ListNode result = solutions.addTwoNumbers(l1, l2);
        do {
            System.out.println(result.val);
            result = result.next;
        } while (result != null);
        System.out.println(342+465);

    }

}

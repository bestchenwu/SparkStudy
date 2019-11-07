package leetCode.simple;

import leetCode.ListNode;

class Pair{
    int first ;
    int end;

    public Pair(int first,int end){
        this.first = first;
        this.end = end;
    }


}
public class Solutions1 {

    public Pair addValue(ListNode l1, ListNode l2, int end){
        int value1 = l1!=null?l1.val:0;
        int value2 = l2!=null?l2.val:0;
        int value = value1+value2+end;
        if(value>=10){
            return new Pair(value%10,1);
        }else{
            return new Pair(value,0);
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2,int end) {
        ListNode list = new ListNode(0);
        if(l1!=null||l2!=null){
            Pair pair = addValue(l1,l2,end);
            list.val = pair.first;
            ListNode next1 = l1!=null?l1.next:null;
            ListNode next2 = l2!=null?l2.next:null;
            if(next1==null&&next2==null){
                if(pair.end>0){
                    list.next = new ListNode(pair.end);
                }
            }else{
                list.next =  addTwoNumbers(next1,next2,pair.end);
            }
        }
        return list;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode list = addTwoNumbers(l1,l2,0);
        return list;
    }
}

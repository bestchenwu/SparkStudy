package leetCode;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/linked-list-cycle/
 * 判断链表是否有环
 *
 * @author chenwu on 2019.9.10
 */
public class HaveCylcleSolution {

    int[] array = new int[]{};

    // 3 -> 2 ->0 ->1
    //  ->2
    //遍历整个链表,判断是否存在一个节点出现两次
    //简化为如何判断一个数组里出现了两次重复数字
    public boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<ListNode>();
        if(head==null||head.next==null){
            return false;
        }
        while(head!=null){
            if(set.contains(head)){
                return true;
            }else{
                set.add(head);
                head = head.next;
            }
        }
        return false;
    }

    public boolean hasCylecle(ListNode head){
        if(head==null||head.next == null){
            return false;
        }
        ListNode slow=head,fast = head;
        while(true){
            if(fast == null||fast.next==null){
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
            if(slow==fast){
                return true;
            }
        }
    }

    public static void main(String[] args){
        ListNode list = new ListNode(1);
        list.next = new ListNode(2);
        HaveCylcleSolution solution = new HaveCylcleSolution();
        System.out.println(solution.hasCylecle(list));
    }
}

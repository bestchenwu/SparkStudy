package nowCode.medium;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Stack;

//栈排序。 编写程序，对栈进行排序使最小元素位于栈顶。最多只能使用一个其他的临时栈存放数据，但不得将元素复制到别的数据结构（如数组）中。该栈支持如下操作：pu
////sh、pop、peek 和 isEmpty。当栈为空时，peek 返回 -1。
////
//// 示例1:
////
////  输入：
////["SortedStack", "push", "push", "peek", "pop", "peek"]
////[[], [1], [2], [], [], []]
//// 输出：
////[null,null,null,1,null,2]
////
////
//// 示例2:
////
////  输入：
////["SortedStack", "pop", "pop", "push", "pop", "isEmpty"]
////[[], [], [], [1], [], []]
//// 输出：
//[null,null,null,null,null,true]
public class LeetCode0305 {

    private Stack<Integer> assistStack = new Stack<>();
    private Stack<Integer> stack = new Stack<>();

    public LeetCode0305() {

    }

    public void push(int val) {
        while(!stack.isEmpty() && stack.peek()<val){
            assistStack.push(stack.pop());
        }
        stack.push(val);
        while(!assistStack.isEmpty()){
            stack.push(assistStack.pop());
        }
    }

    public void pop() {
        if(!stack.isEmpty()){
            stack.pop();
        }
    }

    public int peek() {
        return stack.isEmpty() ? -1 : stack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Test
    public void testLeetCode0305(){
        LeetCode0305 leetCode0305 = new LeetCode0305();
        leetCode0305.push(1);
        leetCode0305.push(2);
        System.out.println("peek:"+leetCode0305.peek());
        leetCode0305.pop();
        System.out.println("peek:"+leetCode0305.peek());
    }
}

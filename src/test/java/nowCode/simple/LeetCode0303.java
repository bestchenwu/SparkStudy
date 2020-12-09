package nowCode.simple;

//请设计一个栈，除了常规栈支持的pop与push函数以外，还支持min函数，该函数返回栈元素中的最小值。执行push、pop和min操作的时间复杂度必须为O(
//1)。 示例： MinStack minStack = new MinStack(); minStack.push(-2); minStack.push(0);
// minStack.push(-3); minStack.getMin();   --> 返回 -3. minStack.pop(); minStack.top
//();      --> 返回 0. minStack.getMin();   --> 返回 -2. Related Topics 栈

import org.junit.Test;

import java.util.PriorityQueue;

/**
 * @author chenwu on 2020.12.9
 */
public class LeetCode0303 {

    class MinStack {

        private int[] data;
        private int topIndex = -1;
        private PriorityQueue<Integer> queue = new PriorityQueue<>();

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            data = new int[10];
        }

        public void push(int x) {
            if (topIndex == data.length-1) {
                int[] newDatas = new int[2 * data.length];
                System.arraycopy(data, 0, newDatas, 0, data.length);
                data = newDatas;
            }
            data[++topIndex] = x;
            queue.add(x);
        }

        public void pop() {
            if (topIndex >= 0) {
                queue.remove(data[topIndex]);
                topIndex--;
            }
        }

        public int top() {
            return data[topIndex];
        }

        public int getMin() {
            return queue.peek();
        }
    }

    @Test
    public void test() {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        int min = minStack.getMin();
        System.out.println("min="+min);
        minStack.pop();
        minStack.top();
        min = minStack.getMin();
        System.out.println("min="+min);
    }
}

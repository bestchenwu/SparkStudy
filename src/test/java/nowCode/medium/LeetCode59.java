package nowCode.medium;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Stack;

//请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都
//是O(1)。
//
// 若队列为空，pop_front 和 max_value 需要返回 -1
//
// 示例 1：
//
// 输入:
//["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
//[[],[1],[2],[],[],[]]
//输出: [null,null,null,2,1,2]
//
//
// 示例 2：
//
// 输入:
//["MaxQueue","pop_front","max_value"]
//[[],[],[]]
//输出: [null,-1,-1]
//
//
//
//
// 限制：
//
//
// 1 <= push_back,pop_front,max_value的总操作数 <= 10000
// 1 <= value <= 10^5
public class LeetCode59 {

    private LinkedList<Integer> list = new LinkedList<>();
    //单调递减栈  因为总是从队列首部删除元素
    private LinkedList<Integer> stack = new LinkedList<>();

    public LeetCode59() {

    }

    public int max_value() {
        if(stack.isEmpty()){
            return -1;
        }else{
            return stack.peekFirst();
        }
    }

    public void push_back(int value) {
        list.addLast(value);
        while(!stack.isEmpty() && stack.peekLast()<value){
            stack.pollLast();
        }
        stack.addLast(value);
    }

    public int pop_front() {
        Integer first = list.pollFirst();
        if(max_value()==first.intValue()){
            stack.remove(0);
        }
        return first;
    }

    @Test
    public void testLeetCode59(){
        LeetCode59 leetCode59 = new LeetCode59();
        leetCode59.push_back(15);
        System.out.println("maxValue="+leetCode59.max_value());
        leetCode59.push_back(9);
        System.out.println("maxValue="+leetCode59.max_value());
    }
}

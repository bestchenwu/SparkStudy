package leetCode.medium;

// 字符串只包含数字0-9、[、-、,、]
//
//
//
//
// 示例 1：
//
// 给定 s = "324",
//
//你应该返回一个 NestedInteger 对象，其中只包含整数值 324。
//
//
// 示例 2：
//
// 给定 s = "[123,[456,[789]]]",
//
//返回一个 NestedInteger 对象包含一个有两个元素的嵌套列表：
//
//1. 一个 integer 包含值 123
//2. 一个包含两个元素的嵌套列表：
//    i.  一个 integer 包含值 456
//    ii. 一个包含一个元素的嵌套列表
//         a. 一个 integer 包含值 789
//
// Related Topics 栈 字符串
// 👍 61 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.Stack;

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 * // Constructor initializes an empty nested list.
 * public NestedInteger();
 * <p>
 * // Constructor initializes a single integer.
 * public NestedInteger(int value);
 * <p>
 * // @return true if this NestedInteger holds a single integer, rather than a nested list.
 * public boolean isInteger();
 * <p>
 * // @return the single integer that this NestedInteger holds, if it holds a single integer
 * // Return null if this NestedInteger holds a nested list
 * public Integer getInteger();
 * <p>
 * // Set this NestedInteger to hold a single integer.
 * public void setInteger(int value);
 * <p>
 * // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 * public void add(NestedInteger ni);
 * <p>
 * // @return the nested list that this NestedInteger holds, if it holds a nested list
 * // Return empty list if this NestedInteger holds a single integer
 * public List<NestedInteger> getList();
 * }
 */
public class LeetCode385 {

    public NestedInteger deserialize(String s) {
        //假定形成了这样一个值栈
        Stack<Integer> numStack = new Stack<>();
        int num = 0;
        Stack<NestedInteger> inNestedIntegerStack = new Stack<>();
        Stack<NestedInteger> outNestedIntegerStack = new Stack<>();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            if (s.charAt(i) == '[') {
                inNestedIntegerStack.push(new NestedInteger());
            } else if(s.charAt(i)==','){
                numStack.push(num);
                num = 0;
            }else if (s.charAt(i) == ']' || i == length - 1) {
                if (inNestedIntegerStack.isEmpty()) {
                    outNestedIntegerStack.push(new NestedInteger(numStack.pop()));
                } else {
                    NestedInteger outPeek = null;
                    if(!outNestedIntegerStack.isEmpty()){
                        outPeek = outNestedIntegerStack.pop();
                    }
                    NestedInteger inPeek = inNestedIntegerStack.pop();
                    inPeek.add(new NestedInteger(numStack.pop()));
                    if(outPeek!=null){
                        inPeek.add(outPeek);
                    }
                    outNestedIntegerStack.push(inPeek);
                }
            } else {
                num = (num * 10 + s.charAt(i) - '0');
            }
        }
        return outNestedIntegerStack.peek();
    }
}

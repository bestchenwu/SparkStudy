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

import org.junit.Test;

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
        if (s == null || s.length() == 0) {
            return new NestedInteger();
        }
        if (s.charAt(0) != '[') {
            return new NestedInteger(Integer.valueOf(s));
        }
        Stack<NestedInteger> stack = new Stack<>();
        stack.push(new NestedInteger());
        int num = 0;
        int sign = 1;
        NestedInteger result = null;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ',') {
                continue;
            } else if (s.charAt(i) == '[') {
                NestedInteger newNode = new NestedInteger();
                stack.peek().add(newNode);
                stack.push(newNode);
            } else if (s.charAt(i) == ']') {
                result = stack.pop();
            } else {
                if (s.charAt(i) == '-') {
                    sign = -1;
                    i += 1;
                }
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = 10 * num + (s.charAt(i) - '0');
                    i+=1;
                }
                i-=1;
                if(sign == -1){
                    num = -num;
                }
                stack.peek().add(new NestedInteger(num));
                num = 0;
                sign = 1;
            }
        }
        return result;
    }

    @Test
    public void testDeserialize() {
        //String str = "[123,[456,223,[789,225]]]";
        String str = "[-123,456,[788,-799,833],[[]],10,[-98],-35]";
        //String str = "324";
        //String str = "[111,555]";
        NestedInteger nestedInteger = deserialize(str);
        System.out.println("nestedInteger=" + nestedInteger);
    }
}

package leetCode.simple;


import org.junit.Test;

import java.util.Stack;

//给你两个二进制字符串，返回它们的和（用二进制表示）。
//
// 输入为 非空 字符串且只包含数字 1 和 0。
//
//
//
// 示例 1:
//
// 输入: a = "11", b = "1"
//输出: "100"
//
// 示例 2:
//
// 输入: a = "1010", b = "1011"
//输出: "10101"
//
//
//
// 提示：
//
//
// 每个字符串仅由字符 '0' 或 '1' 组成。
// 1 <= a.length, b.length <= 10^4
// 字符串如果不是 "0" ，就都不含前导零。
public class LeetCode67 {

    public String addBinary(String a, String b) {
        int aLength = a.length();
        int bLength = b.length();
        int i = aLength - 1;
        int j = bLength - 1;
        Stack<Integer> stack = new Stack<>();
        boolean updateFlag = false;
        int sum = 0;
        while (i >= 0 || j >= 0) {
            sum = (i >= 0 ? (a.charAt(i) - '0') : 0) + (j >= 0 ? (b.charAt(j) - '0') : 0) + (updateFlag ? 1 : 0);
            if (sum >= 2) {
                stack.push(sum - 2);
                updateFlag = true;
            } else {
                stack.push(sum);
                updateFlag = false;
            }
            i -= 1;
            j -= 1;
        }
        if (updateFlag) {
            stack.push(1);
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    @Test
    public void test() {
        String a = "1111";
        String b = "1111";
        String result = addBinary(a, b);
        System.out.println("result=" + result);
    }
}

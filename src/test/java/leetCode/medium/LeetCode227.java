package leetCode.medium;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

//给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
////
//// 整数除法仅保留整数部分。
////
////
////
////
////
//// 示例 1：
////
////
////输入：s = "3+2*2"
////输出：7
////
////
//// 示例 2：
////
////
////输入：s = " 3/2 "
////输出：1
////
////
//// 示例 3：
////
////
////输入：s = " 3+5 / 2 "
////输出：5
////
////
////
////
//// 提示：
////
////
//// 1 <= s.length <= 3 * 105
//// s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
//// s 表示一个 有效表达式
//// 表达式中的所有整数都是非负整数，且在范围 [0, 231 - 1] 内
//// 题目数据保证答案是一个 32-bit 整数
public class LeetCode227 {

    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int length = s.length();
        int sum = 0;
        Stack<Character> operStack = new Stack<>();
        List<Character> operList = Arrays.asList('+', '-', '*', '/');
        for (int i = 0; i < length; i++) {
            if (s.charAt(i) == ' ') {
                continue;
            }
            if (operList.contains(s.charAt(i))) {
                stack.push(sum);
                sum = 0;
                if (operStack.isEmpty()) {
                    operStack.push(s.charAt(i));
                }else if(s.charAt(i)=='+' || s.charAt(i)=='-'){

                }

            } else {
                sum = 10 * sum + (s.charAt(i) - '0');
            }

        }
        return sum;
    }
}

package nowCode.medium;
//给定一个包含正整数、加(+)、减(-)、乘(*)、除(/)的算数表达式(括号除外)，计算其结果。
//
// 表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格 。 整数除法仅保留整数部分。
//
// 示例 1:
//
// 输入: "3+2*2"
//输出: 7
//
//
// 示例 2:
//
// 输入: " 3/2 "
//输出: 1
//
// 示例 3:
//
// 输入: " 3+5 / 2 "
//输出: 5
//
//
// 说明：
//
//
// 你可以假设所给定的表达式都是有效的。
// 请不要使用内置的库函数 eval。

import org.junit.Test;

import java.util.*;

public class NowCode1626 {

    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        char oper = '+';
        int num = 0;
        List<Character> operList = Arrays.asList('+','*','/','-');
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                num = num * 10 + (s.charAt(i) - '0');
            }
            if(operList.contains(s.charAt(i)) || i == s.length()-1){
                if (oper == '*' || oper == '/') {
                    int res = 0;
                    if (oper == '*') {
                        res = stack.pop() * num;
                    } else {
                        res = stack.pop() / num;
                    }
                    stack.push(res);
                } else {
                    stack.push(oper == '-' ? -num : num);
                }
                num = 0;
                oper = s.charAt(i);
            }
        }
        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }

    @Test
    public void testCalculate() {
        String s = "3/2";
        int result = calculate(s);
        System.out.println("result=" + result);
    }
}

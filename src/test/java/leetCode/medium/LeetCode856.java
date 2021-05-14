package leetCode.medium;

import org.junit.Test;

import java.util.Stack;

//给定一个平衡括号字符串 S，按下述规则计算该字符串的分数：
//
//
// () 得 1 分。
// AB 得 A + B 分，其中 A 和 B 是平衡括号字符串。
// (A) 得 2 * A 分，其中 A 是平衡括号字符串。
//
//
//
//
// 示例 1：
//
// 输入： "()"
//输出： 1
//
//
// 示例 2：
//
// 输入： "(())"
//输出： 2
//
//
// 示例 3：
//
// 输入： "()()"
//输出： 2
//
//
// 示例 4：
//
// 输入： "(()(()))"
//输出： 6
//
public class LeetCode856 {

    public int scoreOfParentheses(String s) {
        Stack<String> stack = new Stack<String>();
        int sum = 0;
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push("(");
            } else {
                while (!stack.peek().equals("(")) {
                    num += Integer.parseInt(stack.pop()) * 2;
                }
                //弹出(
                stack.pop();
                if (num == 0) {
                    num = 1;
                }
                stack.push(Integer.toString(num));
                num = 0;
            }
        }
        while(!stack.isEmpty()){
            sum+=Integer.parseInt(stack.pop());
        }
        return sum;
    }

    @Test
    public void testScoreOfParentheses() {
        String str = "(()(()))";
        int result = scoreOfParentheses(str);
        System.out.println("result=" + result);
    }
}

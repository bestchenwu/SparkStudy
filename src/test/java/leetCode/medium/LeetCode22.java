package leetCode.medium;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/generate-parentheses/
 * <p>
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 * <p>
 * 例如，给出 n = 3，生成结果为：
 * <p>
 * [
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 */
public class LeetCode22 {

    public void generateParenthesis1(String str, int left, int right, List<String> result) {
        if (left == 0 && right == 0) {
            result.add(str);
            return;
        }
        //如果left >0 表明还可以放左括号
        if (left > 0) {
            generateParenthesis1(str + "(", left - 1, right, result);
        }
        //说明右括号比左括号少,可以继续放右括号
        if (left < right) {
            generateParenthesis1(str + ")", left, right - 1, result);
        }
    }

    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        generateParenthesis1("", n, n, list);
        return list;
    }

    public static void main(String[] args) {
        LeetCode22 leetCode22 = new LeetCode22();
        List<String> list = leetCode22.generateParenthesis(3);
        System.out.println(list);
    }
}

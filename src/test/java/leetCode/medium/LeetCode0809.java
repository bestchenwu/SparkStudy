package leetCode.medium;

import org.junit.Test;

import java.util.Stack;

//括号。设计一种算法，打印n对括号的所有合法的（例如，开闭一一对应）组合。
//
// 说明：解集不能包含重复的子集。
//
// 例如，给出 n = 3，生成结果为：
//
//
//[
//  "((()))",
//  "(()())",
//  "(())()",
//  "()(())",
//  "()()()"
//]
//
import java.util.*;

public class LeetCode0809 {

    private List<String> res = new ArrayList<>();

    private void helpGenerateParenthesis(int left, int right, Stack<Character> path, int n) {
        if (left == n && right == n) {
            StringBuilder sb = new StringBuilder();
            for (Character character : path) {
                sb.append(character);
            }
            res.add(sb.toString());
            return;
        }
        if (left < n) {
            path.push('(');
            helpGenerateParenthesis(left + 1, right, path, n);
            path.pop();
        }
        if (right < left) {
            path.push(')');
            helpGenerateParenthesis(left, right + 1, path, n);
            path.pop();
        }
    }

    public List<String> generateParenthesis(int n) {
        Stack<Character> path = new Stack<>();
        helpGenerateParenthesis(0, 0, path, n);
        return res;
    }

    @Test
    public void testGenerateParenthesis() {
        int n = 3;
        List<String> list = generateParenthesis(n);
        System.out.println(list);
    }
}

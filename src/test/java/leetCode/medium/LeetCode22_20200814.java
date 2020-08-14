package leetCode.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 括号生成
 *
 * https://leetcode-cn.com/problems/generate-parentheses/
 *
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * 示例：
 *
 * 输入：n = 3
 * 输出：[
 *        "((()))",
 *        "(()())",
 *        "(())()",
 *        "()(())",
 *        "()()()"
 *      ]
 *
 *
 * @author chenwu on 2020.8.14
 */
public class LeetCode22_20200814 {

    public void helpGenerateParenthesis(int left, int right, Stack<Character> stack, int n,List<String> resultList){
        if(stack.size()==n*2){
            String result = "";
            for(int i = 0;i<stack.size();i++){
                result += stack.get(i);
            }
            resultList.add(result);
            return;
        }
        if(left<n){
            stack.push('(');
            helpGenerateParenthesis(left+1,right,stack,n,resultList);
            stack.pop();
        }
        if(left>right){
            stack.push(')');
            helpGenerateParenthesis(left,right+1,stack,n,resultList);
            stack.pop();
        }
    }

    public List<String> generateParenthesis(int n) {
        Stack<Character> stack = new Stack<>();
        List<String> resultList = new ArrayList<>();
        helpGenerateParenthesis(0,0,stack,n,resultList);
        return resultList;
    }

    public static void main(String[] args) {
        LeetCode22_20200814 leetCode22_20200814 = new LeetCode22_20200814();
        List<String> list = leetCode22_20200814.generateParenthesis(3);
        System.out.println(list);
    }
}

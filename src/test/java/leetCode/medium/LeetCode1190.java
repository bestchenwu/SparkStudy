package leetCode.medium;

import org.junit.Test;

import java.util.Stack;

//给出一个字符串 s（仅含有小写英文字母和括号）。
//
// 请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。
//
// 注意，您的结果中 不应 包含任何括号。
//
//
//
// 示例 1：
//
//
//输入：s = "(abcd)"
//输出："dcba"
//
//
// 示例 2：
//
//
//输入：s = "(u(love)i)"
//输出："iloveu"
//解释：先反转子字符串 "love" ，然后反转整个字符串。
//
// 示例 3：
//
//
//输入：s = "(ed(et(oc))el)"
//输出："leetcode"
//解释：先反转子字符串 "oc" ，接着反转 "etco" ，然后反转整个字符串。
//
// 示例 4：
//
//
//输入：s = "a(bcdefghijkl(mno)p)q"
//输出："apmnolkjihgfedcbq"
//
//
//
//
// 提示：
//
//
// 0 <= s.length <= 2000
// s 中只有小写英文字母和括号
// 题目测试用例确保所有括号都是成对出现的
public class LeetCode1190 {

    public String reverseParentheses(String s) {
        Stack<String> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i)!='(' && s.charAt(i)!=')'){
                sb.append(s.charAt(i));
            }else{
                if (sb.length() > 0) {
                    stack.push(sb.toString());
                    sb = new StringBuilder();
                }
                if(s.charAt(i)=='('){
                    stack.push("(");
                }else{
                    StringBuilder tmp = new StringBuilder();
                    while (!stack.isEmpty() && !stack.peek().equals("(")) {
                        tmp.append(new StringBuilder(stack.pop()).reverse());
                    }
                    stack.pop();
                    stack.push(tmp.toString());
                }
            }
        }
        StringBuilder result = new StringBuilder();
        for(int i = 0;i<stack.size();i++){
            result.append(stack.get(i));
        }
        if(sb.length()>0){
            result.append(sb);
        }
        return result.toString();
    }

    public String reverseParentheses1(String s){
        Stack<StringBuilder> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<s.length();i++){
            if(s.charAt(i)!='(' && s.charAt(i)!=')'){
                sb.append(s.charAt(i));
            }else{
                if(sb.length()>0){
                    stack.push(sb);
                    sb = new StringBuilder();
                }
                if(s.charAt(i)=='('){
                    stack.push(new StringBuilder("("));
                }else{
                    StringBuilder tmp = new StringBuilder();
                    while(!stack.isEmpty() && !stack.peek().toString().equals("(")){
                        tmp.append(stack.pop().reverse());
                    }
                    stack.pop();
                    stack.push(tmp);
                }
            }
        }
        StringBuilder result = new StringBuilder();
        for(int i = 0;i<stack.size();i++){
            result.append(stack.get(i));
        }
        if(sb.length()>0){
            result.append(sb);
        }
        return result.toString();
    }

    @Test
    public void testReverseParentheses(){
        String str = "a(bcdefghijkl(mno)p)q";
        String result = reverseParentheses1(str);
        System.out.println("result="+result);
    }
}

package nowCode.medium;

import org.junit.Test;

import java.util.*;
//输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，
//则输出"student. a am I"。
//
//
//
// 示例 1：
//
// 输入: "the sky is blue"
//输出: "blue is sky the"
//
//
// 示例 2：
//
// 输入: "  hello world!  "
//输出: "world! hello"
//解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
//
//
// 示例 3：
//
// 输入: "a good   example"
//输出: "example good a"
//解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
//
//
//
//
// 说明：
//
//
// 无空格字符构成一个单词。
// 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
// 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
//
//
// 注意：本题与主站 151 题相同：https://leetcode-cn.com/problems/reverse-words-in-a-string/
//
//
// 注意：此题对比原题有改动
// Related Topics 双指针 字符串 👍 141 👎 0

public class NowOffer58 {

    public String reverseWords1(String s) {
        if (s.isEmpty()) {
            return s;
        }
        //先去掉首尾空格
        s = s.trim();
        int begin = s.length() - 1;
        int end = s.length() - 1;
        String res = "";
        while (end >= 0) {
            //寻找第一个空格
            while (begin >= 0 && s.charAt(begin) != ' ') {
                begin--;
            }
            res += s.substring(begin + 1, end + 1) + " ";
            //寻找字符串的结尾
            while (begin >= 0 && s.charAt(begin) == ' ') {
                begin -= 1;
            }
            end = begin;
        }
        return res.trim();
    }

    public String reverseWords(String s) {
        if (s.isEmpty()) {
            return s;
        }
        Stack<String> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                if (sb.length() > 0) {
                    stack.push(sb.toString());
                    sb = new StringBuilder();
                }
                continue;
            } else {
                sb.append(s.charAt(i));
            }
        }
        if (sb.length() > 0) {
            stack.push(sb.toString());
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.append(stack.pop());
            if (stack.size() > 0) {
                res.append(" ");
            }
        }
        return res.toString();
    }

    @Test
    public void testReverseWords() {
        String s = "the sky is blue";
        String res = reverseWords1(s);
        System.out.println("res=" + res);
    }
}

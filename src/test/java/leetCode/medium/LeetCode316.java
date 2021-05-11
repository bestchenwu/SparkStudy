package leetCode.medium;

import org.junit.Test;

import java.util.Stack;

//给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
//
// 注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-distinct
//-characters 相同
//
//
//
// 示例 1：
//
//
//输入：s = "bcabc"
//输出："abc"
//
//
// 示例 2：
//
//
//输入：s = "cbacdcbc"
//输出："acdb"
public class LeetCode316 {

    public String removeDuplicateLetters(String s) {
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a'] += 1;
        }
        boolean[] exists = new boolean[26];
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']--;

            if (exists[s.charAt(i) - 'a']) {
                continue;
            }
            //尽可能的维持一个单调递增栈
            while (!stack.isEmpty() && stack.peek() > s.charAt(i)) {
                //说明字符串中只有该一个字符,不能删
                if (count[(stack.peek() - 'a')] == 0) {
                    break;
                }
                exists[(stack.peek() - 'a')] = false;
                stack.pop();
            }
            stack.push(s.charAt(i));
            exists[s.charAt(i) - 'a'] = true;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

    @Test
    public void testRemoveDuplicateLetters() {
        String str = "cbacdcbc";
        String result = removeDuplicateLetters(str);
        System.out.println("result=" + result);
    }

}

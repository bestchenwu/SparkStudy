package leetCode.medium;

import org.junit.Test;

import java.util.*;
//返回 s 字典序最小的子序列，该子序列包含 s 的所有不同字符，且只包含一次。
//
// 注意：该题与 316 https://leetcode.com/problems/remove-duplicate-letters/ 相同
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
//
//
//
// 提示：
//
//
// 1 <= s.length <= 1000
// s 由小写英文字母组成
public class LeetCode316_20210618 {

    public String smallestSubsequence(String s) {
        int[] characterNum = new int[26];
        boolean[] exists = new boolean[26];
        Stack<Character> stack = new Stack<>();
        //形成一个单调递增栈
        int len = s.length();
        for (int i = 0; i < len; i++) {
            characterNum[s.charAt(i) - 'a'] += 1;
        }
        for (int i = 0; i < len; i++) {
            Character tmp = s.charAt(i);
            characterNum[tmp - 'a']--;
            if(exists[tmp-'a']){
                continue;
            }

            while (!stack.isEmpty() && stack.peek() >= tmp) {
                if (characterNum[stack.peek() - 'a'] > 0) {
                    exists[stack.peek()-'a']=false;
                    stack.pop();
                } else {
                    break;
                }
            }
            stack.push(tmp);
            exists[tmp-'a']=true;
        }
        StringBuilder sb = new StringBuilder();
        for(int j = 0;j<stack.size();j++){
            sb.append(stack.get(j));
        }
        return sb.toString();
    }

    @Test
    public void testSmallestSubsequence(){
        String str = "cbacdcbc";
        System.out.println("result="+smallestSubsequence(str));
    }
}

package leetCode.medium;

import org.junit.Test;

/**
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 *
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 *
 * 输入: "cbbd"
 * 输出: "bb"
 *
 */
public class LeetCode5_20220616 {

    public String longestPalindrome(String s) {
        int len = s.length();
        boolean[][] dp = new boolean[len][len];//dp[i][j]表示从i到j是否是回文字符串
        int maxLen = 0;
        String str = "";
        for(int i = 0;i<len;i++){
            for(int j = 0;j<=i;j++){
                dp[j][i] = (i-j<=2 || dp[j+1][i-1]) && (s.charAt(i) == s.charAt(j));
                if(dp[j][i] && i-j+1>maxLen){
                    maxLen = i-j+1;
                    str = s.substring(j,i+1);
                }
            }
        }
        return str;
    }

    @Test
    public void testIsLongestPalindrome(){
        String str = "a";
        String res = longestPalindrome(str);
        System.out.println(res);
    }
}

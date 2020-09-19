package leetCode.medium;

/**
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 *
 * 5. 最长回文子串
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
 * @author chenwu on 2020.9.19
 */
public class LeetCode5_20200919 {

    /**
     * 定义一个二维数组，boolean[][] dp ,dp[i][j]表示从i到j是否是回文子串
     * 那么dp[i+1][j] = for(int k = 0;k<i;k++) s[i+1] == s[k] && dp[k][j]
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if(s == null || s.length()==0){
            return s;
        }
        int length = s.length();
        boolean[][] dp = new boolean[length][length];
        dp[0][0] = true;
        int maxLength = 1;
        String result = s.substring(0,1);
        for(int i = 0;i<length;i++){
            dp[i][i] = true;
            for(int j = i-1;j>=0;j--){
                if(s.charAt(j) == s.charAt(i) && (i-j<=1 || dp[j+1][i-1]) ){
                    dp[j][i] = true;
                    if(i-j+1>maxLength){
                        maxLength=i-j+1;
                        result = s.substring(j,j+maxLength);
                    }

                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "aabbb";
        LeetCode5_20200919 leetCode5_20200919 = new LeetCode5_20200919();
        String longestPalindrome = leetCode5_20200919.longestPalindrome(s);
        System.out.println(longestPalindrome);
    }
}

package leetCode.medium;

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
public class LeetCode5 {

    /**
     * 动态规划法
     *
     * 假定p(i,j)是回文字符串,那么
     * p(i-1,j+1) = (p(i,j)+s(i)==s(j))
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int length = s.length();
        //这里的boolean数组的第一维代表起始位置
        //第二维代表结束位置
        boolean[][] p = new boolean[length][length];
        int maxLen = 0;
        String maxString = "";
        for(int len=1;len<length;len++){
            for(int start = 0;start<length;start++){
                int end  = start+len-1;
                if(end>=length){
                    continue;
                }
                p[start][end] = (len == 1 || len == 2 || p[start+1][end-1]) && s.charAt(start)==s.charAt(end);
                if(p[start][end]){
                    if(end-start>maxLen){
                        maxLen = end-start;
                        maxString = s.substring(start,end+1);
                    }
                }
            }
        }
        return maxString;
    }

    public static void main(String[] args) {
        LeetCode5 leetCode5 = new LeetCode5();
        String str = "babad";
        String result = leetCode5.longestPalindrome(str);
        System.out.println(result);
    }
}

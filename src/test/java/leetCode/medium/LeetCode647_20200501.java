package leetCode.medium;

/**
 * https://leetcode-cn.com/problems/palindromic-substrings/
 */
public class LeetCode647_20200501 {

    public int countSubstrings(String s) {
        if(s==null || s.length()==0){
            return 0;
        }
        int length = s.length();
        int count = 0;
        //dp表示从i到j位置是否是回文子串
        boolean[][] dp = new boolean[length][length];
        int len = 1;
        for(;len<=length;len++){
            for(int i = 0;i<length;i++){
                int end = i+len-1;
                if(end>=length){
                    continue;
                }
                dp[i][end] = (s.charAt(i) == s.charAt(end)) && (len==1 || len == 2 || dp[i+1][end-1]);
                if(dp[i][end]){
                    count+=1;
                }
            }
        }
        return count;
    }
}

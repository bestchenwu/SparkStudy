package leetCode.medium;

/**
 * https://leetcode-cn.com/problems/palindromic-substrings/
 *
 * 647. 回文子串
 *
 *
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 *
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。
 *
 * 示例 1:
 *
 * 输入: "abc"
 * 输出: 3
 * 解释: 三个回文子串: "a", "b", "c".
 * 示例 2:
 *
 * 输入: "aaa"
 * 输出: 6
 * 说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa".
 *
 */
public class LeetCode647 {

    private boolean checkIspalindrome(String s,int startIndex,int endIndex){
        while(startIndex<endIndex && s.charAt(startIndex)==s.charAt(endIndex)){
            startIndex++;
            endIndex--;
        }
        return startIndex>=endIndex?true:false;
    }

    private int countPalindrome(String s,int endIndex){
        int count = 0;
        for(int i = 0;i<endIndex;i++){
            if(checkIspalindrome(s,i,endIndex)){
                count+=1;
            }
        }
        return count;
    }

    /**
     * 假设dp[i]表示在第i个位置的回文子串个数,
     * 那么d[i+1] = d[i]+1(自身)+当前字符与之前字符串的组成的新回文字符串的个数
     *
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        int length = s.length();
        int lastIndex = length-1;
        //每一项代表当前位置处的回文字符串个数
        int[] dp = new int[length];
        dp[0] = 1;
        for(int i = 1;i<=lastIndex;i++){
            dp[i] = dp[i-1]+1+countPalindrome(s,i);
        }
        return dp[lastIndex];
    }

    public static void main(String[] args) {
        LeetCode647 leetCode647 = new LeetCode647();
//        String s = "abcda";
//        System.out.println("is palidinrome:"+leetCode647.checkIspalindrome(s,0,s.length()-1));
        //String s = "aaa";
        String s = "ababac";
        System.out.println("count:"+leetCode647.countSubstrings(s));
    }
}

package leetCode.medium;


import java.util.Arrays;
import java.util.List;

/**
 * 139. 单词拆分
 * <p>
 * https://leetcode-cn.com/problems/word-break/
 * <p>
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * <p>
 * 说明：
 * <p>
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 * <p>
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * 示例 2：
 * <p>
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 * 示例 3：
 * <p>
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 */
public class LeetCode139 {


    private enum State{
        TRUE,
        UNKOWN,
        FALSE
    }
    private State[][] dp ;

    public boolean wordBreak(String s, List<String> wordDict) {
        dp = new State[s.length()+1][s.length()+1];
        for(int i = 0;i<dp.length;i++){
            for(int j = 0;j<dp.length;j++){
                dp[i][j] = State.UNKOWN;
            }
        }
        boolean wordBreak = helpWordBreak(s, 0, wordDict,s.length());
        return wordBreak;
    }



    /**
     * 假设dp[i][j]表示[i,j)的字符串是否能wordBreak,那么dp[0][i] = dp[0][i-1] && [i-1,i)也在里面
     * 最后就是求dp[0][s.length]是否为true
     *
     *
     * 从左至右先找到能最大包含的字符串,然后对剩余的字符串进行循环调用
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean helpWordBreak(String s,int start, List<String> wordDict,int maxLength) {
        if(s==null||s.length()==0 || wordDict==null || wordDict.isEmpty()){
            return false;
        }
        if(dp[start][start+s.length()] != State.UNKOWN){
            return dp[start][start+s.length()]==State.TRUE?true:false;
        }
        if(wordDict.contains(s)){
            return true;
        }
        int length = s.length();
        for(int i = 0;i<=length;i++){
            if(wordDict.contains(s.substring(0,i))){
                dp[start][i+start] = State.TRUE;
                String lastString = s.substring(i,length);
                if(helpWordBreak(lastString,i+start,wordDict,maxLength)){
                    return true;
                }else{
                    dp[start+i][maxLength] = State.FALSE;
                }
            }else{
                dp[start][i+start] = State.FALSE;
            }
        }
        return false;
    }



    public static void main(String[] args) {
        LeetCode139 leetCode139 = new LeetCode139();
//        String s = "applepenapple";
//        List<String> list = Arrays.asList("apple", "pen");
//        String s = "catsandog";
//        List<String> list = Arrays.asList("cats", "dog", "san", "and", "cat","sand");
        String s = "acccbccb";
        List<String> list = Arrays.asList("cc","bc","ac","ca");
        boolean wordBreak = leetCode139.wordBreak(s, list);
        System.out.println("wordBreak="+wordBreak);
    }
}

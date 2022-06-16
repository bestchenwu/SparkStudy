package nowCode.huawei;

/**
 *  给定两个只包含小写字母的字符串，计算两个字符串的最大公共子串的长度。
 * 注：子串的定义指一个字符串删掉其部分前缀和后缀（也可以不删）后形成的字符串。
 * 数据范围：字符串长度：1≤s≤150 1\le s\le 150\ 1≤s≤150
 * 进阶：时间复杂度：O(n3) O(n^3)\ O(n3) ，空间复杂度：O(n) O(n)\ O(n)
 * 输入描述：
 *
 * 输入两个只包含小写字母的字符串
 * 输出描述：
 *
 * 输出一个整数，代表最大公共子串的长度
 *
 * @author chenwu on 2022.6.16
 */
public class HJ75 {


    public int maxDuplicateLength(String str1,String str2){
        if(str1.length()==0 || str2.length()==0){
            return 0;
        }
        int len1 = str1.length(),len2 = str2.length();
        int[][] dp = new int[len1][len2];//dp[i][j]表示以str1[i]到str2[j]之间的最长公共字串长度
        int maxLen = 0;
        for(int i = 0;i<len1;i++){
            for(int j = 0;j<len2;j++){
                if(str1.charAt(i)==str2.charAt(j)){
                    if(i==0 || j ==0){
                        dp[i][j] = 1;
                    }else{
                        dp[i][j] = dp[i-1][j-1]+1;
                    }
                }
                maxLen = Math.max(maxLen,dp[i][j]);
            }
        }
        return maxLen;
    }
}

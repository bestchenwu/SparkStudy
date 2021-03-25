package leetCode.simple;

import org.junit.Test;

//给定一个字符串 s，计算具有相同数量 0 和 1 的非空（连续）子字符串的数量，并且这些子字符串中的所有 0 和所有 1 都是连续的。
//
// 重复出现的子串要计算它们出现的次数。
//
//
//
// 示例 1 :
//
//
//输入: "00110011"
//输出: 6
//解释: 有6个子串具有相同数量的连续1和0：“0011”，“01”，“1100”，“10”，“0011” 和 “01”。
//
//请注意，一些重复出现的子串要计算它们出现的次数。
//
//另外，“00110011”不是有效的子串，因为所有的0（和1）没有组合在一起。
//
//
// 示例 2 :
//
//
//输入: "10101"
//输出: 4
//解释: 有4个子串：“10”，“01”，“10”，“01”，它们具有相同数量的连续1和0。
//
//
//
//
// 提示：
//
//
// s.length 在1到50,000之间。
// s 只包含“0”或“1”字符。
//计数二进制子串
public class LeetCode696 {

    public int countBinarySubstrings(String s) {
        int sLength = s.length();
        if (sLength <= 1) {
            return 0;
        }
        //这里存储每个位置对应的连续0 和 连续1的个数
        int[][] dp = new int[sLength][2];
        dp[0][0] = s.charAt(0) == '0' ? 1 : 0;
        dp[0][1] = s.charAt(0) == '0' ? 0 : 1;
        int count = 0;
        for (int i = 1; i < sLength; i++) {
            dp[i][0] = s.charAt(i) == '0' ? 1 + dp[i - 1][0] : 0;
            dp[i][1] = s.charAt(i) == '0' ? 0 : 1 + dp[i - 1][1];
            if (dp[i][0] > 0 && ((dp[i][0] <= i && dp[i][0] <= dp[i - dp[i][0]][1]) || dp[i - 1][1] >= 1)) {
                count += 1;
            }
            if (dp[i][1] > 0 && ((dp[i][1] <= i && dp[i][1] <= dp[i - dp[i][1]][0]) || dp[i - 1][0] >= 1)) {
                count += 1;
            }
        }
        return count;
    }

    @Test
    public void testCountBinarySubstrings() {
        //int count = countBinarySubstrings("00110011");
        int count = countBinarySubstrings("10101");
        //int count = countBinarySubstrings("000111000");
        //01 0011 000111 10 1100 111000
        System.out.println("count=" + count);
    }
}

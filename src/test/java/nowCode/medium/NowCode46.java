package nowCode.medium;

import org.junit.Test;

import java.util.Arrays;

//给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可
//能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
//
//
//
// 示例 1:
//
// 输入: 12258
//输出: 5
//解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
//
//
//
// 提示：
//
//
// 0 <= num < 2³¹
//
// Related Topics 字符串 动态规划 👍 327 👎 0
public class NowCode46 {


    public int translateNum(int num) {
        String str = String.valueOf(num);
        int len = str.length();
        int[] dp = new int[len+1];
        Arrays.fill(dp,1);
        for(int i = 2;i<=len;i++){
            if(str.charAt(i-2)=='1' || (str.charAt(i-2)=='2' && str.charAt(i-1)<'6')){
                dp[i] = dp[i-1]+dp[i-2];
            }else{
                dp[i] = dp[i-1];
            }
        }
        return dp[len];
    }

    @Test
    public void helpTranslateNum() {
        int num = 25;
        int count = translateNum(num);
        System.out.println("count=" + count);
    }
}

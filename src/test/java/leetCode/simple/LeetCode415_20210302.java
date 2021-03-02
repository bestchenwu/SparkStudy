package leetCode.simple;


import org.junit.Test;

import java.util.Stack;

//给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
//
//
//
// 提示：
//
//
// num1 和num2 的长度都小于 5100
// num1 和num2 都只包含数字 0-9
// num1 和num2 都不包含任何前导零
// 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式
public class LeetCode415_20210302 {

    public String addStrings(String num1, String num2) {
        int length1 = num1.length();
        int length2 = num2.length();
        int i = length1 - 1, j = length2 - 1;
        Stack<Integer> stack = new Stack<>();
        boolean updateFlag = false;
        int sum = 0;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >= 0) {
            sum = (i >= 0 ? num1.charAt(i) - '0' : 0) + (j >= 0 ? num2.charAt(j) - '0' : 0) + (updateFlag ? 1 : 0);
            if (sum >= 10) {
                sum = sum - 10;
                updateFlag = true;
            }else{
                updateFlag = false;
            }
            sb.append(sum);
            i-=1;
            j-=1;
        }
        if (updateFlag) {
            sb.append(1);
        }
        sb.reverse();
        return sb.toString();
    }

    @Test
    public void test() {
        String num1 = "0";
        String num2 = "0";
        String result = addStrings(num1, num2);
        System.out.println("result=" + result);
    }
}

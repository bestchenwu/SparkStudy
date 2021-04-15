package leetCode.simple;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

//给你一个字符串 word ，该字符串由数字和小写英文字母组成。
//
// 请你用空格替换每个不是数字的字符。例如，"a123bc34d8ef34" 将会变成 " 123 34 8 34" 。注意，剩下的这些整数为（相邻彼此至少有
//一个空格隔开）："123"、"34"、"8" 和 "34" 。
//
// 返回对 word 完成替换后形成的 不同 整数的数目。
//
// 只有当两个整数的 不含前导零 的十进制表示不同， 才认为这两个整数也不同。
//
//
//
// 示例 1：
//
//
//输入：word = "a123bc34d8ef34"
//输出：3
//解释：不同的整数有 "123"、"34" 和 "8" 。注意，"34" 只计数一次。
//
//
// 示例 2：
//
//
//输入：word = "leet1234code234"
//输出：2
//
//
// 示例 3：
//
//
//输入：word = "a1b01c001"
//输出：1
//解释："1"、"01" 和 "001" 视为同一个整数的十进制表示，因为在比较十进制值时会忽略前导零的存在。
public class LeetCode1805 {

    private String trimString(StringBuilder number) {
        if (number.length() == 1) {
            return number.toString();
        }
        if (number.charAt(0) == '0') {
            //将后面都是0的字符都删掉
            int i ;
            for (i = 1; i < number.length(); i++) {
                if(number.charAt(i)!='0'){
                    break;
                }
            }
            if(i==number.length()){
                return "0";
            }
            number.delete(0,i);
            return number.toString();
        } else {
            return number.toString();
        }
    }

    public int numDifferentIntegers(String word) {
        Set<String> numSet = new HashSet<>();
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) < '0' || word.charAt(i) > '9') {
                if (number.length() > 0) {
                    numSet.add(trimString(number));
                    number = new StringBuilder();
                }
                continue;
            }
            number.append(word.charAt(i));

        }
        if (number.length() > 0) {
            numSet.add(trimString(number));
        }
        return numSet.size();
    }

    @Test
    public void testNumDifferentIntegers() {
        String number = "01a000b00000";
        int result = numDifferentIntegers(number);
        System.out.println("result=" + result);
    }
}

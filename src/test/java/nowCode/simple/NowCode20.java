package nowCode.simple;

import org.junit.Test;

//请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
//
// 数值（按顺序）可以分成以下几个部分：
//
//
// 若干空格
// 一个 小数 或者 整数
// （可选）一个 'e' 或 'E' ，后面跟着一个 整数
// 若干空格
//
//
// 小数（按顺序）可以分成以下几个部分：
//
//
// （可选）一个符号字符（'+' 或 '-'）
// 下述格式之一：
//
// 至少一位数字，后面跟着一个点 '.'
// 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
// 一个点 '.' ，后面跟着至少一位数字
//
//
//
//
// 整数（按顺序）可以分成以下几个部分：
//
//
// （可选）一个符号字符（'+' 或 '-'）
// 至少一位数字
//
//
// 部分数值列举如下：
//
//
// ["+100", "5e2", "-123", "3.1416", "-1E-16", "0123"]
//
//
// 部分非数值列举如下：
//
//
// ["12e", "1a3.14", "1.2.3", "+-5", "12e+5.4"]
//
//
//
//
// 示例 1：
//
//
//输入：s = "0"
//输出：true
//
//
// 示例 2：
//
//
//输入：s = "e"
//输出：false
//
//
// 示例 3：
//
//
//输入：s = "."
//输出：false
//
// 示例 4：
//
//
//输入：s = "    .1  "
//输出：true
public class NowCode20 {

    public boolean isNumber(String s) {
        if (s.isEmpty()) {
            return false;
        }
        int index = 0;
        int len = s.length();
        while (index < len && s.charAt(index) == ' ') {
            index += 1;
        }
        if (index == len) {
            return false;
        }
        if (s.charAt(index) == '+' || s.charAt(index) == '-') {
            index += 1;
        }
        //如果后面再跟+ - 就表示错误
        if (s.charAt(index) == '+' || s.charAt(index) == '-') {
            return false;
        }
        while (index < len && isDigit(s.charAt(index))) {
            index += 1;
        }
        if (index == len) {
            return true;
        }
        if (s.charAt(index) == 'e' || s.charAt(index) == 'E') {
            if(index == 0){
                return false;
            }
            //那么后面必须是整数
            index += 1;
            if (index == len) {
                return false;
            }
            if (s.charAt(index) == '-') {
                index += 1;
            }
            boolean isAllDigit = isAllDigit(s, index, len);
            return isAllDigit;
        } else if (s.charAt(index) == '.') {
            index += 1;
            boolean isAllDigit = isAllDigit(s, index, len);
            return isAllDigit;
        } else {
            return false;
        }
    }

    private boolean isAllDigit(String s, int index, int len) {
        while (index < len) {
            if (!isDigit(s.charAt(index))) {
                return false;
            }
            index += 1;
        }
        return true;
    }

    private boolean isDigit(char item) {
        return (item >= '0' && item <= '9') || item==' ';
    }

    @Test
    public void testIsNumber() {
        String s = "    .1  ";
        boolean res = isNumber(s);
        System.out.println("res=" + res);
    }

}

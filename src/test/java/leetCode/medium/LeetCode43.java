package leetCode.medium;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/multiply-strings/
 * <p>
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * <p>
 * 示例 1:
 * <p>
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * <p>
 * 示例 2:
 * <p>
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * <p>
 * 说明：
 * <p>
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 */
public class LeetCode43 {

    int addBySelf = -1;

    private void addBySelfWithIsAdd(String long_num, int index, int isAdd1, Stack<Integer> result) {
        if (index == -1) {
            if (isAdd1 == 1) {
                result.push(isAdd1);
            }
            return;
        }
        char currentChar = long_num.charAt(index);
        int currentNumber = (currentChar - '0') * 2 + isAdd1;
        if (currentNumber >= 10) {
            currentNumber = currentNumber - 10;
            isAdd1 = 1;
        } else {
            isAdd1 = 0;
        }
        result.push(currentNumber);
        addBySelfWithIsAdd(long_num, index >= 0 ? index - 1 : -1, isAdd1, result);
    }

    private int addBySelf(String long_num) {
        if (addBySelf == -1) {
            Stack<Integer> result = new Stack<>();
            addBySelfWithIsAdd(long_num, long_num.length() - 1, 0, result);
            addBySelf = 0;
            while (result.size() > 0) {
                int size = result.size();
                addBySelf += result.pop() * (int) Math.pow(10, size - 1);
            }
        }
        return addBySelf;
    }

    /**
     * 将较长的字符串与一个整数相乘,实际上等于较长的字符串和自身相加，加的次数等于整数
     *
     * @param long_num
     * @param multi_num
     * @return
     */
    private int multiply(String long_num, int multi_num) {
        if (multi_num == 0) {
            return 0;
        }
        int result = 0;
        int half = multi_num / 2;
        if(half!=0){
            result += addBySelf(long_num) * half;
        }
        if (multi_num % 2 != 0) {
            //说明要将当前的结果与long_num再相加一次
        }
        return result;
    }


    /**
     * 123 * 456 = 123*6 + 123*50 + 123*400
     *
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        String long_num, short_num;
        if (num1.length() >= num2.length()) {
            long_num = num1;
            short_num = num2;
        } else {
            long_num = num2;
            short_num = num1;
        }
        int pow_index = 0;
        int result = 0;
        for (int index = short_num.length() - 1; index >= 0; index--) {
            char multi_char = short_num.charAt(index);
            int multi_num = multi_char - '0';
            int multiResult = multiply(long_num, multi_num);
            result += multiResult * (int) Math.pow(10, pow_index++);
        }
        return String.valueOf(result);
    }

    public static void main(String[] args) {
        LeetCode43 leetCode43 = new LeetCode43();
        //先测试字符串自加方法
//        int result = leetCode43.addBySelf("586");
//        System.out.println(result);
        String result = leetCode43.multiply("123", "456");
        System.out.println(result);
    }
}

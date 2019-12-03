package leetCode.medium;

import java.util.Arrays;
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

    /**
     * 将较长的字符串与一个整数相乘,实际上等于较长的字符串和自身相加，加的次数等于整数
     *
     * @param long_num
     * @param multi_num
     * @return
     */

    private long multiplyWithAddNum(String long_num, int index,int pow_index,int multi_num,long result) {
        if (multi_num == 0 || index == -1) {
            return result;
        }
        char current_char = long_num.charAt(index);
        long current_number = (long)(current_char-'0')*multi_num;
        result += current_number*(long)Math.pow(10,pow_index);
        index-=1;
        pow_index+=1;
        return multiplyWithAddNum(long_num,index,pow_index,multi_num,result);
    }

    /**
     *  586*8 = 6*8+80*8+500*8
     * @param long_num
     * @param multi_num
     * @return
     */
    private long multiply(String long_num, int multi_num) {
        if (multi_num == 0) {
            return 0l;
        }
        return multiplyWithAddNum(long_num,long_num.length()-1,0,multi_num,0l);
    }


    /**
     * 对于
     * "498828660196"
     * "840477629533"
     * 会输出不一致
     * 结果"-4216307207392701148"
     * 预期"419254329864656431168468"
     *
     * 123 * 456 = 123*6 + 123*50 + 123*400
     *
     * @param num1
     * @param num2
     * @return
     */
    @Deprecated
    public String multiply0(String num1, String num2) {
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
        long result = 0;
        for (int index = short_num.length() - 1; index >= 0; index--) {
            char multi_char = short_num.charAt(index);
            int multi_num = multi_char - '0';
            long multiResult = multiply(long_num, multi_num);
            result += multiResult * (int) Math.pow(10, pow_index++);
        }
        return String.valueOf(result);
    }

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
        String[] results = new String[long_num.length()];
        int results_index = 0;
        for(int long_index = long_num.length()-1;long_index>=0;long_index--){
            int long_index_num = long_num.charAt(long_index)-'0';
            int addNum = 0;
            StringBuilder result = new StringBuilder();
            for(int short_index = short_num.length()-1;short_index>=0;short_index--){
                int short_index_num = short_num.charAt(short_index)-'0';
                int current_multi_result = long_index_num*short_index_num+addNum;
                result.append(current_multi_result%10);
                addNum = current_multi_result/10;
            }
            if(addNum!=0){
                result.append(addNum);
            }
            //通过补0的方式代替乘法  避免整型溢出
            results[results_index++] = result.reverse().append(fillZero(long_num.length()-1-long_index)).toString();
        }

        String final_result = results[0];
        for(int i=1;i<results.length;i++){
            final_result=addStrings(final_result,results[i]);
        }
        return final_result;
    }

    private String fillZero(int n){
        char[] zero = new char[n];
        Arrays.fill(zero,'0');
        return new String(zero);
    }

    /**
     * i表示num1的指针,从右往左
     * j表示num2的指针,从右往左
     *
     * @param num1
     * @param num2
     * @param i
     * @param j
     * @param isAdd1
     * @return
     */
    private void addStringsWithIsAdd1(String num1, String num2, int i, int j, int isAdd1, Stack<Integer> result) {
        if (i == -1 && j == -1) {
            if(isAdd1==1){
                result.push(isAdd1);
            }
            return;
        }
        int currentNumber = 0;
        char num2Char = j >= 0 ? num2.charAt(j) : '0';
        char num1Char = i >= 0 ? num1.charAt(i) : '0';
        currentNumber = (num1Char - '0') + (num2Char - '0') + isAdd1;
        if (currentNumber >= 10) {
            isAdd1 = 1;
            currentNumber = currentNumber - 10;
        } else {
            isAdd1 = 0;
        }
        result.push(currentNumber);
        addStringsWithIsAdd1(num1, num2, i>=0?i - 1:-1, j>=0?j - 1:-1, isAdd1, result);
    }

    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1;
        Stack<Integer> stack = new Stack<>();
        addStringsWithIsAdd1(num1,num2,i,j,0,stack);
        String result = "";
        while(stack.size()>0){
            result+=String.valueOf(stack.pop());
        }
        return result;
    }

    public static void main(String[] args) {
        LeetCode43 leetCode43 = new LeetCode43();
        //先测试字符串自加方法
//        int result = leetCode43.multiply("586",8);
//        System.out.println(result);
        String result = leetCode43.multiply("498828660196", "840477629533");
        System.out.println(result);
    }
}

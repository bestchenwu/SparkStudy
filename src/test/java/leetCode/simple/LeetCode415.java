package leetCode.simple;

import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/add-strings/
 * <p>
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 * <p>
 * 注意：
 * <p>
 * num1 和num2 的长度都小于 5100.
 * num1 和num2 都只包含数字 0-9.
 * num1 和num2 都不包含任何前导零。
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
 */
public class LeetCode415 {

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
        LeetCode415 leetCode415 = new LeetCode415();
        //String addStrings = leetCode415.addStrings("1991", "25523");
        String addStrings = leetCode415.addStrings("1", "9");
        System.out.println(addStrings);
    }
}

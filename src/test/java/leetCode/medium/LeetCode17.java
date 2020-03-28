package leetCode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 *
 * 17. 电话号码的字母组合
 *
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 *
 *
 * 示例:
 *
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 *
 */
public class LeetCode17 {


    /**
     * 假设我们有一个8位数的二维数组char[][] = new char[][]{
     *     {'a','b','c'},
     *     {'d','e','f'}
     *     ...
     * }
     * 那么输入"234" 代表将第1行  第2行 第三行代表的二维数组进行组合
     *
     * 假设 dp[i]表示当坐标为i的时候的List<String> 那么dp[i+1] = 循环dp[i]的每一项将digits[i+1]插入到最后
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        /**
         * 假设我们有一个dp数组，索引为i处的值表示当前位置的字符串列表
         *       那么dp[i+1] = dp[i]的每一项添加i+1处代表的数组每一个char
         *       for(String s:dp[i]){
         *             for(char i : chars[i+1]){
         *                 String newStr = s+i;
         *                 dp[i+1].add(newStr);
         *             }
         *      }
         *
         * 注意到dp数组的每一项代表了一个List<String>
         *      所以这里最好用List<List<String>>来表示dp
         */
        char[][] digitChars = new char[][]{
                {'a', 'b', 'c'},
                {'d', 'e', 'f'},
                {'g', 'h', 'i'},
                {'j', 'k', 'l'},
                {'m','n','o'},
                {'p','q','r','s'},
                {'t','u','v'},
                {'w','x','y','z'}
      };
        if(digits==null||digits.length()==0){
            return Collections.emptyList();
        }
        List<List<String>> dpList = new ArrayList<>();
        int length = digits.length();
        int lastIndex = length-1;
        char[] digitChar0 = digitChars[digits.charAt(0)-'2'];
        List<String> dp0List = new ArrayList<>();
        for(char i :digitChar0){
            dp0List.add(String.valueOf(i));
        }
        dpList.add(dp0List);
        for(int i = 1;i<=lastIndex;i++){
            List<String> dp_i_1_list = dpList.get(i-1);
            char[] currentChars = digitChars[digits.charAt(i)-'2'];
            List<String> dp_i_list = new ArrayList<>();
            for(String str:dp_i_1_list){
                for(char charI : currentChars){
                    String newStr = str+charI;
                    dp_i_list.add(newStr);
                }
            }
            dpList.add(dp_i_list);
        }

        return dpList.get(lastIndex);
    }

    public static void main(String[] args) {
        LeetCode17 leetCode17 = new LeetCode17();
        String str = "72";
        List<String> strings = leetCode17.letterCombinations(str);
        System.out.println(strings);
    }
}

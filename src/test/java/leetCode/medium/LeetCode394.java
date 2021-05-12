package leetCode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/decode-string/
 *
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 *
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 *
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 *
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 *
 * 示例:
 *
 * s = "3[a]2[bc]", 返回 "aaabcbc".
 * s = "3[a2[c]]", 返回 "accaccacc".
 * s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".
 *
 * 这题和https://leetcode-cn.com/problems/valid-parentheses/  Leetcode20很相似
 */
public class LeetCode394 {

    /**
     * 1、构造堆栈stack,从左至右遍历字符串s
     * 2、判断当前字符是否是],如果不是压入到stack
     * 3、如果当前字符是],那么就构造一个字符串变量str,用于保存[]之间的字符串
     * 4、对于当前堆栈逆向寻找第一个出现的[,在遍历的过程中将栈中的元素字符charStr拼接到str
     * str = charStr+str;
     * 同时将charStr弹出
     * 5、找到[后，先弹出一次，目的是将'['字符弹出
     * 6、然后寻找[前面的数字,注意这里有个陷阱,数字有可能是二位数，三位数等等
     * int num = 0;//num的值
     * int numIndex = 0;//10的幂次方
     * while(!stack.isEmpty() && stack.peek()-'0'>=0 && '9'-stack.peek()>=0){
     * num += (stack.pop()-'0')(int)Math.pow(10,numIndex++);
     * }
     * 7、然后将numstr这样的字符串压入到栈中
     * 可以利用二重循环,如下所示:
     * for(int i = 1;i<=num;i++){
     * for(int j=0;j<str.length;j++){
     * stack.push(str.charAt(j));
     * }
     * }
     * 8、当字符串s遍历完成后，将stack从栈底到栈顶输出
     *
     * 空间复杂度O(s.length) 因为只用到了栈，及若干个临时字符串变量
     * 时间复杂度O(s.length) 只遍历了一次字符串,途中对于堆栈的遍历属于跟字符串的复杂程度有关系
     * 要注意的点就是对栈的操作要注意非空判断和取出[前面的正确数字
     *
     * @param s
     * @return
     */
    public String decodeString(String s) {
        Stack<Character> stack = new Stack<Character>();
        for(int i = 0;i<s.length();i++){
            char currentChar = s.charAt(i);
            if(currentChar!=']'){
                stack.push(currentChar);
            }else{
                //寻找stack里面最近的一个[
                String charStr = "";
                 while(stack.peek()!='['){
                    //因为一定可以找到左边的括号,所以不用判断数组越界
                    Character charItem = stack.pop();
                    charStr=charItem+charStr;
                }
                //找到了[,这个时候要找到[前面的数字
                stack.pop();//第一次弹出[
                //寻找数字
                int numIndex = 0;
                int num = 0;
                while(!stack.isEmpty() && stack.peek()-'0'>=0 && '9'-stack.peek()>=0){
                    num+= (stack.pop()-'0')*(int)Math.pow(10,numIndex++);
                }
                //这样新的字符串就是num*charStr
                for(int j= 1;j<=num;j++){
                    for(int k=0;k<charStr.length();k++){
                        stack.push(charStr.charAt(k));
                    }
                }
            }
        }
        String result = "";
        for(int i = 0;i<stack.size();i++){
            result = result +stack.get(i);
        }
        return result;
    }

    public String decodeString1(String s) {
        StringBuilder sb = new StringBuilder();
        Stack<String> stack = new Stack<>();
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                if (sb.length() > 0) {
                    stack.push(sb.toString());
                    sb = new StringBuilder();
                }
                num = num * 10 + (s.charAt(i) - '0');
            } else if (s.charAt(i) == '[') {
                stack.push(Integer.toString(num));
                num = 0;
            } else if (s.charAt(i) == ']') {
                if (sb.length() > 0) {
                    stack.push(sb.toString());
                    sb = new StringBuilder();
                }
                List<String> replicatedList = new ArrayList<>();
                while(!stack.isEmpty() && !(stack.peek().charAt(0)>='0'&&stack.peek().charAt(0)<='9')){
                    replicatedList.add(stack.pop());
                }
                Collections.reverse(replicatedList);
                StringBuilder replicatedString = new StringBuilder();
                replicatedList.forEach(item->replicatedString.append(item));
                int replicatedNum = Integer.parseInt(stack.pop());
                StringBuilder tmp = new StringBuilder();
                for (int k = 1; k <= replicatedNum; k++) {
                    tmp.append(replicatedString);
                }
                stack.push(tmp.toString());
            } else {
                sb.append(s.charAt(i));
            }
        }
        if (sb.length() > 0) {
            stack.push(sb.toString());
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < stack.size(); i++) {
            result.append(stack.get(i));
        }
        return result.toString();
    }

    public String decodeString2(String str){
        Stack<Integer> numStack = new Stack<Integer>();
        Stack<StringBuilder> strStack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int num = 0;
        for(int i = 0;i<str.length();i++){
            if(str.charAt(i)>='0'&& str.charAt(i)<='9'){
                num = num*10+(str.charAt(i)-'0');
            }else if(str.charAt(i)=='['){
                numStack.push(num);
                strStack.push(sb);
                num = 0;
                sb = new StringBuilder();
            }else if(str.charAt(i)==']'){
                StringBuilder tmp = new StringBuilder();
                int replicatedNum = numStack.pop();
                for(int k = 1;k<=replicatedNum;k++){
                    tmp.append(sb);
                }
                sb = strStack.pop();
                sb.append(tmp);
            }else{
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LeetCode394 leetCode394 = new LeetCode394();
        //String str = "3[a]2[bc]"; //希望获得aaabcbc
        //String str = "3[a2[c]]"; //希望获得3[acc] = accaccacc
        //String str = "2[abc]3[cd]ef"; //希望获得abcabccdcdcdef
        String str = "abc3[cd2[rft]jks]xyz";
        String result = leetCode394.decodeString2(str);
        System.out.println(result);
    }
}

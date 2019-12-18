package leetCode.simple;

import org.junit.Assert;

/**
 * https://leetcode-cn.com/problems/reverse-words-in-a-string-iii/
 *
 * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 *
 * 示例 1:
 *
 * 输入: "Let's take LeetCode contest"
 * 输出: "s'teL ekat edoCteeL tsetnoc" 
 *
 * 注意：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
 *
 */
public class LeetCode557 {

    public String reverseWords(String s) {
        if(s==null || s.isEmpty()){
            return "";
        }
        String finalResult = "";
        int index = 0;
        int length = s.length();
        while(index<length){
            int end = index;
            String tempResult = "";
            while(end<length && s.charAt(end)!=' '){
                tempResult = s.charAt(end)+tempResult;
                end++;
            }
            //添加完成后,在字符串末尾加上一个空白
            if(end!=length){
                tempResult=tempResult+" ";
            }
            finalResult = finalResult+tempResult;
            index = end+1;
        }
        return finalResult;
    }

    public static void main(String[] args) {
        String str = "Let's take LeetCode contest";
        LeetCode557 leetCode557 = new LeetCode557();
        String words = leetCode557.reverseWords(str);
        Assert.assertEquals("s'teL ekat edoCteeL tsetnoc",words);
        //System.out.println(words);
    }
}

package leetCode.simple;

import org.junit.Test;

//给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
//
//
//
// 示例：
//
// 输入："Let's take LeetCode contest"
//输出："s'teL ekat edoCteeL tsetnoc"
//
//
//
//
// 提示：
//
//
// 在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
public class LeetCode557_20210323 {

    private StringBuilder sb = new StringBuilder();

    private void append(int start,int end,String s){
        for(int i = end;i>=start;i--){
            sb.append(s.charAt(i));
        }
    }

    public String reverseWords(String s) {
        if(s == null || s.length()==0){
            return s;
        }
        int sLength = s.length();
        int lastSpaceIndex = -1;
        for(int i = 0;i<sLength;i++){
            if(Character.isWhitespace(s.charAt(i))){
                append(lastSpaceIndex+1,i-1,s);
                lastSpaceIndex = i;
                sb.append(s.charAt(i));
            }
        }
        if(lastSpaceIndex!=sLength-1){
            append(lastSpaceIndex+1,sLength-1,s);
        }
        return sb.toString();
    }

    @Test
    public void test(){
        String str = "Let's take LeetCode contest";
        String reverseWordsStr = reverseWords(str);
        System.out.println("reverseWordsStr="+reverseWordsStr);
    }
}

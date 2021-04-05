package leetCode.simple;

import org.junit.Test;

//对于字符串 S 和 T，只有在 S = T + ... + T（T 自身连接 1 次或多次）时，我们才认定 “T 能除尽 S”。
//
// 返回最长字符串 X，要求满足 X 能除尽 str1 且 X 能除尽 str2。
//
//
//
// 示例 1：
//
//
//输入：str1 = "ABCABC", str2 = "ABC"
//输出："ABC"
//
//
// 示例 2：
//
//
//输入：str1 = "ABABAB", str2 = "ABAB"
//输出："AB"
//
//
// 示例 3：
//
//
//输入：str1 = "LEET", str2 = "CODE"
//输出：""
//
//
//
//
// 提示：
//
//
// 1 <= str1.length <= 1000
// 1 <= str2.length <= 1000
// str1[i] 和 str2[i] 为大写英文字母
public class LeetCode1071 {

    public String gcdOfStrings(String str1, String str2) {
        int length1 = str1.length();
        int length2 = str2.length();
        for(int i = Math.min(length1,length2);i>=1;i--){
            if(length1%i==0 && length2%i == 0){
                String tmp = str1.substring(0,i);
                if(checkStrIsMatch(str1,tmp) && checkStrIsMatch(str2,tmp)){
                    return tmp;
                }
            }
        }
        return "";
    }

    private boolean checkStrIsMatch(String word,String pattern){
        int len = word.length()/pattern.length();
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ;i<len;i+=1){
            sb.append(pattern);
        }
        return sb.toString().equals(word);
    }

    public String gcdOfStrings2(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        String gcdStr = str1.substring(0,gcd(len1,len2));
        if(checkStrIsMatch(str1,gcdStr) && checkStrIsMatch(str2,gcdStr)){
            return gcdStr;
        }else{
            return "";
        }
    }


    /**
     * 如果(str1,str2) 和(str2,str1)拼接起来的字符串相等,那么说明一定存在这样的公约字符串
     * str1 = mx,str2 = nx   (m+n)*x == (n+m)*x
     *
     * @param str1
     * @param str2
     * @return
     */
    public String gcdOfStrings3(String str1, String str2) {
        if(!str1.concat(str2) .equals(str2.concat(str1))){
            return "";
        }
        int len1 = str1.length();
        int len2 = str2.length();
        String gcdStr = str1.substring(0,gcd(len1,len2));
        return gcdStr;
    }

    /**
     * 求最大公约数
     *
     * @param len1
     * @param len2
     * @return
     */
    public int gcd(int len1,int len2){
        int reminder = len1%len2;
        while(reminder!=0){
            len1 = len2;
            len2 = reminder;
            reminder = len1%len2;
        }
        return len2;
    }


    @Test
    public void testGdOfStrings(){
        String str1 = "ABAB";
        String str2 = "ABABAB";
        String gcdOfStrings = gcdOfStrings3(str1, str2);
        System.out.println("gcdOfString="+gcdOfStrings);
    }

}

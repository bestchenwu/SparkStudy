package leetCode.simple;

import org.junit.Test;

//给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
//
// 说明：本题中，我们将空字符串定义为有效的回文串。
//
// 示例 1:
//
// 输入: "A man, a plan, a canal: Panama"
//输出: true
//
//
// 示例 2:
//
// 输入: "race a car"
//输出: false
public class LeetCode125_20210224 {

    public boolean isPalindrome(String s) {
        if(s==null || s.length() == 0){
            return true;
        }
        int i = 0,j = s.length()-1;
        while(i<=j){
            if(!isValidCharacter(s.charAt(i))){
                i++;
                continue;
            }
            if(!isValidCharacter(s.charAt(j))){
                j--;
                continue;
            }
            if(!isPalindin(s.charAt(i),s.charAt(j))){
                return false;
            }else{
                i++;
                j--;
            }
        }
        return true;
    }

    private boolean isValidCharacter(char a){
        if(a>='0' && a<='9'){
            return true;
        }
        if(a>='a' && a<='z'){
            return true;
        }
        if(a>='A' && a<='Z'){
            return true;
        }
        return false;
    }

    private boolean isPalindin(char a,char b){
        if(a>='0' && a<='9'){
            return a == b;
        }
        if(a>='a' && a<='z'){
            return a == b || a-b == 32;
        }
        if(a>='A' && a<='Z'){
            return a == b || b-a == 32;
        }
        return false;
    }

    @Test
    public void test(){
        String str = "A man, a plan, a canal: Panama";
        boolean palindrome = isPalindrome(str);
        System.out.println("palindrome="+palindrome);
    }
}

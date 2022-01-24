package leetCode.simple;

import org.junit.Assert;
import org.junit.Test;

//给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
//
// 说明：本题中，我们将空字符串定义为有效的回文串。
//
//
//
// 示例 1:
//
//
//输入: "A man, a plan, a canal: Panama"
//输出: true
//解释："amanaplanacanalpanama" 是回文串
//
//
// 示例 2:
//
//
//输入: "race a car"
//输出: false
//解释："raceacar" 不是回文串
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 2 * 10⁵
// 字符串 s 由 ASCII 字符组成
public class LeetCode125_20220118 {

    public boolean isPalindrome(String s) {
        if(s==null || s.length()==0){
            return true;
        }
        int i = 0,j = s.length()-1;
        while(i<j){
            if(!checkIsAlpha(s.charAt(i))){
                i++;
                continue;
            }else if(!checkIsAlpha(s.charAt(j))){
                j--;
                continue;
            }else if(!checkIsEqual(s.charAt(i),s.charAt(j))){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    private boolean checkIsAlpha(char tmp){
        if(tmp>='a'&&tmp<='z'){
            return true;
        }
        if(tmp>='A'&&tmp<='Z'){
            return true;
        }
        if(tmp>='0'&&tmp<='9'){
            return true;
        }
        return false;
    }

    private boolean checkIsEqual(char tmp1,char tmp2){
        if(tmp1==tmp2){
            return true;
        }
        if((tmp1>='A' && tmp1<='Z') && (tmp2>='a' && tmp2<='z')){
            return tmp2-tmp1==32;
        }
        if((tmp2>='A' && tmp2<='Z') && (tmp1>='a' && tmp1<='z')){
            return tmp1-tmp2 == 32;
        }
        return false;
    }

    @Test
    public void testIsPalindrome(){
        String str = "A man, a plan, a canal: Panama";
        //String str = "0P";
        boolean res = isPalindrome(str);
        Assert.assertTrue(res);
    }
}

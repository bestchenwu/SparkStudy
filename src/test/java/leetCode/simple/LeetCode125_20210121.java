package leetCode.simple;
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
public class LeetCode125_20210121 {

    public boolean isPalindrome(String s) {
        if(s == null || s.length() == 0){
            return true;
        }
        int i = 0,j = s.length()-1;
        while(j>=i){
            if(!checkCharacterIsValid(s.charAt(i))){
                i++;
                continue;
            }
            if(!checkCharacterIsValid(s.charAt(j))){
                j--;
                continue;
            }
            if(!checkCharacterIsEqual(s.charAt(i),s.charAt(j))){
                return false;
            }else{
                i++;
                j--;
            }
        }
        return true;
    }

    private boolean checkCharacterIsValid(Character character){
        return (character-'a'>=0 && character-'z'<=0) || (character-'A'>=0 && character-'Z'<=0) || (character-'0'>=0 && character-'9'<=0);
    }

    private boolean checkCharacterIsEqual(Character a,Character b){
        if(a-'0'>=0 && a-'9'<=0){
            return a==b;
        }else{
            return a == b || Math.abs(a-b) == 32;
        }
    }

    public static void main(String[] args) {
        //String str = "A man, a plan, a canal: Panama";
        String str = "0P";
        LeetCode125_20210121 leetCode125_20210121 = new LeetCode125_20210121();
        boolean palindrome = leetCode125_20210121.isPalindrome(str);
        System.out.println("palindrome="+palindrome);
    }
}

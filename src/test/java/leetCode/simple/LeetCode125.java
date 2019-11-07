package leetCode.simple;

/**
 * https://leetcode-cn.com/problems/valid-palindrome/
 *
 * 回文字符串(只考虑字母和数字字符，可以忽略字母的大小写)
 */
public class LeetCode125 {

    public boolean checkCharIsValid(char char0){
        return (char0>='0'&&char0<='9')||(char0>='A'&&char0<='Z')||(char0>='a'&&char0<='z');
    }

    public boolean isPalindrome(String s) {
        if(s==null||s.length()==0){
            return false;
        }
        int leftIndex = 0,rightIndex = s.length()-1;
        while(rightIndex>leftIndex){
            char leftChar = s.charAt(leftIndex);
            if(!checkCharIsValid(leftChar)){
                leftIndex++;
                continue;
            }
            char rightChar = s.charAt(rightIndex);
            if(!checkCharIsValid(rightChar)){
                rightIndex--;
                continue;
            }
            if(leftChar>='0'&&leftChar<='9'){
                if(rightChar!=leftChar){
                    return false;
                }
            }
            if(leftChar>='A'&&leftChar<='z'){
                if(!(rightChar==leftChar||Math.abs(rightChar-leftChar)==32)){
                    return false;
                }
            }
            leftIndex++;
            rightIndex--;
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCode125 leetCode125 = new LeetCode125();
        String str = "`l;`` 1o1 ??;l`";
//        System.out.println('z'-'a');
//        System.out.println('a'-'Z');
        boolean result = leetCode125.isPalindrome(str);
        System.out.println(result);
    }
}

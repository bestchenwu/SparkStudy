package leetCode.simple;

/**
 * https://leetcode-cn.com/problems/palindrome-number/
 *
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 示例 1:
 *
 * 输入: 121
 * 输出: true
 * 示例 2:
 *
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3:
 *
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 *
 */
public class LeetCode9 {

    private boolean checkXISPalindrome(int x){
        if(x<0){
            return false;
        }
        if(x==0){
            return true;
        }
        int length = 0;
        int tmp = x;
        while(tmp!=0){
            tmp = tmp/10;
            length+=1;
        }
        if(length==1){
            return true;
        }
        int j = length-1;
        if(x/(int)Math.pow(10,j)!=x%(int)Math.pow(10,1)){
            return false;
        }else{
            x = (x-(int)Math.pow(10,j))/10;
            return checkXISPalindrome(x);
        }
    }

    public boolean isPalindrome(int x) {
       return checkXISPalindrome(x);
    }

    public static void main(String[] args) {
        LeetCode9 leetCode9 = new LeetCode9();
        int x = 303;
        boolean result = leetCode9.isPalindrome(x);
        System.out.println(result);
    }
}

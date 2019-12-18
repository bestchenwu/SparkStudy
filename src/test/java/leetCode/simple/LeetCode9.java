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

    /**
     * 对于1000021无法判断出来
     *
     * @param x
     * @return
     * @author chenwu on 2019.12.18
     */
    @Deprecated
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
            x = (x%(int)Math.pow(10,j))/10;
            return checkXISPalindrome(x);
        }
    }

    @Deprecated
    public boolean isPalindrome0(int x) {
       return checkXISPalindrome(x);
    }

    public boolean isPalindrome(int x) {
        //说明x为120 10这样的类型
        if(x<0 || (x%10==0 & x!=0)){
            return false;
        }
        int reverseNumber = 0;
        //从末尾获取数字的一半,并反转，再与剩下的进行判断
        while(x>reverseNumber){
            reverseNumber = reverseNumber*10+x%10;
            x = x/10;
        }
        //如果x的长度为偶数,则反转的数字与剩下的进行比较
        //如果x的长度为奇数,例如x为32123，循环退出的时候x=32,reverseNumber = 321,此时/10即可
        return x == reverseNumber || x == reverseNumber/10;
    }

    public static void main(String[] args) {
        LeetCode9 leetCode9 = new LeetCode9();
        int x = 32123;
        boolean result = leetCode9.isPalindrome(x);
        System.out.println("result="+result);
    }
}

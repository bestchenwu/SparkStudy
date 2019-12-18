package leetCode.simple;

/**
 * https://leetcode-cn.com/problems/power-of-two/
 *
 * 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
 *
 * 示例 1:
 *
 * 输入: 1
 * 输出: true
 * 解释: 20 = 1
 * 示例 2:
 *
 * 输入: 16
 * 输出: true
 * 解释: 24 = 16
 * 示例 3:
 *
 * 输入: 218
 * 输出: false
 *
 */
public class LeetCode231 {

    /**
     * 10 与01 进行异或运算  10 ^ 01  = 11
     * 正好是2+1 = 3
     *
     * 100 与 01 进行异或运算  100 ^ 01 = 101
     * 正好是4+1 = 5
     *
     * 反之101 与 01进行异或运算  101 ^ 01 =  100
     *
     *
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {
        if(n==1){
            return true;
        }
        while(n>2){
            if(n%2!=0){
                return false;
            }else{
                n = n/2;
            }
        }
        return n ==2 ;
    }

    public static void main(String[] args) {
        LeetCode231 leetCode231 = new LeetCode231();
        boolean result = leetCode231.isPowerOfTwo(5);
        System.out.println(result);
    }
}

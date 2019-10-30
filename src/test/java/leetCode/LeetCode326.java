package leetCode;

/**
 * https://leetcode-cn.com/problems/power-of-three/
 *
 * 给定一个整数，写一个函数来判断它是否是 3 的幂次方。
 */
public class LeetCode326 {

    public boolean isPowerOfThree(int n) {
        if(n<3){
            return false;
        }
        while(n!=0&&n!=3){
            if(n%3!=0){
                return false;
            }else{
                n = n/3;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCode326 leetCode326 = new LeetCode326();
        //boolean powerOfThree = leetCode326.isPowerOfThree(3);
        System.out.println(3%3);
        //System.out.println(powerOfThree);
    }
}

package leetCode;


/**
 * https://leetcode-cn.com/problems/sqrtx/
 *
 * 求x的平方根
 */
public class LeetCode69 {

    public int mySqrt(int x) {
        if(x==0){
            return 0;
        }
        if(x==1){
            return 1;
        }
        long low = 0;
        long high = x/2;
        long middle = 0;
        while(low<high){
            middle = low +(high-low+1)/2;
            if(middle*middle>x){
                high = middle-1;
            }else{
                low = middle;
            }
        }
        if (middle > x / middle) return (int)middle - 1;
        return (int)middle;
    }

    public static void main(String[] args) {
        LeetCode69 leetCode69 = new LeetCode69();
        int result = leetCode69.mySqrt(8);
        System.out.println(result);
    }
}

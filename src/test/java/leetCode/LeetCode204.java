package leetCode;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/count-primes/
 *
 * 计算<n的质数的数量
 */
public class LeetCode204 {

    public boolean checkNIsPrime(int n){
        if(n==2){
            return true;
        }
        int half = n/2;
        for(int i = 2;i<=half;i++){
            if(n%i==0){
                return false;
            }
        }
        return true;
    }

    public int countPrimes(int n) {
        if(n<2){
            return 0;
        }
        int count = 0;
        for(int i=2;i<n;i++){
            if(checkNIsPrime(i)){
                count+=1;
            }
        }
        return count;
    }

    public int countPrimes1(int n) {
        boolean[] isPrim = new boolean[n];
        // 将数组都初始化为 true
        Arrays.fill(isPrim, true);

        for (int i = 2; i < n; i++)
            if (isPrim[i])
                // i 的倍数不可能是素数了
                for (int j = 2 * i; j < n; j += i)
                    isPrim[j] = false;

        int count = 0;
        for (int i = 2; i < n; i++)
            if (isPrim[i]) count++;

        return count;
    }

    public static void main(String[] args) {
        LeetCode204 leetCode204 = new LeetCode204();
        int result = leetCode204.countPrimes(499979);
        System.out.println(result);
    }

}

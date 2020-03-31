package leetCode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 338. 比特位计数
 *
 * https://leetcode-cn.com/problems/counting-bits/
 *
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 *
 * 示例 1:
 *
 * 输入: 2
 * 输出: [0,1,1]
 * 示例 2:
 *
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 *
 */
public class LeetCode338 {

    /**
     * 返回n的bit位为1的个数
     *
     * @param n
     * @return int
     * @author chenwu on 2020.3.31
     */
    private int getIntegerOneBitNum(int n){
        List<Integer> list = new ArrayList<>();
        if(n == 1){
            list.add(1);
        }else if(n==0){
            list.add(0);
        }else{
            while(n>1 && n/2!=0){
                list.add(n%2);
                n = n/2;
            }
            if(n==1){
                list.add(1);
            }
        }
        long count = list.stream().filter(i->i==1).count();
        return (int)count;
    }

    public int[] countBits0(int num) {
        int[] bits = new int[num+1];
        for(int i = 0;i<=num;i++){
            bits[i] = getIntegerOneBitNum(i);
        }
        return bits;
    }

    /**
     *  0 0
     *  1 1
     *  2 10
     *  3 11
     *  4 100
     *  5 101
     *  6 110
     *  7 111
     *  8 1000
     *  9 1001
     *  10 1010
     *  11 1011
     *  12 1100
     *  13 1101
     *  14 1110
     *  15 1111
     *  16 10000
     *
     * @param num
     * @return
     */
    public int[] countBits(int num) {

        int[] bits = new int[num+1];
        int index = 1;
        int base = 0;
        for(int i = 0;i<=num;i++){
            if(i ==0){
                bits[i] = 0;
            }else if(i==1){
                bits[i] = 1;
            }else if(i == (int)Math.pow(2,index)){
                base = (int)Math.pow(2,index++);
                bits[i] = 1;
            }else{
                bits[i] = 1+bits[i-base];
            }
        }
        return bits;
    }

    public static void main(String[] args) {
        LeetCode338 leetCode338 = new LeetCode338();
        //int num = 2;
        int num = 17;
        //int num = 1;
        int[] bits = leetCode338.countBits(num);
        System.out.println(Arrays.toString(bits));
    }
}

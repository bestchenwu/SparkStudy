package offer.simple;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/
 *
 * 入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
 *
 * 示例 1:
 *
 * 输入: n = 1
 * 输出: [1,2,3,4,5,6,7,8,9]
 *
 *
 * 说明：
 *
 * 用返回一个整数列表来代替打印
 * n 为正整数
 *
 * @author chenwu on 2023.5.1
 */
public class Offer17 {

    public int[] printNumbers(int n) {
        int length = (int)Math.pow(10,n);
        int[] res = new int[length-1];
        for(int i = 0;i<length-1;i++){
            res[i] = i+1;
        }
        return res;
    }

    public static void main(String[] args) {
        Offer17 offer17 = new Offer17();
        int n = 1;
        int[] res = offer17.printNumbers(n);
        System.out.println("res="+ Arrays.toString(res));
    }
}

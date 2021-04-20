package leetCode.simple;

import org.junit.Test;

//实现 int sqrt(int x) 函数。
//
// 计算并返回 x 的平方根，其中 x 是非负整数。
//
// 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
//
// 示例 1:
//
// 输入: 4
//输出: 2
//
//
// 示例 2:
//
// 输入: 8
//输出: 2
//说明: 8 的平方根是 2.82842...,
//     由于返回类型是整数，小数部分将被舍去。
public class LeetCode69_20210420 {

    public int mySqrt(int x) {
        if (x == 0 || x == 1) {
            return x;
        }
        long left = 1, right = x/2;
        long middle;
        long result = 0;
        while (left <= right) {
            middle = left+(right-left)/2;
            if (middle * middle == x) {
                return (int)middle;
            } else if (middle * middle > x) {
                right = middle-1;
            } else {
                left = middle+1;
                result = middle;
            }
        }
        return (int)result;
    }

    @Test
    public void testMySqrt() {
        int x = 2147395599;
        int sqrt = mySqrt(x);
        System.out.println("sqrt=" + sqrt);
    }
}

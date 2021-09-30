package nowCode.medium;

import java.util.Arrays;

//给定两个整数数组a和b，计算具有最小差绝对值的一对数值（每个数组中取一个值），并返回该对数值的差
//
//
//
// 示例：
//
//
//输入：{1, 3, 15, 11, 2}, {23, 127, 235, 19, 8}
//输出：3，即数值对(11, 8)
//
//
//
//
// 提示：
//
//
// 1 <= a.length, b.length <= 100000
// -2147483648 <= a[i], b[i] <= 2147483647
// 正确结果在区间 [0, 2147483647] 内
public class LeetCode1606 {

    public int smallestDifference(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        int i = 0,aLen = a.length;
        int j = 0,bLen = b.length;
        long smallest_difference = Long.MAX_VALUE;
        while(i<aLen && j<bLen){
            long current_difference = Math.abs((long)a[i]-(long)b[j]);
            if(a[i]<b[j]){
                i++;
            }else if(a[i]>b[j]){
                j++;
            }else{
                return 0;
            }
            smallest_difference = Math.min(smallest_difference,current_difference);
        }
        return (int)smallest_difference;
    }
}

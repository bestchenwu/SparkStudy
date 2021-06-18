package leetCode.medium;

//给你一份工作时间表 hours，上面记录着某一位员工每天的工作小时数。
//
// 我们认为当员工一天中的工作小时数大于 8 小时的时候，那么这一天就是「劳累的一天」。
//
// 所谓「表现良好的时间段」，意味在这段时间内，「劳累的天数」是严格 大于「不劳累的天数」。
//
// 请你返回「表现良好时间段」的最大长度。
//
//
//
// 示例 1：
//
// 输入：hours = [9,9,6,0,6,6,9]
//输出：3
//解释：最长的表现良好时间段是 [9,9,6]。

import org.junit.Test;

import java.util.*;

public class LeetCode1124 {

    public int longestWPI(int[] hours) {
        int sum = 0, res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < hours.length; i++) {
            sum += (hours[i] > 8 ? 1 : -1);
            if (sum > 0) {
                res = i + 1;
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
            if (map.containsKey(sum - 1)) {
                res = Math.max(res, i - map.get(sum - 1));
            }
        }
        return res;
    }

    @Test
    public void testLongestWPI() {
        int[] hours = new int[]{9, 9, 6, 0, 6, 6, 9};
        int result = longestWPI(hours);
        System.out.println("result=" + result);
    }
}

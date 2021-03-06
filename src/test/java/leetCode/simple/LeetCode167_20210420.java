package leetCode.simple;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
//给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
//
// 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
//
// 说明:
//
//
// 返回的下标值（index1 和 index2）不是从零开始的。
// 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
//
//
// 示例:
//
// 输入: numbers = [2, 7, 11, 15], target = 9
//输出: [1,2]
//解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
public class LeetCode167_20210420 {

    public int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            Integer supplementIndex = map.get(numbers[i]);
            if (supplementIndex != null) {
                result[0] = supplementIndex + 1;
                result[1] = i + 1;
                return result;
            } else {
                int supplement = target - numbers[i];
                map.put(supplement, i);
            }
        }
        return result;
    }

    @Test
    public void testTwoSum(){
        int[] numbers = new int[]{2, 7, 11, 15};
        int target = 9;
        int[] result = twoSum(numbers, target);
        System.out.println(Arrays.toString(result));
    }
}

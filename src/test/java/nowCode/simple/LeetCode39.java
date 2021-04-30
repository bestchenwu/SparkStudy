package nowCode.simple;

import org.junit.Test;

//数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
////
////
////
//// 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
////
////
////
//// 示例 1:
////
//// 输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
////输出: 2
////
////
////
//// 限制：
////
//// 1 <= 数组长度 <= 50000
////
////
////
//// 注意：本题与主站 169 题相同：https://leetcode-cn.com/problems/majority-element/
public class LeetCode39 {

    //假设x(众数) 等于当前票nums[0]
    //若后面的数与当前数相等,则count+=1
    //否则count-=1;
    //若count = 0,则x = 当前新的num
    public int majorityElement(int[] nums) {
        int count = 0, x = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                x = nums[i];
                count+=1;
            }
            if (nums[i] == x) {
                count += 1;
            } else {
                count -= 1;
            }
        }
        return x;
    }

    @Test
    public void testMajorityElement() {
        int[] nums = new int[]{3, 3, 4};
        int result = majorityElement(nums);
        System.out.println("result=" + result);
    }
}

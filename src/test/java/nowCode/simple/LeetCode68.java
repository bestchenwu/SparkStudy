package nowCode.simple;

import org.junit.Test;

//给定一个排序的整数数组 nums 和一个整数目标值 target ，请在数组中找到 target ，并返回其下标。如果目标值不存在于数组中，返回它将会被按顺
//序插入的位置。
//
// 请必须使用时间复杂度为 O(log n) 的算法。
//
//
//
// 示例 1:
//
//
//输入: nums = [1,3,5,6], target = 5
//输出: 2
//
//
// 示例 2:
//
//
//输入: nums = [1,3,5,6], target = 2
//输出: 1
//
//
// 示例 3:
//
//
//输入: nums = [1,3,5,6], target = 7
//输出: 4
//
//
// 示例 4:
//
//
//输入: nums = [1,3,5,6], target = 0
//输出: 0
//
//
// 示例 5:
//
//
//输入: nums = [1], target = 0
//输出: 0
//
//
//
//
// 提示:
//
//
// 1 <= nums.length <= 10⁴
// -10⁴ <= nums[i] <= 10⁴
// nums 为无重复元素的升序排列数组
// -10⁴ <= target <= 10⁴
public class LeetCode68 {

    public int searchInsert(int[] nums, int target) {
        int len = nums.length;
        int start = 0, end = len - 1;
        int middle = start + (end - start) / 2;
        //[1 3 5 6] 2
        //start = 0 end = 3 middle = 1
        //start = 0  end = 0
        while (start <= end) {
            if (nums[middle] < target) {
                start = middle + 1;
            } else if(nums[middle]>target){
                end = middle - 1;
            } else{
                return middle;
            }
            middle = start + (end - start) / 2;
        }
        return start;
    }

    @Test
    public void testSearchInsert(){
        int[] nums = new int[]{1,3,5,6};
        int target = 4;
        int searchInsert = searchInsert(nums, target);
        System.out.println("searchInsert="+searchInsert);
    }
}

package leetCode.medium;

/**
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
 * <p>
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * <p>
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * <p>
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * <p>
 * 你可以假设数组中不存在重复的元素。
 * <p>
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * <p>
 * 示例 2:
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 */
public class LeetCode33 {

    private int binaraySearch(int[] nums, int start, int end, int target) {
        if (start > end) {
            return -1;
        } else if (start == end) {
            return nums[start] == target ? start : -1;
        } else if(nums[start]>target || nums[end]<target){
            return -1;
        }
        int half = 0;
        while (end > start) {
            if(end-start==1){
                return nums[start]==target?start:(nums[end]==target?end:-1);
            }
            half = start + (end - start) / 2;
            if (target == nums[half]) {
                return half;
            } else if (target > nums[half]) {
                start = half;
            } else {
                end = half;
            }
        }
        return -1;
    }

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        //找出旋转数组的分折点
        int index = 0;
        int end = nums.length - 1;
        while (index + 1 <= end && nums[index + 1] > nums[index]) {
            index += 1;
        }
        if(index==end){
            return binaraySearch(nums,0,index,target);
        }else if (nums[index + 1] <= target && nums[end] >= target) {
            //target位于第二个区间
            return binaraySearch(nums, index + 1, end, target);
        } else if (nums[0] <= target && nums[index] >= target) {
            //target位于第一个区间
            return binaraySearch(nums, 0, index, target);
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        LeetCode33 leetCode33 = new LeetCode33();
        //int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2};
        int[] nums = new int[]{7,0,1,2,4,5,6};
        int index = leetCode33.search(nums, 0);
        System.out.println(index);
    }
}

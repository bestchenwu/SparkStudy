package leetCode.medium;

/**
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 *
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 *
 * 示例 2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 *
 * 说明:
 *
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 *
 */
public class LeetCode215 {

    private void findLargeOnes(int[] nums,int end){
        int tmp;
        for(int i=0;i<end;i++){
            if(nums[i]>nums[end]){
                tmp = nums[end];
                nums[end] = nums[i];
                nums[i]=tmp;
            }
        }
    }

    public int findKthLargest(int[] nums, int k) {
        if(nums.length==1){
            return nums[0];
        }
        int end = nums.length-1;
        for(int i=1;i<=k;i++){
            findLargeOnes(nums,end);
            end = end-1;
        }
        return nums[end+1];
    }

    public static void main(String[] args) {
        LeetCode215 leetCode215 = new LeetCode215();
        int[] nums = new int[]{3,2,1,5,6,4};
        int result = leetCode215.findKthLargest(nums,2);
        System.out.println(result);
    }
}

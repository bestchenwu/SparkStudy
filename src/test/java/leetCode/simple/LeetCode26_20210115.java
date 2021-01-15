package leetCode.simple;

import java.util.Arrays;

//给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
//
// 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
//
//
//
// 示例 1:
//
// 给定数组 nums = [1,1,2],
//
//函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
//
//你不需要考虑数组中超出新长度后面的元素。
//
// 示例 2:
//
// 给定 nums = [0,0,1,1,1,2,2,3,3,4],
//
//函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
//
//你不需要考虑数组中超出新长度后面的元素。
public class LeetCode26_20210115 {

    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int p = 0 ,q = 1;
        while(q<nums.length){
            if(nums[p]!=nums[q]){
                nums[p+1] = nums[q];
                p++;
            }
            q=q+1;
        }
        return p+1;
    }

    public static void main(String[] args) {
        LeetCode26_20210115 leetCode26_20210115 = new LeetCode26_20210115();
        int[] nums = new int[]{0,0,1,1,1,2,2,3,3,4};
        int length = leetCode26_20210115.removeDuplicates(nums);
        System.out.println("length:"+length);
        System.out.println(Arrays.toString(nums));
    }
}

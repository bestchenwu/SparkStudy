package leetCode.medium;

import java.util.Arrays;

//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
//
// 示例:
//
// 输入: [0,1,0,3,12]
//输出: [1,3,12,0,0]
//
// 说明:
//
//
// 必须在原数组上操作，不能拷贝额外的数组。
// 尽量减少操作次数
public class LeetCode283 {

    public void moveZeroes(int[] nums) {
        if(nums == null || nums.length == 0){
            return;
        }
        int p = 0,q = 0;
        int length = nums.length;
        while(q<length){
            if(nums[q]==0){
                q++;
            }else{
                if(nums[p]==0){
                    swap(nums,p,q);
                }
                p++;
                q++;
            }
        }
    }

    private void swap(int[] nums,int p,int q){
        int tmp = nums[p];
        nums[p] = nums[q];
        nums[q] = tmp;
    }

    public static void main(String[] args) {
        //int[] nums = new int[]{2,1};
        int[] nums = new int[]{0,1,0,3,12};
        LeetCode283 leetCode283 = new LeetCode283();
        leetCode283.moveZeroes(nums);
        System.out.println("nums="+ Arrays.toString(nums));
    }
}

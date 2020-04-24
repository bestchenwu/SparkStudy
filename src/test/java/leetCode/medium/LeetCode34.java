package leetCode.medium;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 *
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 如果数组中不存在目标值，返回 [-1, -1]。
 *
 * 示例 1:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * 示例 2:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 */
public class LeetCode34 {

//    private int findIndex(int[] nums, int target){
//        int startIndex = 0;
//        int endIndex = nums.length-1;
//        int halfIndex = 0;
//        while(endIndex>=startIndex){
//            halfIndex = startIndex+(endIndex-startIndex)/2;
//            if(nums[halfIndex] == target){
//                return halfIndex;
//            }
//            if(halfIndex == startIndex){
//                if(nums[startIndex]==target){
//                    return startIndex;
//                }
//                if(nums[endIndex]==target){
//                    return endIndex;
//                }
//                return -1;
//            }
//            if(nums[halfIndex]>target){
//                endIndex = halfIndex;
//            }else{
//                startIndex = halfIndex;
//            }
//        }
//        return -1;
//    }
//
//    public int[] searchRange(int[] nums, int target) {
//        //先二分查找target在nums的位置
//        if(nums==null || nums.length==0){
//            return new int[]{-1,-1};
//        }
//        int index = findIndex(nums,target);
//        if(index==-1){
//            return new int[]{-1,-1};
//        }
//        int i = index;
//        int j = index;
//        for(;i<nums.length;i++){
//            if(nums[i] != target){
//                break;
//            }
//        }
//        for(;j>=0;j--){
//            if(nums[j] != target){
//                break;
//            }
//        }
//        return new int[]{j+1,i-1};
//    }


    private int findIndex(int[] nums,int target){
        int left = 0;
        int right = nums.length-1;
        int middle = (left+right)/2;
        while(right>=left){
            if(left==right){
                return nums[left]==target?left:-1;
            }
            if(nums[middle]==target){
                return middle;
            }else if(nums[middle] < target){
                left = middle;
            }else{
                right = middle;
            }
            middle = (left+right)/2;
        }
        return -1;
    }

    public int[] searchRange(int[] nums, int target) {
        if(nums==null || nums.length==0){
            return new int[]{-1,-1};
        }
        int index = findIndex(nums,target);
        if(index == -1){
            return new int[]{-1,-1};
        }
        int leftIndex = index;
        while(leftIndex-1>=0 && nums[leftIndex-1]==nums[index]){
            leftIndex-=1;
        }
        int rightIndex = index;
        while(rightIndex+1<nums.length && nums[rightIndex+1]==nums[index]){
            rightIndex+=1;
        }
        return new int[]{leftIndex,rightIndex};
    }

    public static void main(String[] args) {
        LeetCode34 leetCode34 = new LeetCode34();
//        int[] nums = new int[]{2,7,7,8,8,8};
//        int target = 7;
        int[] nums = new int[]{5,7,7,8,8,10};
        int target = 6;
        int[] index = leetCode34.searchRange(nums, target);
        System.out.println(Arrays.toString(index));
    }
}

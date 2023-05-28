package offer.simple;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/
 *
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数在数组的前半部分，所有偶数在数组的后半部分。
 *
 *
 *
 * 示例：
 *
 * 输入：nums = [1,2,3,4]
 * 输出：[1,3,2,4]
 * 注：[3,1,2,4] 也是正确的答案之一。
 *
 *
 * 提示：
 *
 * 0 <= nums.length <= 50000
 * 0 <= nums[i] <= 10000
 *
 * @author chenwu on 2023.5.1
 */
public class Offer21 {

    public int[] exchange(int[] nums) {
        int left = 0,right = nums.length-1,middle = (left+right)/2+1;
        while(left<=right){
            if(nums[left]%2==0){
                while(right>left && nums[right]%2==0){
                    right--;
                }
                if(right<0){
                    break;
                }
                //将left与middle对应的进行交换
                swap(left,right,nums);
                right-=1;
            }
            left+=1;
        }
        return nums;
    }

    private void swap(int left,int right,int[] nums){
        int tmp ;
        tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }

    public static void main(String[] args) {
        Offer21 offer21 = new Offer21();
        //int[] nums = new int[]{1,2,3,4,5};
        //int[] nums = new int[]{2,16,3,5,13,1,16,1,12,18,11,8,11,11,5,1};
        int[] nums = new int[]{1,11,14};
        int[] res = offer21.exchange(nums);
        System.out.println("res="+ Arrays.toString(res));
    }
}

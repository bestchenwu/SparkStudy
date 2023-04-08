package offer.simple;

/**
 * https://leetcode.cn/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/
 *
 * 找出数组中重复的数字。
 *
 *
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 *
 * 示例 1：
 *
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 */
public class Offer03 {

    public int findRepeatNumber(int[] nums) {
        int length = nums.length;
        int[] table = new int[length];
        for(int i = 0;i<length;i++){
            table[nums[i]-0]+=1;
            if(table[nums[i]-0]>1){
                return nums[i];
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Offer03 offer03 = new Offer03();
        int[] nums = new int[]{2, 3, 1, 0, 2, 5, 3};
        int res = offer03.findRepeatNumber(nums);
        System.out.println("res="+res);
    }
}

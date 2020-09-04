package leetCode.medium;

/**
 * https://leetcode-cn.com/problems/find-the-duplicate-number/
 *
 * 287. 寻找重复数
 *
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 *
 * 示例 1:
 *
 * 输入: [1,3,4,2,2]
 * 输出: 2
 * 示例 2:
 *
 * 输入: [3,1,3,4,2]
 * 输出: 3
 *
 * @author chenwu on 2020.9.4
 */
public class LeetCode287_20200904 {

    /**
     * 抽屉原理，从1-n中抽取小于等于中间值的数，正常情况下<=中间值的个数应该也等于中间值。
     * 例如1 2 3 4 5 中间值为3，那么1-3的数字个数也是3
     * 如果1-3的数字个数比3多(1 2 2 3 4 5)，说明重复数字区间落在1-3之内 right = middle
     *   1 2 2 3 middle = 2
     *   left = 1 right = 2
     *   middle = 1
     *   left = 2
     * 反之(1 2 3 4 4 5) 1-3的数字个数<=3,说明重复数字比3大，left = middle+1
     * 重复查找，直到left = right
     *
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int left = 0;
        int right = nums.length-1;
        int count = 0;
        int middle = (left+right)/2;
        while(left<right){
            for(int num :nums){
                if(num<=middle){
                    count+=1;
                }
            }
            if(count<=middle){
                left = middle+1;
            }else{
                right = middle;
            }
            count = 0;
            middle = (left+right)/2;
        }
        return left;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,2,3,4,5};
        LeetCode287_20200904 leetCode287_20200904 = new LeetCode287_20200904();
        int duplicate = leetCode287_20200904.findDuplicate(nums);
        System.out.println(duplicate);
    }
}

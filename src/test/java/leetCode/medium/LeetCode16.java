package leetCode.medium;

/**
 * https://leetcode-cn.com/problems/3sum-closest/
 * <p>
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * <p>
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 * <p>
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 */
public class LeetCode16 {

    private void sort(int[] nums) {
        int temp;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[i]) {
                    //进行交换排序
                    temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
    }

    //先对nums排序
    //每次遍历nums，获得一个nums[i]
    //然后从start=i+1到end=nums.length-1进行两端收缩累加,如果加的值比target大，end-1;反之则start+1,直到start=end或者sum=target
    public int threeSumClosest(int[] nums, int target) {
        if (nums.length < 3) {
            throw new RuntimeException("nums.length must more than 3");
        }
        if (nums.length == 3) {
            return nums[0] + nums[1] + nums[2];
        }
        sort(nums);
        int numsI;
        int start;
        int end;
        int sum;
        //结果sum值和gap的差距
        int sumGap = Integer.MAX_VALUE;
        //当前sum值与target的差距
        int currentSumGap;
        //最终结果
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            numsI = nums[i];
            if (i == nums.length - 2) {
                break;
            }
            start = i + 1;
            end = nums.length - 1;
            while (start < end) {
                sum = numsI + nums[start] + nums[end];
                //循环完成,计算当前的sum与target的差距
                currentSumGap = Math.abs(sum - target);
                if (currentSumGap < sumGap) {
                    result = sum;
                    sumGap = currentSumGap;
                }
                if (sum > target) {
                    end = end - 1;
                } else if (sum < target) {
                    start = start + 1;
                } else {
                    return sum;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        LeetCode16 leetCode16 = new LeetCode16();
        //int[] nums = new int[]{-1, 2, 1, -4};
        int[] nums = new int[]{-1,2,-8,-5,3,4,5,-3};
        //int sumClosest = leetCode16.threeSumClosest(nums, 1);
        int sumClosest = leetCode16.threeSumClosest(nums, 2);
        System.out.println(sumClosest);
    }
}

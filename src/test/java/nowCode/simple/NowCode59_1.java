package nowCode.simple;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;
import java.util.stream.StreamSupport;

//给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
//
// 示例:
//
// 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
//输出: [3,3,5,5,6,7]
//解释:
//
//  滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7
// 提示：
//
// 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。
//
// 注意：本题与主站 239 题相同：https://leetcode-cn.com/problems/sliding-window-maximum/
//
//
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) {
            return new int[0];
        }
        //维护一个单调递增的栈,栈顶保存最大数
        LinkedList<Integer> list = new LinkedList<>();
        int length = nums.length;
        int[] result = new int[length - k + 1];
        int index = 0;
        for(int i = 0;i<length;i++){
            while(!list.isEmpty() && nums[list.getLast()]<=nums[i]){
                list.removeLast();
            }
            list.addLast(i);
            if(list.peekFirst()==i-k){
                list.pollFirst();
            }
            if(i>=(k-1)){
                result[index++] = nums[list.peekFirst()];
            }
        }
        return result;
    }
}

public class NowCode59_1 {

    public static void main(String[] args) {
//        int[] nums = new int[]{7, 2, 4};
//        int k = 2;
        int[] nums = new int[]{1,3,-1,-3,5,3,6,7};
        int k = 3;
        Solution solution = new Solution();
        int[] result = solution.maxSlidingWindow(nums, k);
        System.out.println(Arrays.toString(result));
    }
}

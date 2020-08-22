package leetCode.medium;

import java.util.PriorityQueue;

/**
 * 215. 数组中的第K个最大元素
 *
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 *
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 *
 * @author chenwu on 2020.3.22
 */
public class LeetCode215_20200822 {

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int i : nums){
            queue.add(i);
            if(queue.size()>k){
                queue.poll();
            }
        }
        return queue.peek();
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,2,3,1,2,4,5,5,6};
        LeetCode215_20200822 leetCode215_20200822 = new LeetCode215_20200822();
        int kthLargest = leetCode215_20200822.findKthLargest(nums, 4);
        System.out.println("kthLargest="+kthLargest);
    }
}

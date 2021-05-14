package leetCode.medium;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

import java.util.Map;
import java.util.Stack;

//给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第
//一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
//
// 示例 1:
//
//
//输入: [1,2,1]
//输出: [2,-1,2]
//解释: 第一个 1 的下一个更大的数是 2；
//数字 2 找不到下一个更大的数；
//第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
public class LeetCode503 {

    public int[] nextGreaterElements2(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int length = nums.length;
        int[] result = new int[nums.length];
        Arrays.fill(result,-1);
        for(int i = 0;i<length*2;i++){
            int num = nums[i%length];
            while(!stack.isEmpty() && nums[stack.peek()]<num){
               result[stack.pop()]=num;
            }
            if(i<length){
                stack.push(i);
            }

        }
        return result;
    }

    public int[] nextGreaterElements(int[] nums) {
        //从左往右建立单调递增栈、单调递减栈
        //对于递增栈来说,被弹出的元素 和当前栈顶元素组成了关系
        //对于单调递减栈来说,如果元素能加进来，则元素和stack(0)组成映射关系,如果不能加进来,则弹出元素后和stack(0)组成关系
        //所以最终就是stack(0)
        Stack<Integer> stack1 = new Stack<>();
        Map<Integer, Integer> map1 = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            //stack1是单调递减栈
            while (!stack1.isEmpty() && nums[stack1.peek()] < nums[i]) {
                map1.put(stack1.pop(), i);
            }
            stack1.push(i);
        }
        //对于stack1里面的元素,在数组里从左到右寻找
        for (int j=0; j < nums.length && stack1.size()>1;) {
            if (nums[j] > nums[stack1.peek()]) {
                map1.put(stack1.pop(), j);
            } else {
                j++;
            }
        }
        int[] result = new int[nums.length];
        for (int j = 0; j < nums.length; j++) {
            Integer tmp = map1.get(j);
            if (tmp != null) {
                result[j] = nums[tmp];
            } else {
                result[j] = -1;
            }
        }
        return result;
    }

    @Test
    public void testNextGreaterElements() {
        int[] nums = new int[]{100, 1, 11, 1, 120, 111, 123, 1, -1, -100};
        int[] result = nextGreaterElements2(nums);
        System.out.println(Arrays.toString(result));
    }
}

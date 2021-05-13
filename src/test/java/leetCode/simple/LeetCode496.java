package leetCode.simple;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/next-greater-element-i/
 *
 * 给定两个没有重复元素的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。找到 nums1 中每个元素在 nums2 中的下一个比其大的值。
 *
 * nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出-1。
 *
 * 示例 1:
 *
 * 输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
 * 输出: [-1,3,-1]
 * 解释:
 *     对于num1中的数字4，你无法在第二个数组中找到下一个更大的数字，因此输出 -1。
 *     对于num1中的数字1，第二个数组中数字1右边的下一个较大数字是 3。
 *     对于num1中的数字2，第二个数组中没有下一个更大的数字，因此输出 -1。
 * 示例 2:
 *
 * 输入: nums1 = [2,4], nums2 = [1,2,3,4].
 * 输出: [3,-1]
 * 解释:
 *     对于num1中的数字2，第二个数组中的下一个较大数字是3。
 *     对于num1中的数字4，第二个数组中没有下一个更大的数字，因此输出 -1。
 * 注意:
 *
 * nums1和nums2中所有元素是唯一的。
 * nums1和nums2 的数组大小都不超过1000。
 *
 */
public class LeetCode496 {

    private int findMoreNumber(int currentNumber,int[] nums2){
        for(int j=0;j<nums2.length;j++){
            if(currentNumber==nums2[j]){
                if(j==nums2.length-1){
                    //说明到达数组末尾
                    return -1;
                }else{
                    for(int k=j+1;k<nums2.length;k++){
                        if(nums2[k]>currentNumber){
                            return nums2[k];
                        }
                    }
                    return -1;
                }
            }
        }
        return -1;
    }

    public int[] nextGreaterElement1(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];
        for(int i=0;i<nums1.length;i++){
            result[i] = findMoreNumber(nums1[i],nums2);
        }

        return result;
    }

    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            int j = 0;
            while (j < nums2.length && nums2[j] != nums1[i]) {
                j++;
            }
            if (j == nums2.length - 1) {
                result[i] = -1;
            } else {
                int k;
                for (k = j + 1; k < nums2.length; k++) {
                    if (nums2[k] > nums1[i]) {
                        result[i] = nums2[k];
                        break;
                    }
                }
                if (k == nums2.length) {
                    result[i] = -1;
                }
            }
        }
        return result;
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        //先对nums2构建一个map,map的key为nums2的元素值,value为比它大的下一个元素值
        //同时将当前数存入到堆栈中
        Map<Integer,Integer> hashMap = new HashMap<>();
        Stack<Integer> unMatchedStack = new Stack<Integer>();
        for(int num:nums2){
            if(!unMatchedStack.empty()){
                Integer peekNum = unMatchedStack.peek();
                while(num>peekNum){
                    hashMap.put(peekNum,num);
                    unMatchedStack.pop();
                    if(unMatchedStack.isEmpty()){
                        break;
                    }
                    peekNum = unMatchedStack.peek();
                }
            }
            unMatchedStack.push(num);
        }
        while(!unMatchedStack.empty()){
            Integer num = unMatchedStack.pop();
            hashMap.put(num,-1);
        }
        int[] result = new int[nums1.length];
        for(int i=0;i<nums1.length;i++){
            result[i] = hashMap.get(nums1[i]);
        }
        return result;
    }

    public int[] nextGreaterElement3(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<Integer>();
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i<nums2.length;i++){
            while(!stack.isEmpty() && stack.peek()<nums2[i]){
                map.put(stack.pop(),nums2[i]);
            }
            stack.push(nums2[i]);
        }
        int[] result = new int[nums1.length];
        for(int i = 0;i<nums1.length;i++){
            result[i] = map.getOrDefault(nums1[i],-1);
        }
        return result;
    }

    public static void main(String[] args) {
        LeetCode496 leetCode496 = new LeetCode496();
//        int[] nums1 = new int[]{2,4};
//        int[] nums2 = new int[]{1,2,3,4};
        int[] nums1 = new int[]{4,1,2};
        int[] nums2 = new int[]{1,3,4,2};
        int[] result = leetCode496.nextGreaterElement3(nums1,nums2);
        System.out.println(Arrays.toString(result));
    }
}

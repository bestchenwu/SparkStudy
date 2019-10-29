package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/
 *
 * 找出两个数组里的交集
 */
public class LeetCode350 {

    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int p0 = 0;
        int p1 = 0;
        List<Integer> list = new ArrayList<Integer>();
        while(p0<nums1.length&&p1<nums2.length){
            if(nums1[p0]<nums2[p1]){
                p0++;
            }else if(nums1[p0]>nums2[p1]){
                p1++;
            }else{
                list.add(nums1[p0]);
                p0++;
                p1++;
            }
        }
        int[] result = new int[list.size()];
        for(int i=0;i<list.size();i++){
            result[i]=list.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        LeetCode350 leetCode350 = new LeetCode350();
        int[] nums1 = new int[]{1,2,2,1};
        int[] nums2 = new int[]{2,2};
        int[] result = leetCode350.intersect(nums1,nums2);
        System.out.println(Arrays.toString(result));
    }
}

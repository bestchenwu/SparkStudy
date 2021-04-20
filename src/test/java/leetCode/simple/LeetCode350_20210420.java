package leetCode.simple;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//给定两个数组，编写一个函数来计算它们的交集。
//
//
//
// 示例 1：
//
// 输入：nums1 = [1,2,2,1], nums2 = [2,2]
//输出：[2,2]
//
//
// 示例 2:
//
// 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
//输出：[4,9]
//
//
//
// 说明：
//
//
// 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
// 我们可以不考虑输出结果的顺序。
//
//
// 进阶：
//
//
// 如果给定的数组已经排好序呢？你将如何优化你的算法？
// 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
// 如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
//
// Related Topics 排序 哈希表 双指针 二分查找
public class LeetCode350_20210420 {

    public int[] intersect(int[] nums1, int[] nums2) {
        int[] small = nums1.length < nums2.length ? nums1 : nums2;
        int[] big = nums1.length < nums2.length ? nums2 : nums1;
        Arrays.sort(big);
        Arrays.sort(small);
        List<Integer> numList = new ArrayList<>();
        int i = 0 , j = 0;
        for(;i<small.length;i++){
            j = i;
            while(j+1<small.length && small[j+1] == small[j]){
                j+=1;
            }
            int smallRepliatedNum = j-i+1;
            int bigRepliateNum = checkNumIsExisted(big,small[i]);
            if(bigRepliateNum>0){
                int num = Math.min(smallRepliatedNum,bigRepliateNum);
                for(int k = 1;k<=num;k++){
                    numList.add(small[i]);
                }
            }
            i = j;
        }
        int[] result = new int[numList.size()];
        int index = 0;
        for (Integer num : numList) {
            result[index++] = num;
        }
        return result;
    }

    private int checkNumIsExisted(int[] big, int target) {
        int left = 0, right = big.length - 1;
        int middle;
        while (left <= right) {
            middle = left + (right - left) / 2;
            if (big[middle] == target) {
                left = right = middle;
                while(left-1>=0 && big[left-1]==big[middle]){
                    left-=1;
                }
                while(right+1<=big.length-1 && big[right+1] == big[middle]){
                    right+=1;
                }
                return right-left+1;
            } else if (big[middle] < target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return 0;
    }

    @Test
    public void testIntersect(){
        int[] nums1 = new int[]{4,9,5};
        int[] nums2 = new int[]{9,4,9,4,8};
        int[] intersect = intersect(nums1, nums2);
        System.out.println("intersect="+Arrays.toString(intersect));
    }
}

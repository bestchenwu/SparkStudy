package leetCode.simple;

public class leetcode88_20210121 {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m-1;
        int j = n-1;
        int insertIndex =  m+n-1;
        while(i>=0&&j>=0){
            nums1[insertIndex--] = nums1[i]>nums2[j]?nums1[i--]:nums2[j--];
        }
        System.arraycopy(nums2,0,nums1,0,j+1);
    }
}

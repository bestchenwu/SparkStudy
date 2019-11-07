package leetCode.simple;

import java.util.Arrays;

public class LeetCode88 {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m-1;
        int j = n-1;
        int insertLength = m+n-1;
        while(i>=0&&j>=0){
            nums1[insertLength--]=(nums1[i]>nums2[j])?nums1[i--]:nums2[j--];
        }
        System.arraycopy(nums2,0,nums1,0,j+1);
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] nums2 = new int[]{2, 5, 6};
        int n = 3;
        LeetCode88 leetCode88 = new LeetCode88();
        leetCode88.merge(nums1,m,nums2,n);
        System.out.println(Arrays.toString(nums1));
    }
}

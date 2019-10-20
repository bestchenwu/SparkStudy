package leetCode;

/**
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。<br/>
 * 你不需要考虑数组中超出新长度后面的元素。<br/>
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
 *
 */
public class LeetCode26 {

    public int removeDuplicates(int[] nums) {
        if(nums==null||nums.length==0){
            return 0;
        }
        if(nums.length==1){
            return 1;
        }
        int length = 1;
        int j = 0;
        for(int i=0;i<nums.length;i++){
            for(j=i;j<nums.length;j++){
                if(nums[j]!=nums[i]) {
                    break;
                }
            }
            nums[length++]=nums[j];
            i = j;
        }
        return length;
    }

    public static void main(String[] args){
        LeetCode26 leetCode26 = new LeetCode26();
        int[] nums = new int[]{1,2,3};
        int length = leetCode26.removeDuplicates(nums);
        System.out.println(length);
    }
}

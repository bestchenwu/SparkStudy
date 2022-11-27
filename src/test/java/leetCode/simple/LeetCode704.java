package leetCode.simple;

public class LeetCode704 {

    public static int search(int[] nums, int target) {
        int left = 0,right = nums.length-1;
        int middle;
        while(left<=right){
            middle = left+(right-left)/2;
            if(nums[middle]==target){
                return middle;
            }else if(nums[middle]>target){
                right = middle-1;
            }else{
                left = middle+1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        //int[] nums = new int[]{-1,0,3,5,9,12};
        int[] nums = new int[]{9};
        int target = 9;
        int res = search(nums,target);
        System.out.println("res="+res);
    }
}

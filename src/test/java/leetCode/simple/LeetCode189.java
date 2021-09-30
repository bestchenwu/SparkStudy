package leetCode.simple;

/**
 * https://leetcode-cn.com/problems/rotate-array/
 *
 * 右移若干位
 */
public class LeetCode189 {

    public void rotate(int[] nums, int k) {
        if(nums==null||nums.length==0){
            return;
        }
        int temp;
        int length = nums.length-1;
        int j = length;
        for(int i=1;i<=k;i++){
            temp = nums[length];
            while(j>=1){
                nums[j]=nums[j-1];
                j--;
            }
            nums[0]=temp;
            j = length;
        }
    }

    public void rotate1(int[] nums, int k) {
        int length = nums.length;
        k = k%length;
        int[] copy = new int[length];
        for(int i = 0;i<length;i++){
            copy[i] = nums[i];
        }
        for(int i = 0;i<k;i++){
            nums[i]=copy[length-(k-i)];
        }
        for(int j = k;j<length;j++){
            nums[j] = copy[j-k];
        }
    }

    public void rotate2(int[] nums, int k) {
        int length = nums.length;
        k = k%length;
        //利用数组翻转
        reverse(nums,0,length-1);
        reverse(nums,0,k-1);
        reverse(nums,k,length-1);
    }

    private void reverse(int[] nums,int start,int end){
        int tmp = 0;
        while(start<end){
            tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end --;
        }
    }

    public static void main(String[] args) {
        LeetCode189 leetCode189 = new LeetCode189();
        int[] nums = new int[]{1,2,3,4,5,6,7};
        leetCode189.rotate2(nums,3);
        System.out.println(nums);
    }
}

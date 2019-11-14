package leetCode.simple;

import java.util.Arrays;

/**
 * 将数组向右移动k位
 *
 * 例如:[1,2,3,4]向右边移动1位,则变成[4,1,2,3]
 *      向右移动2位,则变成[3,4,1,2]
 */
public class ArrayMoveTest {

    private void moveOnes(int[] nums){
        int length = nums.length;
        int temp = nums[length-1];
        for(int j=length-1;j>=1;j--){
            nums[j]=nums[j-1];
        }
        nums[0]=temp;
    }

    public int[] moveArray(int[] nums,int k){
        if(nums==null||nums.length<=0||k<=0){
            return nums;
        }
        int length = nums.length;
        k = k%length;
        for(int i = 1;i<=k;i++){
            moveOnes(nums);
        }
        return nums;
    }

    public static void main(String[] args) {
        ArrayMoveTest arrayMoveTest = new ArrayMoveTest();
        int[] nums = new int[]{1,2,3,4};
        int[] array = arrayMoveTest.moveArray(nums, 2);
        System.out.println(Arrays.toString(array));
    }

}

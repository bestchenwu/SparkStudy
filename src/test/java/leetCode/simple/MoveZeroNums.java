package leetCode.simple;

import java.util.Arrays;

public class MoveZeroNums {

    public void moveZeroes(int[] nums) {
        if(nums==null||nums.length==0){
            return;
        }
        int temp;
        int j = 0;
        //记录第一个不为0的指针
        //记录后续遍历的指针,如果发现为0,则与第一个指针的下一位进行交换
        for(int i=0;i<nums.length;){
            if(nums[i]==0){
                for(;j<nums.length;j++){
                    if(nums[j]!=0){
                        //将firstNonIndex与i交换
                        temp = nums[i];
                        nums[i] = nums[j];
                        nums[j] = temp;
                        //i = j;
                        break;
                    }
                }
                if(j==nums.length&&nums[i]==0){
                    break;
                }
            }
            i+=1;
            j+=1;
        }
    }

    public static void main(String[] args){
        MoveZeroNums test = new MoveZeroNums();
        int[] nums = new int[]{0,1,0,3,1};
        test.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }
}

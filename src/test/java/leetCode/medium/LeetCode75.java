package leetCode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * <p>
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * <p>
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 * <p>
 * 示例:
 * <p>
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 * 进阶：
 * <p>
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。
 * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 */
public class LeetCode75 {

    public void sortColors0(int[] nums) {
        List<Integer> num0List = new ArrayList();
        List<Integer> num1List = new ArrayList();
        List<Integer> num2List = new ArrayList();
        for (int i = 0; i < nums.length; i++) {
            switch (nums[i]) {
                case 0:
                    num0List.add(0);
                    break;
                case 1:
                    num1List.add(1);
                    break;
                case 2:
                    num2List.add(2);
                    break;
            }
        }
        int index = 0;
        for (Integer num0 : num0List) {
            nums[index++] = num0;
        }
        for (Integer num1 : num1List) {
            nums[index++] = num1;
        }
        for (Integer num2 : num2List) {
            nums[index++] = num2;
        }
    }

    private void swap(int[] nums,int p0,int p1){
        int tmp = nums[p0];
        nums[p0] = nums[p1];
        nums[p1] = tmp;
    }

    public void sortColors(int[] nums) {
        int p0  =0 ,cur = 0, p2 = nums.length - 1;
        while(cur<=p2){
            if(nums[cur]==0){
                //将cur与p0进行交换
                swap(nums,p0,cur);
                p0++;
                cur++;
            }else if(nums[cur]==2){
                swap(nums,p2,cur);
                p2--;
            }else{
                cur++;
            }

        }
    }

    public static void main(String[] args) {
        LeetCode75 leetCode75 = new LeetCode75();
        //int[] nums = new int[]{2, 0, 2, 1, 1, 0};
        int[] nums = new int[]{1,2,0};
        leetCode75.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}

package nowCode.medium;

import org.junit.Test;

import java.util.Arrays;

//给定一个整数数组，编写一个函数，找出索引m和n，只要将索引区间[m,n]的元素排好序，整个数组就是有序的。注意：n-m尽量最小，也就是说，找出符合条件的最短
//序列。函数返回值为[m,n]，若不存在这样的m和n（例如整个数组是有序的），请返回[-1,-1]。
// 示例：
// 输入： [1,2,4,7,10,11,7,12,6,7,16,18,19]
//输出： [3,9]
//
// 提示：
//
// 0 <= len(array) <= 1000000
public class NowCode1616 {

    public int[] subSort(int[] array) {

        if (array.length == 0) return new int[]{-1,-1};

        int start = 0;
        int max = array[0];

        int r = -1;
        for (int i = 0; i < array.length; i++) {

            if (array[i] >= max) {
                max = array[i];
            }else {
                r = i;
            }
        }

        int l = -1;
        int min = array[array.length - 1];
        for (int i =  array.length-1; i >= 0; i--) {

            if (array[i] <= min) {
                min = array[i];
            }else {
                l = i;
            }
        }

        return new int[]{l, r};

    }

    @Test
    public void testNowCode1616(){
        int[] nums = new int[]{1,2,4,7,10,11,7,12,6,7,16,18,19};
        int[] result = subSort(nums);
        System.out.println("result="+ Arrays.toString(result));
    }
}

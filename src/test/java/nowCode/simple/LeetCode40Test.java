package nowCode.simple;

import org.junit.Test;

import java.util.Arrays;

public class LeetCode40Test {

    @Test
    public void testGetLeastNumbers(){
        LeetCode40 leetCode40 = new LeetCode40();
        int[] arr = new int[]{0,0,0,2,0,5};
        int k = 2;
        int[] leastNumbers = leetCode40.getLeastNumbers(arr, k);
        System.out.println(Arrays.toString(leastNumbers));
    }
}

package nowCode.simple;

import org.junit.Test;

public class LeetCode1046Test {

    @Test
    public void testLastStoneWeight(){
        LeetCode1046 leetCode1046 = new LeetCode1046();
        int[] stones = new int[]{2,7,4,1,8,1};
        int lastStoneWeight = leetCode1046.lastStoneWeight(stones);
        System.out.println(lastStoneWeight);
    }
}

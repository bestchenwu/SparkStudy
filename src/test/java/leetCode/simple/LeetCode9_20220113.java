package leetCode.simple;

import org.junit.Assert;
import org.junit.Test;

public class LeetCode9_20220113 {

    public boolean isPalindrome(int x) {
        if(x<0){
            return false;
        }
        int sum = 0;
        while(x/10>0 && x>sum){
            sum = sum*10+x%10;
            x = x/10;
        }
        return x == sum || sum/10 == x;
    }

    @Test
    public void testIsPalindrome(){
        int x = 1221;
        boolean flag = isPalindrome(x);
        Assert.assertEquals(true,flag);
    }
}

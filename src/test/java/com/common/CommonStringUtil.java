package com.common;

import org.junit.Test;

public class CommonStringUtil {
    @Test
    public void testStr(){
        String s = "abc";
        String dp1 = s.substring(0,1);
        String dp2 = dp1;
        dp1+="dd";
        System.out.println(dp1);
        System.out.println(dp2);
    }
}

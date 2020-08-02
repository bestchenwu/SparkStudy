package com.redisStudy.appliance7;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link Funnel}的测试
 */
public class FunnelTest {

    private Map<String, Funnel> funnels = new HashMap<>();

    /**
     * 对于userId是否允许灌进quota的水进来
     *
     * @param userId
     * @param capacity
     * @param leakingRate
     * @param quota
     * @return boolean
     * @author chenwu on 2020.8.2
     */
    public boolean isAllowed(String userId, int capacity, float leakingRate, int quota) {
        Funnel funnel = funnels.get(userId);
        if (funnel == null) {
            funnel = new Funnel(capacity, leakingRate);
            funnels.put(userId, funnel);
        }
        funnel.makeSpace();
        boolean waterFlag = funnel.water(quota);
        return waterFlag;
    }

    public static void main(String[] args) {
        FunnelTest funnelTest = new FunnelTest();
        boolean isAllowed = funnelTest.isAllowed("raoshanshan",10000,0.5f,5000);
        System.out.println("isAllowed="+isAllowed);
        try{
            Thread.sleep(100);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        isAllowed = funnelTest.isAllowed("raoshanshan",10000,0.05f,8000);
        System.out.println("isAllowed="+isAllowed);
    }
}

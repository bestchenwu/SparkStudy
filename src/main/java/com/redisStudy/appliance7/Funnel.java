package com.redisStudy.appliance7;

/**
 * 简易java漏斗模型
 *
 * @author chenwu on 2020.8.2
 */
public class Funnel {

    private int capacity;//总容量
    private float leakingRate;//水流泄露速度
    private int leftQuota;//剩余容量
    private long lastLeakingTs;//上次统计泄露的记录时间

    public Funnel(int capacity, float leakingRate) {
        this.capacity = capacity;
        this.leakingRate = leakingRate;
        this.leftQuota = capacity;
        this.lastLeakingTs = System.currentTimeMillis();
    }

    public void makeSpace() {
        long nowTime = System.currentTimeMillis();
        //当前统计时间与上次
        int deltaQuota = (int) ((nowTime - lastLeakingTs) * leakingRate);
        lastLeakingTs = nowTime;
        if (deltaQuota < 0) {
            //当前统计的时间与上次统计的时间间隔太长
            leftQuota = capacity;
            return;
        }
        //最小腾出的空间至少为1
        if (deltaQuota < 1) {
            return;
        }
        leftQuota += deltaQuota;
        if (leftQuota > capacity) {
            leftQuota = capacity;
        }
    }

    /**
     * 是否允许灌进容量为quota的水进来
     *
     * @param quota
     * @return boolean
     * @author chenwu on 2020.8.2
     */
    public boolean water(int quota) {
        makeSpace();
        System.out.println("leftQuota:"+leftQuota);
        if (leftQuota >= quota) {
            leftQuota -= quota;
            return true;
        }
        return false;
    }
}

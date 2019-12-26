package com.zookeeperStudy.unit8_curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;

/**
 * 使用curator的入门例子
 *
 * @author chenwu on 2019.12.25
 */
public class CuratorSampleTest {

    public static void main(String[] args) {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient("127.0.0.1:2181", new RetryNTimes(3, 1000));
        curatorFramework.start();
        try{
            //同步模式
            curatorFramework.create().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath("/curator","success".getBytes());
            Thread.sleep(10*1000);
            //异步模式
            //curatorFramework.create().withMode(CreateMode.EPHEMERAL).inBackground().forPath("/curator","success".getBytes());
        }catch(Exception e){
            e.printStackTrace();
        }

        curatorFramework.close();
    }

}

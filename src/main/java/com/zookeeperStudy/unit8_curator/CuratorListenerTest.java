package com.zookeeperStudy.unit8_curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.retry.RetryNTimes;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.zookeeper.CreateMode;

/**
 * CuratorListener
 *
 * @author chenwu on 2019.12.29
 */
public class CuratorListenerTest {

    private static CuratorListener masterListener = new CuratorListener() {
        @Override
        public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {
            switch (event.getType()) {
                case CHILDREN:
                    break;
                case CREATE:
                    System.out.println("path="+event.getPath()+",data="+new String(event.getData()));
                    break;
                case DELETE:
                    break;
            }
        }
    };

    public static void main(String[] args) {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient("127.0.0.1:2181",new RetryNTimes(5,1000*10));
        try{
            curatorFramework.start();
            curatorFramework.getCuratorListenable().addListener(masterListener);
            curatorFramework.create().withMode(CreateMode.PERSISTENT).forPath("/curator1","success".getBytes());
            Thread.sleep(100*1000);
            curatorFramework.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

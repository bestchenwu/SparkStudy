package com.zookeeperStudy.unit8_curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.api.UnhandledErrorListener;
import org.apache.curator.retry.RetryNTimes;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.zookeeper.CreateMode;

/**
 * CuratorListener
 *
 * @author chenwu on 2019.12.29
 */
public class CuratorListenerTest {

    //调用inBackGround方法会获得监听,而对于节点的创建或者修改则不会处罚监听事件
    private static CuratorListener masterListener = new CuratorListener() {
        @Override
        public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {
                System.out.println("监听事件触发，event内容为：" + event);
//            switch (event.getType()) {
//                case CHILDREN:
//                    break;
//                case CREATE:
//                    System.out.println("path="+event.getPath()+",data="+new String(event.getData()));
//                    break;
//                case DELETE:
//                    break;
//            }
        }
    };

    private static UnhandledErrorListener unhandledErrorListener = new UnhandledErrorListener() {
        @Override
        public void unhandledError(String message, Throwable e) {
            System.out.println(message);
        }
    };

    public static void main(String[] args) {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient("127.0.0.1:2181",new RetryNTimes(5,1000*10));
        try{
            curatorFramework.start();
            curatorFramework.getCuratorListenable().addListener(masterListener);                                        
            curatorFramework.getUnhandledErrorListenable().addListener(unhandledErrorListener);
            curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath("/curator1","success".getBytes());
            //curatorFramework.getData().inBackground().forPath("/curator1");
            //输出:
            /**
             * 监听事件触发，event内容为：CuratorEventImpl{type=WATCHED, resultCode=3, path='null', name='null', children=null, context=null, stat=null, data=null, watchedEvent=WatchedEvent state:SyncConnected type:None path:null, aclList=null}
             * 监听事件触发，event内容为：CuratorEventImpl{type=GET_DATA, resultCode=0, path='/curator1', name='null', children=null, context=null, stat=17179869199,17179869199,1577861325262,1577861325262,0,0,0,72057739052777476,7,0,17179869199
             * , data=[115, 117, 99, 99, 101, 115, 115], watchedEvent=null, aclList=null}
             */
            curatorFramework.getData().forPath("/curator1");
            //输出
            /**
             * 监听事件触发，event内容为：CuratorEventImpl{type=WATCHED, resultCode=3, path='null', name='null', children=null, context=null, stat=null, data=null, watchedEvent=WatchedEvent state:SyncConnected type:None path:null, aclList=null}
             * 监听事件触发，event内容为：CuratorEventImpl{type=CLOSING, resultCode=0, path='null', name='null', children=null, context=null, stat=null, data=null, watchedEvent=null, aclList=null}
             *
             */
            Thread.sleep(10*1000);
            curatorFramework.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

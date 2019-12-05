package com.zookeeperStudy.unit4_decode_state_change;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.List;

/**
 * 获取/workers下的所有节点
 */
public class WorkerChildren implements Watcher {

    private ZooKeeper zk;

    public void startZK(String hostPort) throws IOException {
        zk = new ZooKeeper(hostPort, 60000, this);
    }

    public void stopZK() {
        try {
            zk.close();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void getWorks() {
        zk.getChildren("/workers", childrenChangeWatcher, childrenCallBack, null);
    }

    private Watcher childrenChangeWatcher = new Watcher() {
        @Override
        public void process(WatchedEvent event) {
            if (event.getType() == Event.EventType.NodeChildrenChanged) {
                assert "/workers".equals(event.getPath());
                getWorks();
            }
        }
    };

    private AsyncCallback.ChildrenCallback childrenCallBack = new AsyncCallback.ChildrenCallback() {
        @Override
        public void processResult(int rc, String path, Object ctx, List<String> children) {
            switch (KeeperException.Code.get(rc)) {
                case CONNECTIONLOSS:
                    getWorks();
                    break;
                case OK:
                    System.out.println("children size:"+children.size());
                    children.forEach(System.out::println);
                    break;
                default:
                    throw new RuntimeException("you should never reached here");
            }
        }
    };


    @Override
    public void process(WatchedEvent event) {
        System.out.println(event);
    }

    public static void main(String[] args) throws IOException {
        WorkerChildren workerChildren = new WorkerChildren();
        workerChildren.startZK("127.0.0.1:2181");
        workerChildren.getWorks();
        try{
            Thread.sleep(1000*60);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        workerChildren.stopZK();

    }
}

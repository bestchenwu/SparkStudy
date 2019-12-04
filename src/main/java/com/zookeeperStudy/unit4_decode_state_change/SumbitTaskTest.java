package com.zookeeperStudy.unit4_decode_state_change;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * 提交任务的回调函数测试类
 *
 * @author chenwu on 2019.12.4
 */
public class SumbitTaskTest implements Watcher {

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

    public void submitTasks(String task) {
        zk.create("/tasks/task-", task.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL, stringCallback, task);
    }

    private AsyncCallback.StringCallback stringCallback = new AsyncCallback.StringCallback() {
        @Override
        public void processResult(int rc, String path, Object ctx, String name) {
            switch (KeeperException.Code.get(rc)) {
                case CONNECTIONLOSS:
                    submitTasks((String) ctx);
                    break;
                case OK:
                    System.out.println("created task:" + (String) ctx);
                    break;
                default:
                    throw new RuntimeException("you should never reach here");
            }
        }
    };

    @Override
    public void process(WatchedEvent event) {
        System.out.println(event);
    }

    public static void main(String[] args) throws IOException {
        SumbitTaskTest sumbitTaskTest = new SumbitTaskTest();
        sumbitTaskTest.startZK("127.0.0.1:2181");
        sumbitTaskTest.submitTasks("submitTasks");
        try {
            Thread.sleep(60 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sumbitTaskTest.stopZK();

    }
}

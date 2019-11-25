package com.zookeeperStudy.unit3_java_api;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * 设置zookeeper元数据
 *
 * @author chenwu on 2019.11.24
 */
public class MetaDataTest implements Watcher {

    private Random random = new Random(47);
    private ZooKeeper zk;

    private AsyncCallback.StringCallback stringCallback = new AsyncCallback.StringCallback() {
        @Override
        public void processResult(int rc, String path, Object ctx, String name) {
            switch (KeeperException.Code.get(rc)) {
                case CONNECTIONLOSS:
                    createParent(path, (byte[]) ctx);
                    break;
                case OK:
                    System.out.println("parent created");
                    break;
                case NODEEXISTS:
                    System.out.println("parent already registered,path=" + path);
                    break;
                default:
                    throw new RuntimeException("you should reach this code block");

            }
        }
    };

    private void startZK(String hostPort, int sessionTimeout, Watcher watcher) throws IOException {
        zk = new ZooKeeper(hostPort, sessionTimeout, watcher);
    }

    private void stopZK() {
        try {
            zk.close();
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }

    }

    private void createParent(String path, byte[] data) {
        //这里的第二个参数data表示要在节点里保存的数据
        //最后一个参数data表示我们要在回调函数里传入的参数
        zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, stringCallback, data);
    }

    private void bootstrap() {
        byte[] bytes = new byte[0];
        createParent("/workers", bytes);
        createParent("/assign", bytes);
        createParent("/tasks", bytes);
        createParent("/status", bytes);
    }

    private void registerWorkers() {
        Integer serverId = random.nextInt(100);
        byte[] bytes = "Idle".getBytes();
        zk.create("/workers/worker-" + serverId, bytes, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, createCallback, serverId);
    }

    private AsyncCallback.StringCallback createCallback = new AsyncCallback.StringCallback() {
        @Override
        public void processResult(int rc, String path, Object ctx, String name) {
            Integer serverId = (Integer) ctx;
            switch (KeeperException.Code.get(rc)) {
                case CONNECTIONLOSS:
                    registerWorkers();
                    break;
                case OK:
                    System.out.println("worker has registered successfully,workerId:" + serverId);
                    break;
                case NODEEXISTS:
                    System.out.println("worker has already exists,workerId:" + serverId);
                    break;
                default:
                    throw new RuntimeException("you should never reach this code block");
            }
        }
    };

    @Override
    public void process(WatchedEvent event) {
        System.out.println(event);
    }

    public static void main(String[] args) throws IOException {
        final MetaDataTest metaDataTest = new MetaDataTest();
        metaDataTest.startZK("127.0.0.1:2181", 6000, metaDataTest);
//        metaDataTest.bootstrap();
        IntStream.range(0, 10).forEach(number -> {
            metaDataTest.registerWorkers();
        });
        try {
            Thread.sleep(1000 * 30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        metaDataTest.stopZK();
    }
}

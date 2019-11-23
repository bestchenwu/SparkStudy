package com.zookeeperStudy.unit3_java_api;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.Random;
import java.io.IOException;

public class Master implements Watcher {

    private ZooKeeper zk;
    private String hostPort;
    private Random random = new Random(47);
    private String serverId;
    private volatile boolean isLeader = false;

    public Master(String hostPort) {
        this.hostPort = hostPort;
        serverId = Integer.toHexString(random.nextInt(3) + 1);
    }

    public void startZK() throws IOException {
        //第一个参数是zk的链接port,第二个参数是客户端超时时间,单位是毫秒,第三个参数是监视器(监控节点的变化等)
        zk = new ZooKeeper(hostPort, 15000, this);
    }

    public void stopZK() throws InterruptedException {
        zk.close();
    }

    private boolean checkIsMaster() {
        while (true) {
            try {
                Stat stat = new Stat();
                //获取节点内容
                //这里getData方法会填充znode节点的元数据信息
                byte[] data = zk.getData("/masters", false, stat);
                isLeader = new String(data).equals(serverId);
                if (isLeader) {
                    System.out.println("czxid:" + stat.getCzxid());
                    System.out.println("current node is master,serverId:" + serverId);
                } else {
                    System.out.println("current node is not master");
                }

                Thread.sleep(10 * 1000);
                return isLeader;
            } catch (KeeperException | InterruptedException e) {
                return false;
            }
        }
    }

    /**
     * 创建节点
     */
    public void runForMaster() {
        while (true) {
            try {
                //ZooDefs.Ids.OPEN_ACL_UNSAFE为所有人提供了权限
                //CreateMode.EPHEMERAL表示临时性节点,当会话失效或者关闭的时候,zookeeper会检测到并删除该节点
                zk.create("/masters", serverId.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL
                );
                isLeader = true;
            } catch (KeeperException | InterruptedException e) {
                isLeader = false;
                break;
                //throw new RuntimeException(e);
            }
            if (checkIsMaster()) {
                break;
            }

        }

    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println(watchedEvent);
    }


    public static void main(String[] args) throws Exception {
        Master master = new Master("127.0.0.1:2181");
        master.startZK();
        master.runForMaster();
        //睡眠10分钟,避免节点被立即删除了
        if (master.isLeader) {
            System.out.println("I am the leader");
        } else {
            System.out.println("someone else is the leader");
        }
        master.stopZK();
        //master.stopZK();
    }
}

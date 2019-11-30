package com.zookeeperStudy.unit3_java_api;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Date;

/**
 * 客户端管理
 */
public class AdminClient implements Watcher {

    private ZooKeeper zk;

    public AdminClient(String hostPort) {
        try {
            this.zk = new ZooKeeper(hostPort, 60000, this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void stopZK() {
        try {
            this.zk.close();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void listStatus() {
        Stat stat = new Stat();
        try {
            byte[] data = zk.getData("/masters", false, stat);
            Date startDate = new Date(stat.getCtime());
            System.out.println("/masters data:" + new String(data) + ",start date:" + startDate);
            for (String children : zk.getChildren("/tasks", false)) {
                byte[] childData = zk.getData("/tasks/"+children,false,stat);
                System.out.println("/tasks/"+children+",data:"+new String(childData));
            }
        } catch (KeeperException | InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println(event);
    }

    public static void main(String[] args) {
        AdminClient adminClient = new AdminClient(args[0]);
        adminClient.listStatus();
        adminClient.stopZK();
    }
}

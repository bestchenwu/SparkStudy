package com.zookeeperStudy.unit3_java_api;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

public class Master implements Watcher {

    private ZooKeeper zk;
    private String hostPort;

    public Master(String hostPort){
        this.hostPort = hostPort;
    }

    public void startZK() throws IOException {
        //第一个参数是zk的链接port,第二个参数是客户端超时时间,单位是毫秒,第三个参数是监视器(监控节点的变化等)
        zk = new ZooKeeper(hostPort,15000,this);
    }

    public void stopZK() throws InterruptedException{
        zk.close();
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println(watchedEvent);
    }

    public static void main(String[] args) throws Exception{
        Master master = new Master("127.0.0.1:2181");
        master.startZK();
        Thread.sleep(60000);
        master.stopZK();
    }
}

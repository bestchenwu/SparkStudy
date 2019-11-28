package com.zookeeperStudy.unit3_java_api;

import org.apache.zookeeper.*;

import java.io.IOException;

public class Client implements Watcher {

    private ZooKeeper zk;

    public Client(String hostPort) throws IOException {
        zk = new ZooKeeper(hostPort,60000,this);
    }

    public void executeCommand(String command){
        while(true){
            try{
                //这里创建一个永久性的节点名称单调递增的节点
                zk.create("/tasks/task-",command.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
                break;
            }catch(KeeperException | InterruptedException e){
                throw new RuntimeException(e);
            }

        }
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println(event);
    }

    private void stopZK(){
        try{
            this.zk.close();
        }catch(InterruptedException e){
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) throws IOException {
        Client client = new Client("127.0.0.1:2181");
        client.executeCommand(args[0]);
        try{
            Thread.sleep(1000*30);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        client.stopZK();
    }
}

package com.zookeeperStudy.unit4_decode_state_change;


import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.Arrays;

/**
 * 批量改变状态的操作集合
 *
 * @author chenwu on 2019.12.14
 */
public class MultiOpTest implements Watcher {

    private ZooKeeper zk;

    public void startZK(String hostPort) throws IOException {
        zk = new ZooKeeper(hostPort,60000,this);
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println(event);
    }

    public void multiProcess() {
        try{
            zk.multi(Arrays.asList(Op.create("/masters-op","multiOpCreate".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL),Op.delete("/status",0)));
        }catch(InterruptedException | KeeperException e){
            throw new RuntimeException(e);
        }

    }

    public void stopZK(){
        try{
            zk.close();
        }catch(InterruptedException e){
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) throws IOException{
        MultiOpTest multiOpTest = new MultiOpTest();
        multiOpTest.startZK("127.0.0.1:2181");
        multiOpTest.multiProcess();
        try{
            Thread.sleep(10*1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        multiOpTest.stopZK();
    }
}

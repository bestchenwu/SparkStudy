package com.zookeeperStudy.unit4_decode_state_change;

import com.zookeeperStudy.common.MasterStates;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Random;

/**
 * 检查znode的状态
 *
 * @author chenwu on 2019.12.1
 */
public class ZNodeExistCheck implements Watcher {

    private ZooKeeper zk;
    private String serverId;
    private Random random = new Random(47);
    private volatile boolean isLeader = false;
    private volatile MasterStates masterStates;

    public ZNodeExistCheck(String hostPort) throws IOException {
        this.zk = new ZooKeeper(hostPort, 60000, this);
        serverId = Integer.toHexString(random.nextInt(3) + 1);
    }

    private void stopZK() {
        try {
            this.zk.close();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void checkPathIsExisted(String path) {
        zk.exists(path, existsWatcher, statCallback, null);
    }

    /**
     * 同步创建节点
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

    /**
     * 异步创建节点
     */
    public void runForMasterAsync() {
        zk.create("/masters", serverId.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, masterCreateCallback, null);
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


    Watcher existsWatcher = new Watcher() {
        @Override
        public void process(WatchedEvent event) {
            System.out.println(event);
        }
    };

    AsyncCallback.StatCallback statCallback = new AsyncCallback.StatCallback() {
        @Override
        public void processResult(int rc, String path, Object ctx, Stat stat) {

        }
    };

    AsyncCallback.StringCallback masterCreateCallback = new AsyncCallback.StringCallback() {
        @Override
        public void processResult(int rc, String path, Object ctx, String name) {
            switch (KeeperException.Code.get(rc)) {
                //在链接丢失的时候去检查master节点是否存在
                case CONNECTIONLOSS:
                    checkIsMaster();
                    break;
                case OK:
                    masterStates = MasterStates.ELECTED;
                    //todo:takeLeaderShip()
                    break;
                //MasterState
                case NODEEXISTS:
                    masterStates = MasterStates.NOTELECTED;
                    checkPathIsExisted("/masters");
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

    public static void main(String[] args) {

    }
}

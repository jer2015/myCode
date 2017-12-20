package com.tom.test;

import org.apache.zookeeper.*;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Tom on 2017/2/7.
 */
public class TestZk {

    /**
     * System.out.println("=========创建节点===========");
     * if(zookeeper.exists("/test", false) == null)
     * {
     * zookeeper.create("/test", "znode1".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
     * }
     * System.out.println("=============查看节点是否安装成功===============");
     * System.out.println(new String(zookeeper.getData("/test", false, null)));
     * <p>
     * System.out.println("=========修改节点的数据==========");
     * String data = "zNode2";
     * zookeeper.setData("/test", data.getBytes(), -1);
     * <p>
     * System.out.println("========查看修改的节点是否成功=========");
     * System.out.println(new String(zookeeper.getData("/test", false, null)));
     * <p>
     * System.out.println("=======删除节点==========");
     * zookeeper.delete("/test", -1);
     * <p>
     * System.out.println("==========查看节点是否被删除============");
     * System.out.println("节点状态：" + zookeeper.exists("/test", false));
     */

    @Test
    public void test1() {
        String ZKServers = "172.16.6.104:2181";
        /**
         * 使用正确权限信息的zookeeper会话访问含有权限信息的数据节点
         */
        try {
            final CountDownLatch signal = new CountDownLatch(1);
            ZooKeeper zk3 = new ZooKeeper(ZKServers, 5000, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    signal.countDown();
                }
            });
            signal.await();
//            zk3.addAuthInfo("digest", "admin:123456".getBytes());
            System.out.println("NodeData: " + new String(zk3.getData("/", false, null)));
            //zk3.delete("/", -1);
            zk3.close();
        } catch (Exception e) {
            System.out.println("Failed to delete Znode: " + e.getMessage());
        }
    }

    @Test
    public void testZk() throws IOException, InterruptedException, KeeperException {
        //zk集群的地址
        String ZKServers = "192.168.30.164:2181,192.168.30.165:2181,192.168.30.166:2181";

        /**
         * 使用含有权限信息的zookeeper会话创建数据节点
         */
        final CountDownLatch connectedSignal = new CountDownLatch(1);
        ZooKeeper zk = new ZooKeeper("192.168.1.109:2182", 5000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                connectedSignal.countDown();
            }
        });
        connectedSignal.await();
        zk.addAuthInfo("digest", "huey:123".getBytes());
        zk.create("/zk-huey", "hello".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
        zk.close();

        /**
         * 使用无权限信息的zookeeper会话访问含有权限信息的数据节点
         */
        try {
            final CountDownLatch signal = new CountDownLatch(1);
            ZooKeeper zk1 = new ZooKeeper("192.168.1.109:2182", 5000, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    signal.countDown();
                }
            });
            signal.await();
            zk1.getData("/zk-huey", false, null);
            System.out.println("NodeData: " + new String(zk1.getData("/zk-huey", false, null)));
            zk1.close();
        } catch (Exception e) {
            System.out.println("Failed to delete Znode: " + e.getMessage());
        }

        /**
         * 使用错误权限信息的zookeeper会话访问含有权限信息的数据节点
         */
        try {
            final CountDownLatch signal = new CountDownLatch(1);
            ZooKeeper zk2 = new ZooKeeper("192.168.1.109:2182", 5000, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    signal.countDown();
                }
            });
            signal.await();
            zk2.addAuthInfo("digest", "huey:abc".getBytes());
            System.out.println("NodeData: " + new String(zk2.getData("/zk-huey", false, null)));
            zk2.close();
        } catch (Exception e) {
            System.out.println("Failed to delete Znode: " + e.getMessage());
        }

        /**
         * 使用正确权限信息的zookeeper会话访问含有权限信息的数据节点
         */
        try {
            final CountDownLatch signal = new CountDownLatch(1);
            ZooKeeper zk3 = new ZooKeeper("192.168.1.109:2182", 5000, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    signal.countDown();
                }
            });
            signal.await();
            zk3.addAuthInfo("digest", "huey:123".getBytes());
            System.out.println("NodeData: " + new String(zk3.getData("/zk-huey", false, null)));
            zk3.close();
        } catch (Exception e) {
            System.out.println("Failed to delete Znode: " + e.getMessage());
        }

    }


}

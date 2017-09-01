package com.lovecws.mumu.zookeeper.simple;

import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

public class ZookeeperCreat {

    /**
     * 创建数据
     * @param path 路径
     * @param value 值
     * @param createMode 创建模式 PERSISTENT PERSISTENT_SEQUENTIAL EPHEMERAL EPHEMERAL_SEQUENTIAL
     * @return
     */
    public String create(String path,String value,CreateMode createMode){
        ZookeeperConnect zookeeperConnecttion=new ZookeeperConnect();
        ZooKeeper zooKeeper = zookeeperConnecttion.connect();
        try {
            String result = zooKeeper.create(path, value!=null?value.getBytes():null, ZooDefs.Ids.OPEN_ACL_UNSAFE, createMode);
            return result;
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 异步创建
     * @param path 路径
     * @param value 值
     * @param createMode 创建模式 PERSISTENT PERSISTENT_SEQUENTIAL EPHEMERAL EPHEMERAL_SEQUENTIAL
     * @return
     */
    public String createAsync(String path,String value,CreateMode createMode){
        ZookeeperConnect zookeeperConnecttion=new ZookeeperConnect();
        ZooKeeper zooKeeper = zookeeperConnecttion.connect();
        CountDownLatch countDownLatch=new CountDownLatch(1);
        zooKeeper.create(path, value != null ? value.getBytes() : null, ZooDefs.Ids.OPEN_ACL_UNSAFE, createMode, new ZookeeperCreationStringCallback(),countDownLatch);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public class ZookeeperCreationStringCallback implements AsyncCallback.StringCallback{
        @Override
        public void processResult(int i, String s, Object o, String s1) {
            System.out.println("节点创建成功:"+i+" "+s+" "+o+" "+s1);
            CountDownLatch countDownLatch = (CountDownLatch) o;
            countDownLatch.countDown();
        }
    }
}

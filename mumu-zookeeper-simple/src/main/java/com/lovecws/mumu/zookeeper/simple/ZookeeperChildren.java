package com.lovecws.mumu.zookeeper.simple;
import org.apache.zookeeper.*;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ZookeeperChildren {

    /**
     * 获取路径下的所有子节点
     * @param path 路径
     * @param watch 是否启用watch 启用之后 默认使用创建连接时候的watcher
     * @return
     */
    public List<String> getChildren(String path,boolean watch){
        ZookeeperConnect zookeeperConnecttion=new ZookeeperConnect();
        ZooKeeper zooKeeper = zookeeperConnecttion.connect();
        try {
            List<String> children = zooKeeper.getChildren(path, watch);
            return children;
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ZookeeperChildrenCallback zookeeperChildrenCallback=new ZookeeperChildrenCallback();
    private ZookeeperChildrenWatch zookeeperChildrenWatch=new ZookeeperChildrenWatch();
    /**
     * 异步获取路径下的子节点
     * @param path 路径
     */
    public void getChildrenAsync(String path){
        ZookeeperConnect zookeeperConnecttion=new ZookeeperConnect();
        ZooKeeper zooKeeper = zookeeperConnecttion.connect();
        CountDownLatch countDownLatch=new CountDownLatch(1);
        zooKeeper.getChildren(path,zookeeperChildrenWatch,zookeeperChildrenCallback,countDownLatch);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public class ZookeeperChildrenWatch implements Watcher{
        @Override
        public void process(WatchedEvent watchedEvent) {
            if(watchedEvent.getType().equals(Event.EventType.NodeChildrenChanged)){
                System.out.println("数据节点发生改变");
                getChildrenAsync(watchedEvent.getPath());
            }
        }
    }

    public class ZookeeperChildrenCallback implements AsyncCallback.ChildrenCallback{
        @Override
        public void processResult(int i, String s, Object o, List<String> list) {
            System.out.println("获取子节点回调:"+i+" "+s+" "+o+" "+list);
            CountDownLatch countDownLatch = (CountDownLatch) o;
            countDownLatch.countDown();
        }
    }
}

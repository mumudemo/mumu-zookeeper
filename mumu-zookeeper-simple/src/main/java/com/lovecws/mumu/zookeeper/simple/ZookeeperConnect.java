package com.lovecws.mumu.zookeeper.simple;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZookeeperConnect {

    private CountDownLatch countDownLatch=new CountDownLatch(1);

    private static ZooKeeper zooKeeper=null;
    /**
     * 创建连接
     * @return
     */
    public synchronized ZooKeeper connect(){
        try {
            if(zooKeeper==null){
                zooKeeper=new ZooKeeper(ZookeeperConfiguration.address,ZookeeperConfiguration.sessionTimeOut,new ZookeeperConnectionWatcher());
                countDownLatch.await();
            }
            return zooKeeper;
        } catch (IOException e) {
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 以上一个回话为基础创建客户端连接
     * @param sessionId
     * @param sessionPasswd
     * @return
     */
    public synchronized ZooKeeper connect(long sessionId,byte[] sessionPasswd){
        try {
            if(zooKeeper==null){
                zooKeeper = new ZooKeeper(ZookeeperConfiguration.address, ZookeeperConfiguration.sessionTimeOut, new ZookeeperConnectionWatcher(), sessionId, sessionPasswd);
                countDownLatch.await();
            }
            return zooKeeper;
        } catch (IOException e) {
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 连接监听器
     */
    public class ZookeeperConnectionWatcher implements Watcher{
        @Override
        public void process(WatchedEvent watchedEvent) {
            if(watchedEvent.getState().equals(Event.KeeperState.SyncConnected)){
                ZookeeperChildren zookeeperChildren = new ZookeeperChildren();
                if(watchedEvent.getType().equals(Event.EventType.NodeDataChanged)){
                    System.out.println("zookeeper["+watchedEvent.getPath()+"] 数据发生变更");
                    System.out.println(zookeeperChildren.getChildren(watchedEvent.getPath(),true));
                }else if(watchedEvent.getType().equals(Event.EventType.NodeCreated)){
                    System.out.println("zookeeper["+watchedEvent.getPath()+"] 检测节点新增");
                    System.out.println(zookeeperChildren.getChildren(watchedEvent.getPath(),true));
                }else if(watchedEvent.getType().equals(Event.EventType.NodeChildrenChanged)){
                    System.out.println("zookeeper["+watchedEvent.getPath()+"] 子节点列表发生变更");
                    System.out.println(zookeeperChildren.getChildren(watchedEvent.getPath(),true));
                }else if(watchedEvent.getType().equals(Event.EventType.NodeDeleted)){
                    System.out.println("zookeeper["+watchedEvent.getPath()+"] 检测节点被删除");
                    System.out.println(zookeeperChildren.getChildren(watchedEvent.getPath(),true));
                }else if(watchedEvent.getType().equals(Event.EventType.None)){
                    System.out.println("zookeeper连接创建完成");
                    countDownLatch.countDown();
                }
            }else if(watchedEvent.getState().equals(Event.KeeperState.Disconnected)){
                System.out.println("zookeeper断开连接");
            }else if(watchedEvent.getState().equals(Event.KeeperState.Expired)){
                System.out.println("zookeeper连接超时");
                countDownLatch.countDown();
            }


        }
    }
}

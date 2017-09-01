package com.lovecws.mumu.zookeeper.simple;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

public class ZookeeperDelete {

    /**
     * 编辑信息
     * @param path 路径
     * @param version zookeeper数据版本,当删除数据的时候会比对version，如果version发生改动，则删除失败（compare and swap）传入-1代表以最新版本来删除数据
     * @return
     */
    public String delete(String path,int version){
        ZookeeperConnect zookeeperConnecttion=new ZookeeperConnect();
        ZooKeeper zooKeeper = zookeeperConnecttion.connect();
        try {
            zooKeeper.delete(path,version);
            return "数据删除成功";
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 异步删除
     * @param path 路径
     * @param version zookeeper数据版本,当删除数据的时候会比对version，如果version发生改动，则删除失败（compare and swap）传入-1代表以最新版本来删除数据
     * @return
     */
    public String deleteAsync(String path,int version){
        ZookeeperConnect zookeeperConnecttion=new ZookeeperConnect();
        ZooKeeper zooKeeper = zookeeperConnecttion.connect();
        CountDownLatch countDownLatch=new CountDownLatch(1);
        zooKeeper.delete(path,version,new ZookeeperEditStatVoidback(),countDownLatch);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public class ZookeeperEditStatVoidback implements AsyncCallback.VoidCallback{
        @Override
        public void processResult(int i, String s, Object o) {
            System.out.println("节点删除:"+i+" "+s+" "+o);
            CountDownLatch countDownLatch = (CountDownLatch) o;
            countDownLatch.countDown();
        }
    }
}

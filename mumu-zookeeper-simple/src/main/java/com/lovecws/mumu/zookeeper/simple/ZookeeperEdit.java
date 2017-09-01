package com.lovecws.mumu.zookeeper.simple;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

public class ZookeeperEdit {

    /**
     * 编辑信息
     * @param path 路径
     * @param value 修改后的值
     * @param version zookeeper数据版本,当修改数据的时候会比对version，如果version发生改动，则修改失败（compare and swap）传入-1代表以最新版本来修改数据
     * @return
     */
    public String edit(String path,String value,int version){
        ZookeeperConnect zookeeperConnecttion=new ZookeeperConnect();
        ZooKeeper zooKeeper = zookeeperConnecttion.connect();
        try {
            Stat data = zooKeeper.setData(path, value != null ? value.getBytes() : null, version);
            return data.toString();
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 异步编辑
     * @param path 路径
     * @param value 修改后的值
     * @param version zookeeper数据版本,当修改数据的时候会比对version，如果version发生改动，则修改失败（compare and swap）传入-1代表以最新版本来修改数据
     * @return
     */
    public String editAsync(String path,String value,int version){
        ZookeeperConnect zookeeperConnecttion=new ZookeeperConnect();
        ZooKeeper zooKeeper = zookeeperConnecttion.connect();
        CountDownLatch countDownLatch=new CountDownLatch(1);
        zooKeeper.setData(path, value != null ? value.getBytes() : null, version,new ZookeeperEditStatCallback(),countDownLatch);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public class ZookeeperEditStatCallback implements AsyncCallback.StatCallback{
        @Override
        public void processResult(int i, String s, Object o, Stat stat) {
            System.out.println("编辑成功:"+i+" "+s+" "+o+" "+stat);
            CountDownLatch countDownLatch = (CountDownLatch) o;
            countDownLatch.countDown();
        }
    }
}

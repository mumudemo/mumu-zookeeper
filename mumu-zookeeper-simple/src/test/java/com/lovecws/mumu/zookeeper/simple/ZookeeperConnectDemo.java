package com.lovecws.mumu.zookeeper.simple;

import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;

public class ZookeeperConnectDemo {

    @Test
    public void connect(){
        ZookeeperConnect zookeeperConnecttion=new ZookeeperConnect();
        ZooKeeper zooKeeper = zookeeperConnecttion.connect();
        System.out.println(zooKeeper);
    }

    @Test
    public void connectWithZookeeper(){
        ZookeeperConnect zookeeperConnecttion=new ZookeeperConnect();
        ZooKeeper zooKeeper = zookeeperConnecttion.connect( 0l,null);
        System.out.println(zooKeeper);
    }
}

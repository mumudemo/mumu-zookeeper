package com.lovecws.mumu.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.junit.Test;

public class ZookeeperConnectDemo {

    @Test
    public void connect(){
        ZookeeperConnect zookeeperConnect=new ZookeeperConnect();
        CuratorFramework connect = zookeeperConnect.connect();
        try {
            System.out.println(connect.getChildren().forPath("/"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

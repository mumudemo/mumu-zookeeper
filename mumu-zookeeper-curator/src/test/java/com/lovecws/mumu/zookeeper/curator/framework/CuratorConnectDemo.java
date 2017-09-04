package com.lovecws.mumu.zookeeper.curator.framework;

import com.lovecws.mumu.zookeeper.curator.framework.CuratorConnect;
import org.apache.curator.framework.CuratorFramework;
import org.junit.Test;

public class CuratorConnectDemo {

    @Test
    public void connect(){
        CuratorConnect zookeeperConnect=new CuratorConnect();
        CuratorFramework connect = zookeeperConnect.connect();
        try {
            System.out.println(connect.getChildren().forPath("/"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

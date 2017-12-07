package com.lovecws.mumu.zookeeper.zkclient;

import org.I0Itec.zkclient.ZkClient;
import org.junit.Test;

import java.util.List;

public class ZKClientConnectDemo {

    @Test
    public void zkClient(){
        ZkClient zkClient = new ZKClientConnect().zkClient();
        List<String> children = zkClient.getChildren("/zookeeper");
        System.out.println(children);
    }

    @Test
    public void zkClientWithSerializer(){
        ZkClient zkClient = new ZKClientConnect().zkClientWithSerializer();
        List<String> children = zkClient.getChildren("/zookeeper1223");
        System.out.println(children);
    }
}

package com.lovecws.mumu.zookeeper.simple;

import org.apache.zookeeper.server.ZooKeeperServer;
import org.apache.zookeeper.server.ZooKeeperServerMain;

public class ZookeeperServerDemo {

    /**
     * 单独开启一个zookeeper服务【用于测试开发】
     * @param args
     */
    public static void main(String[] args) {
        ZooKeeperServerMain.main(new String[]{"2181","zookeeper"});
    }
}

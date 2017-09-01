package com.lovecws.mumu.zookeeper.simple;

import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ZookeeperChildrenDemo {

    @Test
    public void getChildren(){
        ZookeeperChildren zookeeperChildren=new ZookeeperChildren();
        List<String> children = zookeeperChildren.getChildren("/simpleAsync",true);
        System.out.println(children);
        try {
            TimeUnit.SECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getChildrenAsync(){
        ZookeeperChildren zookeeperChildren=new ZookeeperChildren();
        zookeeperChildren.getChildrenAsync("/simpleAsync");
        try {
            TimeUnit.SECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

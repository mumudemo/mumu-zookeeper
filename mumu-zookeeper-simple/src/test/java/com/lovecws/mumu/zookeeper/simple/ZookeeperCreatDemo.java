package com.lovecws.mumu.zookeeper.simple;

import org.apache.zookeeper.CreateMode;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class ZookeeperCreatDemo {

    @Test
    public void create(){
        ZookeeperCreat zookeeperCreation=new ZookeeperCreat();
        String result = zookeeperCreation.create("/simple/temp", null, CreateMode.PERSISTENT);
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }

    @Test
    public void createAsync(){
        ZookeeperCreat zookeeperCreation=new ZookeeperCreat();
        String result = zookeeperCreation.createAsync("/simpleAsync/async", null,CreateMode.PERSISTENT_SEQUENTIAL);
        System.out.println(result);
    }
}

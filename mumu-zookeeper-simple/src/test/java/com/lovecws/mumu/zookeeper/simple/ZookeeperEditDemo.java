package com.lovecws.mumu.zookeeper.simple;

import org.apache.zookeeper.CreateMode;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class ZookeeperEditDemo {

    @Test
    public void edit(){
        ZookeeperEdit zookeeperEdit=new ZookeeperEdit();
        String result = zookeeperEdit.edit("/simple2", "lovecws5211314", -1);
        System.out.println(result);
    }

    @Test
    public void editAsync(){
        ZookeeperEdit zookeeperEdit=new ZookeeperEdit();
        String result = zookeeperEdit.editAsync("/simple2", "lovecws", -1);
        System.out.println(result);
    }
}

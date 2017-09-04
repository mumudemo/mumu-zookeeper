package com.lovecws.mumu.zookeeper.curator.framework;

import com.lovecws.mumu.zookeeper.curator.framework.CuratorCreate;
import org.junit.Test;

public class CuratorCreateDemo {

    @Test
    public void create(){
        CuratorCreate zookeeperCreate=new CuratorCreate();
        zookeeperCreate.create("/curator","lovecws".getBytes());
    }
}

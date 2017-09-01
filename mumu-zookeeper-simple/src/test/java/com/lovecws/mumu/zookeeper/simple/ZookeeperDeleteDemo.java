package com.lovecws.mumu.zookeeper.simple;

import org.junit.Test;

public class ZookeeperDeleteDemo {

    @Test
    public void delete(){
        ZookeeperDelete zookeeperDelete=new ZookeeperDelete();
        String result = zookeeperDelete.delete("/simple2", -1);
        System.out.println(result);
    }

    @Test
    public void deleteAsync(){
        ZookeeperDelete zookeeperDelete=new ZookeeperDelete();
        String result = zookeeperDelete.deleteAsync("/simpleAsync/async0000000003", -1);
        System.out.println(result);
    }
}

package com.lovecws.mumu.zookeeper.simple;

import org.junit.Test;

public class ZookeeperTransactionDemo {

    @Test
    public void multiCreate(){
        ZookeeperTransaction zookeeperTransaction=new ZookeeperTransaction();
        Object multiCreate = zookeeperTransaction.multiCreate();
        System.out.println(multiCreate);
    }
}

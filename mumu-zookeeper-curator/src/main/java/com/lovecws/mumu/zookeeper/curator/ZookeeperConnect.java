package com.lovecws.mumu.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class ZookeeperConnect {

    /**
     * 获取curator对象
     * @return
     */
    public CuratorFramework connect(){
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(ZookeeperConfiguration.address, retryPolicy);
        client.start();
        return client;
    }
}

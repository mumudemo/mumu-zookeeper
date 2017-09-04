package com.lovecws.mumu.zookeeper.curator.framework;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CuratorConnect {

    /**
     * 获取curator对象
     * @return
     */
    public CuratorFramework connect(){
        //声明一个重试策略
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(CuratorConfiguration.address, retryPolicy);
        client.start();
        return client;
    }
}

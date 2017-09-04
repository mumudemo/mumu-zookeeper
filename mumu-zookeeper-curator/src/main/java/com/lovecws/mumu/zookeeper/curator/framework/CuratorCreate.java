package com.lovecws.mumu.zookeeper.curator.framework;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;

public class CuratorCreate {

    /**
     * 创建节点
     * @return
     */
    public String create(String path,byte[] bs){
        CuratorFramework curatorFramework = new CuratorConnect().connect();
        try {
            String result = curatorFramework.
                    create().
                    creatingParentsIfNeeded().
                    withMode(CreateMode.PERSISTENT_SEQUENTIAL).
                    forPath(path,bs);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

package com.lovecws.mumu.zookeeper.curator.framework;

import org.apache.curator.framework.CuratorFramework;

public class CuratorDelete {

    /**
     * 删除节点
     * @return
     */
    public String delete(String path){
        CuratorFramework curatorFramework = new CuratorConnect().connect();
        try {
            curatorFramework.
                    delete().
                    deletingChildrenIfNeeded().
                    withVersion(-1).
                    forPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

package com.lovecws.mumu.zookeeper.curator.framework;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;

import java.util.concurrent.TimeUnit;

public class CuratorCache {

    /**
     * 节点监听
     * @return
     */
    public String nodeCache(String path){
        CuratorFramework curatorFramework = new CuratorConnect().connect();
        NodeCache nodeCache=new NodeCache(curatorFramework,path);
        try {
            nodeCache.start(true);
            nodeCache.getListenable().addListener(new NodeCacheListener() {
                @Override
                public void nodeChanged() throws Exception {
                    System.out.println(nodeCache.getCurrentData().getData());
                }
            });

            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

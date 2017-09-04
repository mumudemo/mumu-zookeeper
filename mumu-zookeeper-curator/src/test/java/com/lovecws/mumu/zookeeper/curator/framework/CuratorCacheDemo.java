package com.lovecws.mumu.zookeeper.curator.framework;

import com.lovecws.mumu.zookeeper.curator.framework.CuratorCache;
import org.junit.Test;

public class CuratorCacheDemo {

    @Test
    public void nodeCache(){
       CuratorCache curatorCache=new CuratorCache();
       curatorCache.nodeCache("/zookeeper1223");
    }
}

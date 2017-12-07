package com.lovecws.mumu.zookeeper.curator.recipes;

import org.junit.Test;

/**
 * Created by Administrator on 2017/12/7/007.
 */
public class CuratorQueueDemo {

    @Test
    public void queue() throws InterruptedException {
        CuratorQueue curatorQueue = new CuratorQueue();
        curatorQueue.queue("/curator/queue");
    }
}

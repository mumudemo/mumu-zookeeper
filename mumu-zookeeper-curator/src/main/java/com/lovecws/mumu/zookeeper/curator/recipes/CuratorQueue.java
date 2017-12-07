package com.lovecws.mumu.zookeeper.curator.recipes;

import com.lovecws.mumu.zookeeper.curator.framework.CuratorConnect;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.queue.SimpleDistributedQueue;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2017/12/7/007.
 * 分布式队列
 */
public class CuratorQueue {

    public void queue(String path) throws InterruptedException {
        CuratorFramework curatorFramework = new CuratorConnect().connect();
        SimpleDistributedQueue queue = new SimpleDistributedQueue(curatorFramework, path);
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            final int j = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        queue.offer(("lovecws" + j).getBytes());
                        countDownLatch.countDown();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        countDownLatch.await();

        try {
            byte[] element = null;
            while ((element = queue.poll()) != null) {
                System.out.println(String.valueOf(element));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

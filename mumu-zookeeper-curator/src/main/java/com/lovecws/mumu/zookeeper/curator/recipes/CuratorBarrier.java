package com.lovecws.mumu.zookeeper.curator.recipes;

import com.lovecws.mumu.zookeeper.curator.framework.CuratorConnect;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.framework.recipes.barriers.DistributedDoubleBarrier;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CuratorBarrier {

    static  DistributedBarrier barrier=null;
    /**
     * 分布式计数器
     * @return
     */
    public String barrier(String path,int currency){
        CountDownLatch latch=new CountDownLatch(currency);
        CountDownLatch startLatch=new CountDownLatch(currency);
        for (int i = 1; i <=currency ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        CuratorFramework curatorFramework = new CuratorConnect().connect();
                        barrier=new DistributedBarrier(curatorFramework,path);
                        System.out.println(Thread.currentThread().getName()+"准备就绪");
                        latch.countDown();
                        barrier.setBarrier();
                        barrier.waitOnBarrier();
                        System.out.println(Thread.currentThread().getName()+"开始...");
                        startLatch.countDown();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        try {
            latch.await();
            TimeUnit.SECONDS.sleep(10);
            barrier.removeBarrier();
            startLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String doubleBarrier(String path,int currency){
        CountDownLatch latch=new CountDownLatch(currency);
        for (int i = 1; i <=currency ; i++)
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        CuratorFramework curatorFramework = new CuratorConnect().connect();
                        DistributedDoubleBarrier barrier = new DistributedDoubleBarrier(curatorFramework, path, currency);
                        System.out.println(Thread.currentThread().getName()+"进入.....");
                        TimeUnit.SECONDS.sleep(5);

                        barrier.enter();
                        System.out.println(Thread.currentThread().getName()+"启动...");
                        TimeUnit.SECONDS.sleep(5);

                        barrier.leave();
                        System.out.println(Thread.currentThread().getName()+"离开...");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        latch.countDown();
                    }
                }
            }).start();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}

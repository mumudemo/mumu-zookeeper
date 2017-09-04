package com.lovecws.mumu.zookeeper.curator.recipes;

import com.lovecws.mumu.zookeeper.curator.framework.CuratorConnect;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class CuratorMutex {

    /**
     * 分布式锁
     * @return
     */
    public String lock(String path,int currency){
        CuratorFramework curatorFramework = new CuratorConnect().connect();
        InterProcessMutex mutex=new InterProcessMutex(curatorFramework,path);
        CountDownLatch latch=new CountDownLatch(currency);
        for (int i = 0; i <currency ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        mutex.acquire();
                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-mm-dd HH:MM:SS");
                        System.out.println(simpleDateFormat.format(new Date()));
                        latch.countDown();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            mutex.release();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}

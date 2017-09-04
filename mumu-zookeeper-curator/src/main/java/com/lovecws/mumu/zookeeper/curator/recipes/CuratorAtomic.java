package com.lovecws.mumu.zookeeper.curator.recipes;

import com.lovecws.mumu.zookeeper.curator.framework.CuratorConnect;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.retry.RetryNTimes;

import java.util.concurrent.CountDownLatch;

public class CuratorAtomic {

    /**
     * 分布式计数器
     * @return
     */
    public String atomic(String path,int currency){
        CuratorFramework curatorFramework = new CuratorConnect().connect();
        DistributedAtomicInteger distributedAtomicInteger=new DistributedAtomicInteger(curatorFramework,path,new RetryNTimes(3,1000));
        CountDownLatch latch=new CountDownLatch(currency);
        for (int i = 1; i <=currency ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        AtomicValue<Integer> add = distributedAtomicInteger.add(currency);
                        System.out.println("before:"+add.preValue()+",after:"+add.postValue());
                        latch.countDown();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        try {
            latch.await();
            System.out.println("total:"+distributedAtomicInteger.get().postValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

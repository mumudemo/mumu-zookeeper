package com.lovecws.mumu.zookeeper.curator.recipes;

import com.lovecws.mumu.zookeeper.curator.recipes.CuratorBarrier;
import org.junit.Test;

public class CuratorBarrierDemo {
    @Test
    public void barrier(){
        CuratorBarrier barrier=new CuratorBarrier();
        barrier.barrier("/curator/barrier",10);
    }

    @Test
    public void doubleBarrier(){
        CuratorBarrier barrier=new CuratorBarrier();
        barrier.doubleBarrier("/curator/barrier2",10);
    }
}

package com.lovecws.mumu.zookeeper.curator.recipes;

import com.lovecws.mumu.zookeeper.curator.recipes.CuratorMutex;
import org.junit.Test;

public class CuratorMutexDemo {

    @Test
    public void lock(){
        CuratorMutex curatorMutex=new CuratorMutex();
        curatorMutex.lock("/curator/master",100);
    }
}

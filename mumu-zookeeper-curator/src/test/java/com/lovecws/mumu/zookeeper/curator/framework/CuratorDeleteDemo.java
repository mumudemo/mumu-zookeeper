package com.lovecws.mumu.zookeeper.curator.framework;

import com.lovecws.mumu.zookeeper.curator.framework.CuratorDelete;
import org.junit.Test;

public class CuratorDeleteDemo {

    @Test
    public void delete(){
        CuratorDelete curatorDelete=new CuratorDelete();
        curatorDelete.delete("/zookeeper1223");
    }
}
